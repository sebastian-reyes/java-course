package com.sreyes;

import com.sreyes.model.NotificationEvent;
import com.sreyes.model.NotificationStatus;
import com.sreyes.model.Priority;
import com.sreyes.service.NotificationService;
import com.sreyes.service.impl.EmailService;
import com.sreyes.service.impl.PhoneService;
import com.sreyes.service.impl.TeamsService;
import com.sreyes.util.Constants;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class NotificationSystem {

  private final Sinks.Many<NotificationEvent> mainEventSink;

  @Getter
  private final Sinks.Many<NotificationEvent> historySink;

  private final NotificationService emailService;
  private final NotificationService phoneService;
  private final NotificationService teamsService;

  private final Sinks.One<NotificationEvent> teamsSink;
  private final Sinks.One<NotificationEvent> phoneSink;
  private final Sinks.One<NotificationEvent> emailSink;

  private final ConcurrentHashMap<String, NotificationEvent> notificationCache;

  public NotificationSystem() {
    this.mainEventSink = Sinks.many().multicast().onBackpressureBuffer();
    this.historySink = Sinks.many().replay().limit(50);

    this.emailService = new EmailService();
    this.phoneService = new PhoneService();
    this.teamsService = new TeamsService();

    this.teamsSink = Sinks.one();
    this.phoneSink = Sinks.one();
    this.emailSink = Sinks.one();

    this.notificationCache = new ConcurrentHashMap<>();
  }

  public NotificationSystem(
      NotificationService teamsService,
      NotificationService emailService,
      NotificationService phoneService) {
    this.mainEventSink = Sinks.many().multicast().onBackpressureBuffer();
    this.historySink = Sinks.many().replay().limit(50);

    this.teamsSink = Sinks.one();
    this.emailSink = Sinks.one();
    this.phoneSink = Sinks.one();

    this.teamsService = teamsService;
    this.emailService = emailService;
    this.phoneService = phoneService;

    this.notificationCache = new ConcurrentHashMap<>();

    setUpProcessingFlows();
  }

  private void setUpProcessingFlows() {
    this.mainEventSink
        .asFlux()
        .doOnNext(event -> {
          log.info("Received notification event: {}", event);
          updateEventStatus(event);
          this.historySink.tryEmitNext(event);
        })
        .subscribe(this::routeEventByPriority);

    this.setUpTeamsProcessor();
    this.setUpEmailProcessor();
    this.setUpPhoneProcessor();
  }

  private void setUpTeamsProcessor() {
    this.teamsSink
        .asMono()
        .flatMap(notificationEvent ->
            this.teamsService.sendNotification(notificationEvent).subscribeOn(Schedulers.boundedElastic())
                .doOnSuccess(success -> updateSuccess(notificationEvent, Constants.TEAMS_CHANNEL))
                .doOnError(error -> updateErrorStatus(notificationEvent, Constants.TEAMS_CHANNEL, error))
                .onErrorResume(error -> Mono.just(false))
        )
        .subscribe();
  }

  private void setUpEmailProcessor() {
    this.emailSink
        .asMono()
        .flatMap(notificationEvent ->
            this.emailService.sendNotification(notificationEvent).subscribeOn(Schedulers.boundedElastic())
                .doOnSuccess(success -> updateSuccess(notificationEvent, Constants.EMAIL_CHANNEL))
                .doOnError(error -> updateErrorStatus(notificationEvent, Constants.EMAIL_CHANNEL, error))
                .onErrorResume(error -> Mono.just(false))
        )
        .subscribe();
  }

  private void setUpPhoneProcessor() {
    this.phoneSink
        .asMono()
        .flatMap(notificationEvent ->
            this.phoneService.sendNotification(notificationEvent).subscribeOn(Schedulers.boundedElastic())
                .doOnSuccess(success -> updateSuccess(notificationEvent, Constants.PHONE_CHANNEL))
                .doOnError(error -> updateErrorStatus(notificationEvent, Constants.PHONE_CHANNEL, error))
                .retry(3)
                .onErrorResume(error -> Mono.just(false))
        )
        .subscribe();
  }

  private void updateEventStatus(NotificationEvent notificationEvent) {
    if (Objects.isNull(notificationEvent.getStatus())) {
      notificationEvent.setId(UUID.randomUUID().toString());
      notificationEvent.setStatus(NotificationStatus.PENDING);
    }
    this.notificationCache.put(notificationEvent.getId(), notificationEvent);
  }

  private void updateErrorStatus(NotificationEvent notificationEvent,
                                 String channel,
                                 Throwable error) {
    log.error("Error sending notification by: {}, for event {}, error; {}",
        channel,
        notificationEvent.getId(),
        error.getMessage());
    NotificationEvent cacheEvent = notificationCache.get(notificationEvent.getId());
    if (Objects.nonNull(cacheEvent)) {
      cacheEvent.setStatus(NotificationStatus.FAILED);
      this.historySink.tryEmitNext(cacheEvent);
    }
  }

  private void updateSuccess(NotificationEvent notificationEvent,
                             String channel) {
    log.info("Notification sent successfully by: {}, for event {}", channel,
        notificationEvent.getId());
    NotificationEvent cacheEvent = notificationCache.get(notificationEvent.getId());
    if (Objects.nonNull(cacheEvent)) {
      cacheEvent.setStatus(NotificationStatus.DELIVERED);
      this.historySink.tryEmitNext(cacheEvent);
    }
  }

  private void routeEventByPriority(NotificationEvent notificationEvent) {
    this.teamsSink.tryEmitValue(notificationEvent);

    if (notificationEvent.getPriority() == Priority.HIGH || notificationEvent.getPriority() == Priority.MEDIUM) {
      this.emailSink.tryEmitValue(notificationEvent);
    }

    if (notificationEvent.getPriority() == Priority.HIGH) {
      this.phoneSink.tryEmitValue(notificationEvent);
    }
  }

  public void publishEvent(NotificationEvent notificationEvent) {
    this.mainEventSink.tryEmitNext(notificationEvent);
  }

  public Flux<NotificationEvent> getNotificationHistory() {
    return this.historySink.asFlux();
  }

  public Mono<NotificationEvent> getNotificationById(String idNotification) {
    return Mono.justOrEmpty(this.notificationCache.get(idNotification));
  }

  public Flux<NotificationEvent> retryFailedNotification() {
    return Flux.fromIterable(this.notificationCache.values())
        .filter(notificationEvent -> notificationEvent.getStatus() == NotificationStatus.FAILED)
        .doOnNext(this::routeEventByPriority);
  }
}

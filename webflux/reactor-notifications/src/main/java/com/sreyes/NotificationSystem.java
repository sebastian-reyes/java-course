package com.sreyes;

import com.sreyes.model.NotificationEvent;
import com.sreyes.model.NotificationStatus;
import com.sreyes.service.NotificationService;
import com.sreyes.service.impl.EmailService;
import com.sreyes.service.impl.PhoneService;
import com.sreyes.service.impl.TeamsService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Sinks;

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
}

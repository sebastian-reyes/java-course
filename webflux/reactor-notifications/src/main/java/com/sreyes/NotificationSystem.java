package com.sreyes;

import com.sreyes.model.NotificationEvent;
import com.sreyes.service.NotificationService;
import com.sreyes.service.impl.EmailService;
import com.sreyes.service.impl.PhoneService;
import com.sreyes.service.impl.TeamsService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Sinks;

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
}

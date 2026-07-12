package com.sreyes.service.impl;

import com.sreyes.model.NotificationEvent;
import com.sreyes.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class EmailService implements NotificationService {

  @Override
  public Mono<Boolean> sendNotification(NotificationEvent event) {
    return Mono.fromCallable(() -> {
      //Simulate network latency
      Thread.sleep(300);

      //Simulate error 15%
      if (ThreadLocalRandom.current().nextInt(100) < 15) {
        throw new RuntimeException("Failed to send notification to Email");
      }

      // Simulate sending notification to Email
      log.info("Message in Email OK: {}", event.getMessage());

      // Simulate success
      return true;
    });
  }
}

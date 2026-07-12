package com.sreyes.service.impl;

import com.sreyes.model.NotificationEvent;
import com.sreyes.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class TeamsService implements NotificationService {

  @Override
  public Mono<Boolean> sendNotification(NotificationEvent event) {
    return Mono.fromCallable(() -> {
      //Simulate network latency
      Thread.sleep(150);

      //Simulate error
      if (ThreadLocalRandom.current().nextInt(10) == 2) {
        throw new RuntimeException("Failed to send notification to Microsoft Teams");
      }

      // Simulate sending notification to Microsoft Teams
      log.info("Message in Microsoft Teams OK: {}", event.getMessage());

      // Simulate success
      return true;
    });
  }
}

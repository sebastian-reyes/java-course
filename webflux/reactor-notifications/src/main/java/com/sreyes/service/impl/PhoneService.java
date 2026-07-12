package com.sreyes.service.impl;

import com.sreyes.model.NotificationEvent;
import com.sreyes.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class PhoneService implements NotificationService {

  @Override
  public Mono<Boolean> sendNotification(NotificationEvent event) {
    return Mono.fromCallable(() -> {
      //Simulate network latency
      Thread.sleep(1000);

      //Simulate error 20%
      if (ThreadLocalRandom.current().nextInt(100) < 20) {
        throw new RuntimeException("Failed to send notification to Phone");
      }

      // Simulate sending notification to Phone
      log.info("Message in Phone OK: {}", event.getMessage());

      // Simulate success
      return true;
    });
  }
}

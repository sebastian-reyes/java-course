package com.sreyes.service;

import com.sreyes.model.NotificationEvent;
import reactor.core.publisher.Mono;

public interface NotificationService {
  Mono<Boolean> sendNotification(NotificationEvent event);
}

package com.sreyes;


import com.sreyes.model.NotificationEvent;
import com.sreyes.service.NotificationService;
import com.sreyes.service.impl.EmailService;
import com.sreyes.service.impl.PhoneService;
import com.sreyes.service.impl.TeamsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicInteger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotificationSystemTest {

  private NotificationSystem notificationSystem;

  private AtomicInteger teamsCallCount;
  private AtomicInteger phoneCallCount;
  private AtomicInteger emailCallCount;

  @BeforeEach
  void setup() {
    this.teamsCallCount = new AtomicInteger(0);
    this.phoneCallCount = new AtomicInteger(0);
    this.emailCallCount = new AtomicInteger(0);

    NotificationService mockTeamsService = mock(TeamsService.class);
    NotificationService mockEmailService = mock(EmailService.class);
    NotificationService mockPhoneService = mock(PhoneService.class);

    when(mockTeamsService.sendNotification(any(NotificationEvent.class)))
        .thenAnswer(invocation -> {
          this.teamsCallCount.incrementAndGet();
          return Mono.just(true);
        });

    when(mockEmailService.sendNotification(any(NotificationEvent.class)))
        .thenAnswer(invocation -> {
          this.emailCallCount.incrementAndGet();
          return Mono.just(true);
        });

    when(mockPhoneService.sendNotification(any(NotificationEvent.class)))
        .thenAnswer(invocation -> {
          this.phoneCallCount.incrementAndGet();
          return Mono.just(true);
        });
  }

}
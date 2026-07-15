package com.sreyes;

import com.sreyes.model.NotificationEvent;
import com.sreyes.model.NotificationStatus;
import com.sreyes.model.Priority;
import com.sreyes.service.NotificationService;
import com.sreyes.service.impl.EmailService;
import com.sreyes.service.impl.PhoneService;
import com.sreyes.service.impl.TeamsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotificationSystemTest {

  private NotificationSystem notificationSystem;

  private NotificationService mockTeamsService;
  private NotificationService mockEmailService;
  private NotificationService mockPhoneService;

  private AtomicInteger teamsCallCount;
  private AtomicInteger phoneCallCount;
  private AtomicInteger emailCallCount;

  @BeforeEach
  void setup() {
    this.teamsCallCount = new AtomicInteger(0);
    this.phoneCallCount = new AtomicInteger(0);
    this.emailCallCount = new AtomicInteger(0);

    this.mockTeamsService = mock(TeamsService.class);
    this.mockEmailService = mock(EmailService.class);
    this.mockPhoneService = mock(PhoneService.class);

    when(mockTeamsService.sendNotification(any(NotificationEvent.class)))
        .thenAnswer(invocation -> {
          this.teamsCallCount.incrementAndGet();
          return Mono.just(true);
        });

    this.notificationSystem = new NotificationSystem(mockTeamsService, mockEmailService,
        mockPhoneService);
  }

  @Test
  @DisplayName("should send events with LOW priority")
  void testLowPriority(){
    NotificationEvent event = this.createTestEvent(Priority.LOW);
    this.notificationSystem.publishEvent(event);
    this.sleep(500);

    verify(mockTeamsService, times(1)).sendNotification(any());
    verify(mockEmailService, never()).sendNotification(any());
    verify(mockPhoneService, never()).sendNotification(any());

    assert this.teamsCallCount.get() == 1;
    assert this.emailCallCount.get() == 0;
    assert this.phoneCallCount.get() == 0;
  }

  private NotificationEvent createTestEvent(Priority priority) {
    return NotificationEvent.builder()
        .id(UUID.randomUUID().toString())
        .source("TEST")
        .message("Test msg with priority: " + priority.toString())
        .priority(priority)
        .timestamp(LocalDateTime.now())
        .status(NotificationStatus.PENDING)
        .build();
  }

  private void sleep(long mills) {
    try {
      Thread.sleep(mills);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
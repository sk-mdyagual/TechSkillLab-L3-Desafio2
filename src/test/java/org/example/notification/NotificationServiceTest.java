package org.example.notification;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationService notificationService;
    private String message;
    private String messageNull;
    private String messageBlank;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        message = "Notification from integration test";
        messageBlank = "";
        messageNull = null;
    }

    @Test
    void sendNotification() {
        Mockito.when(notificationRepository.send(message))
                .thenReturn(Boolean.TRUE);

        Boolean responseNotification = notificationService.sendNotification(message);

        Assertions.assertEquals(Boolean.TRUE, responseNotification);

        Mockito.verify(notificationRepository, Mockito.times(1))
                .send(message);
    }

    @Test
    void sendNotificationBlank() {
        Mockito.when(notificationRepository.send(messageBlank))
                .thenReturn(Boolean.FALSE);

        Boolean responseNotification = notificationService.sendNotification(messageBlank);

        Assertions.assertEquals(Boolean.FALSE, responseNotification);

        Mockito.verify(notificationRepository, Mockito.never())
                .send(messageBlank);
    }

    @Test
    void sendNotificationNull() {
        Mockito.when(notificationRepository.send(messageNull))
                .thenReturn(Boolean.FALSE);

        Boolean responseNotification = notificationService.sendNotification(messageNull);

        Assertions.assertEquals(Boolean.FALSE, responseNotification);

        Mockito.verify(notificationRepository, Mockito.never())
                .send(messageNull);
    }
}
package org.example.notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationService notificationService;

    private String message;
    private String invalidMessage;
    private String blankMessage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        message = "Test Notification";
        invalidMessage = null;
        blankMessage = "";
    }

    @Test
    void sendNotificationSuccess() {
        Mockito.when(notificationRepository.send(Mockito.anyString()))
                .thenReturn(true);
        boolean result = notificationService.sendNotification(message);
        assertTrue(result);
        Mockito.verify(notificationRepository, Mockito.times(1))
                .send(message);
    }

    @Test
    void sendNotificationNullMessageReturnFalse() {
        Mockito.when(notificationRepository.send(Mockito.anyString()))
                        .thenReturn(false);
        boolean result = notificationService.sendNotification(invalidMessage);
        assertFalse(result);
        Mockito.verify(notificationRepository, Mockito.never()).send(invalidMessage);
    }

    @Test
    void sendNotificationBlankMessageReturnFalse() {
        Mockito.when(notificationRepository.send(Mockito.anyString()))
                .thenReturn(false);
        boolean result = notificationService.sendNotification(blankMessage);
        assertFalse(result);
        Mockito.verify(notificationRepository, Mockito.never()).send(blankMessage);
    }
}
package org.example.notification;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class NotificationServiceTest {
    @InjectMocks
    private NotificationService notificationService;

    @Mock
    private NotificationRepository notificationRepository;

    private String message;
    private String nullMessage;
    private String blankMessage;

    @BeforeEach
    void setUp(){
        message = "Test notification";
        MockitoAnnotations.openMocks(this);
        nullMessage = null;
        blankMessage = "";
    }

    @Test
    void sendNotification_success(){
        Mockito.when(notificationRepository.send(message)).thenReturn(true);
        boolean result = notificationService.sendNotification(message);
        Assertions.assertTrue(result);
        Mockito.verify(notificationRepository, Mockito.times(1)).send(message);
    }

    @Test
    void sendNotification_nullMessage_returnFalse(){
        Mockito.when(notificationRepository.send(nullMessage)).thenReturn(false);
        boolean result = notificationService.sendNotification(nullMessage);
        Assertions.assertFalse(result);
        Mockito.verify(notificationRepository, Mockito.never()).send(nullMessage);
    }

    @Test
    void sendNotification_blankMessage_returnFalse(){
        Mockito.when(notificationRepository.send(blankMessage)).thenReturn(false);
        boolean result = notificationService.sendNotification(blankMessage);
        Assertions.assertFalse(result);
        Mockito.verify(notificationRepository, Mockito.never()).send(blankMessage);
    }

}
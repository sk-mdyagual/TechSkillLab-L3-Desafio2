package org.example.notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class NotificationServiceTest {

    private NotificationRepository notificationRepository;
    private NotificationService notificationService;
    private String message = "Mensaje de prueba";

    @BeforeEach
    void setUp(){
        notificationRepository = mock(NotificationRepository.class);
        notificationService = new NotificationService(notificationRepository);
    }

    @Test
    void sendNotification_Success() {
        Mockito.when(notificationRepository.send(message)).thenReturn(true);

        boolean result = notificationService.sendNotification(message);

        assertTrue(result);

        Mockito.verify(notificationRepository).send(message);
    }

    @Test
    void sendNotification_Failure_NullMessage() {
        boolean result = notificationService.sendNotification(null);
        assertFalse(result);
        Mockito.verify(notificationRepository, Mockito.never()).send(Mockito.anyString());
    }

    @Test
    void sendNotification_Failure_BlankMessage() {
        boolean result = notificationService.sendNotification("  ");
        assertFalse(result);
        Mockito.verify(notificationRepository, Mockito.never()).send(Mockito.anyString());
    }
}
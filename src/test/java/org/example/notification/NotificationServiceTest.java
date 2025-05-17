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

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationService notificationService;

    private String messageBlank;
    private String messageNull;
    private String messageSuccess;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        messageBlank = "";
        messageNull = null;
        messageSuccess = "Mensaje Exitoso";
    }


    @Test
    void sendNotification_Success() {
        Mockito.when(notificationRepository.send(messageSuccess))
                .thenReturn(true);

        boolean mensaje = notificationService.sendNotification(messageSuccess);

        Assertions.assertTrue(mensaje);

        Mockito.verify(notificationRepository, Mockito.times(1))
                .send(messageSuccess);
    }

    @Test
    void sendNotification_BlankMessage() {
        Mockito.when(notificationRepository.send(messageBlank))
                .thenReturn(false);

        boolean mensaje = notificationService.sendNotification(messageBlank);

        Assertions.assertFalse(mensaje);

        Mockito.verify(notificationRepository, Mockito.never())
                .send(messageBlank);
    }

    @Test
    void sendNotification_NullMessage() {
        Mockito.when(notificationRepository.send(messageNull))
                .thenReturn(false);

        boolean mensaje = notificationService.sendNotification(messageNull);

        Assertions.assertFalse(mensaje);

        Mockito.verify(notificationRepository, Mockito.never())
                .send(messageNull);
    }
}
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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        message = "¡Orden creada correctamente!";
    }

    @Test
    void processNotificationNull_Or_Blank_Failed(){
        Mockito.when(notificationRepository.send(Mockito.any(String.class)))
                .thenReturn(false);

        Assertions.assertFalse(notificationService.sendNotification(null));

        Assertions.assertFalse(notificationService.sendNotification(""));

        Mockito.verify(notificationRepository, Mockito.never())
                .send(Mockito.any(String.class));
    }

    @Test
    void processNotification_Success(){
        Mockito.when(notificationRepository.send(Mockito.any(String.class)))
                .thenReturn(true);

        Assertions.assertTrue(notificationService.sendNotification(message));

        Mockito.verify(notificationRepository, Mockito.times(1))
                .send(message);
    }
}
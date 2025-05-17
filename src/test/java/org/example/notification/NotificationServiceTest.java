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

    String messageValido;
    String messageNulo;
    String messageVacio;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.messageValido = "Hola, esto es una notificación";
        this.messageNulo = null;
        this.messageVacio = "";
    }

    @Test
    void sendNotificationExitosoConMensajeValido() {
        Mockito.when(notificationRepository.send(Mockito.any(String.class))).thenReturn(true);
        boolean respuesta = notificationService.sendNotification(messageValido);
        Assertions.assertTrue(respuesta);
        Mockito.verify(notificationRepository, Mockito.times(1)).send(messageValido);
    }

    @Test
    void sendNotificationFallidoConMensajeNulo() {
        Mockito.when(notificationRepository.send(Mockito.any(String.class))).thenReturn(false);
        boolean respuesta = notificationService.sendNotification(messageNulo);
        Assertions.assertFalse(respuesta);
        Mockito.verify(notificationRepository, Mockito.never()).send(Mockito.any(String.class));
    }

    @Test
    void sendNotificationFallidoConMensajeVacio() {
        Mockito.when(notificationRepository.send(Mockito.any(String.class))).thenReturn(false);
        boolean respuesta = notificationService.sendNotification(messageVacio);
        Assertions.assertFalse(respuesta);
        Mockito.verify(notificationRepository, Mockito.never()).send(Mockito.any(String.class));
    }
}
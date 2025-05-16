package org.example.notification;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;


class NotificationServiceTest {

    private NotificationRepository notificationRepository;
    private NotificationService notificationService;
    private String mensajeValido;
    private String mensajeNulo;
    private String mensajeVacio;

    @BeforeEach
    void setUp(){
        notificationRepository = mock(NotificationRepository.class);
        notificationService = new NotificationService(notificationRepository);
        mensajeValido = "Prueba";
        mensajeNulo = null;
        mensajeVacio = "";
    }

    @Test
    void sendNotification_Success() {
        Mockito.when(notificationRepository.send(mensajeValido)).thenReturn(true);
        boolean respuestaExitosa = notificationService.sendNotification(mensajeValido);
        Assertions.assertEquals(true, respuestaExitosa);
        Mockito.verify(notificationRepository, Mockito.times(1))
                .send(mensajeValido);
    }

    @Test
    void sendNotificationNull_Or_Blank_Failed(){
        Mockito.when(notificationRepository.send(mensajeNulo))
               .thenReturn(false);
        boolean respuestaFallida_Null = notificationService.sendNotification(mensajeNulo);
        Assertions.assertEquals(false, respuestaFallida_Null);
        boolean respuestaFallida_Blank = notificationService.sendNotification(mensajeVacio);
        Assertions.assertEquals(false, respuestaFallida_Blank);
        Mockito.verify(notificationRepository, Mockito.never())
                .send(mensajeNulo);
    }
}
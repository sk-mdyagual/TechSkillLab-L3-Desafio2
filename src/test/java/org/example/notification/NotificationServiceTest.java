package org.example.notification;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class NotificationServiceTest {

    private NotificationRepository notificationRepository;
    private NotificationService notificationService;
    private String mensaje;

    @BeforeEach
    void setUp() {
        notificationRepository = Mockito.mock(NotificationRepository.class);
        notificationService = new NotificationService(notificationRepository);
    }

    @Test
    void send_notification_con_mensaje_valido_y_devolver_true() {
        mensaje = "Hola, esta es una prueba de integración";
        Mockito.when(notificationRepository.send(Mockito.anyString()))
                .thenReturn(true);
        boolean resultado = notificationService.sendNotification(mensaje);
        Assertions.assertTrue(resultado);
        Mockito.verify(notificationRepository, Mockito.times(1))
                .send(mensaje);
    }

    @Test
    void send_notification_con_mensaje_null_y_devolver_false() {
        mensaje = null;
        boolean resultado = notificationService.sendNotification(mensaje);
        Assertions.assertFalse(resultado);
        Mockito.verify(notificationRepository, Mockito.never())
                .send(Mockito.anyString());
    }

    @Test
    void send_notification_con_mensaje_vacio_y_devolver_false() {
        mensaje = "";
        boolean resultado = notificationService.sendNotification(mensaje);
        Assertions.assertFalse(resultado);
        Mockito.verify(notificationRepository, Mockito.never()).send(Mockito.anyString());
    }
}

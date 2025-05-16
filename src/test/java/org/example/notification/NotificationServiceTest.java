package org.example.notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); 
    }

    @Test
    void sendNotificationMensajeVacioRetornarTrue() {
        String mensajeValido = "¡Hola mundo!";
        when(notificationRepository.send(mensajeValido)).thenReturn(true);

        boolean resultado = notificationService.sendNotification(mensajeValido);

        assertTrue(resultado);
        verify(notificationRepository).send(mensajeValido);
    }

    @Test
    void sendNotificationMensajeNull() {
        boolean resultado = notificationService.sendNotification(null);

        assertFalse(resultado);
        verify(notificationRepository, never()).send(any());
    }

    @Test
    void sendNotificationMensajeVacioRetornarFalse() {
        boolean resultado = notificationService.sendNotification("   ");

        assertFalse(resultado);
        verify(notificationRepository, never()).send(any());
    }
}

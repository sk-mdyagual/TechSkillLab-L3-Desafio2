package org.example.notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


public class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository; // Mock del repositorio

    @InjectMocks
    private NotificationService notificationService; // Instancia de la clase a probar, con mocks inyectados

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks antes de cada prueba
    }

    @Test
    void testSendNotification_ValidMessage() {
        // 1. Configura el mock para que devuelva true cuando se llame a send con cualquier String
        when(notificationRepository.send(anyString())).thenReturn(true);
        // 2.  metodo
        boolean result = notificationService.sendNotification("test@example.com");
        // 3. Verifica mensaje correcto
        verify(notificationRepository, times(1)).send("test@example.com");
        // 4. Asegura que el resultado es true
        assertTrue(result);
    }

    @Test
    void testSendNotification_NullMessage() {
        // Llama al metodo en null
        boolean result = notificationService.sendNotification(null);

        // Verifica que el metodo no se llamo
        verify(notificationRepository, never()).send(anyString());
        // Asegura que el resultado es false
        assertFalse(result);
    }

    @Test
    void testSendNotification_EmptyMessage() {
        // Llama al metodo en blanco
        boolean result = notificationService.sendNotification("");
        // Verifica que el metodo no se llamo
        verify(notificationRepository, never()).send(anyString());
        // Asegura que el resultado es false
        assertFalse(result);
    }

    @Test
    void testSendNotification_BlankMessage() {
        // Llama al metodo con espacios
        boolean result = notificationService.sendNotification("   ");
        // Verifica que el metodo no se llamo
        verify(notificationRepository, never()).send(anyString());
        // Asegura que el resultado es false
        assertFalse(result);
    }
}

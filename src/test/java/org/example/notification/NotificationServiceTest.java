package org.example.notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    void testSendNotification_ValidEmail() {
        // Simula el comportamiento del repositorio para un email válido
        when(notificationRepository.send("valid@example.com")).thenReturn(true);

        // Llama al método a probar
        boolean result = notificationService.sendNotification("valid@example.com");

        // Verifica el resultado
        assertTrue(result);

        // Verifica que el método del mock fue llamado con el argumento correcto
        verify(notificationRepository).send("valid@example.com");
    }

    @Test
    void testSendNotification_NullEmail() {
        // Llama al método a probar con un email nulo
        boolean result = notificationService.sendNotification(null);

        // Verifica el resultado
        assertFalse(result);

        // Verifica que el método del mock no fue llamado
        verify(notificationRepository, never()).send(anyString());
    }

    @Test
    void testSendNotification_EmptyEmail() {
        // Llama al método a probar con un email vacío
        boolean result = notificationService.sendNotification("");

        // Verifica el resultado
        assertFalse(result);

        // Verifica que el método del mock no fue llamado
        verify(notificationRepository, never()).send(anyString());
    }

    @Test
    void testSendNotification_BlankEmail() {
        // Llama al método a probar con un email en blanco
        boolean result = notificationService.sendNotification("   ");

        // Verifica el resultado
        assertFalse(result);

        // Verifica que el método del mock no fue llamado
        verify(notificationRepository, never()).send(anyString());
    }
}
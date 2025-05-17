package org.example.notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Mockito;

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
    void testSendNotification_ValidarMensaje_ReturnTrue() {
        // Arrange
        String message = "Hello, World!";
        Mockito.when(notificationRepository.send(message)).thenReturn(true);

        // Act
        boolean result = notificationService.sendNotification(message);

        // Assert
        assertTrue(result);
        Mockito.verify(notificationRepository).send(message);
    }

    @Test
    void testSendNotification_NullMensaje_ReturnFalse() {
        // Act
        boolean result = notificationService.sendNotification(null);

        // Assert
        assertFalse(result);
        Mockito.verify(notificationRepository, Mockito.never()).send(Mockito.anyString());
    }

    @Test
    void testSendNotification_BlankMessage_ReturnFalse() {
        // Act
        boolean result = notificationService.sendNotification("   ");

        // Assert
        assertFalse(result);
        Mockito.verify(notificationRepository, Mockito.never()).send(Mockito.anyString());
    }

    @Test
    void testSendNotification_RepositoryReturnFalse() {
        // Arrange
        String message = "Failed message";
        Mockito.when(notificationRepository.send(message)).thenReturn(false);

        // Act
        boolean result = notificationService.sendNotification(message);

        // Assert
        assertFalse(result);
        Mockito.verify(notificationRepository).send(message);
    }

}
package org.example.notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

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
    void sendNotification_WithValidMessage_ShouldCallRepositoryAndReturnTrue() {

        String validMessage = "Mensaje válido";

        when(notificationRepository.send(validMessage)).thenReturn(true);

        boolean result = notificationService.sendNotification(validMessage);

        verify(notificationRepository, times(1)).send(validMessage);
        assertTrue(result);
    }

    @Test
    void sendNotification_WithNullMessage_ShouldNotCallRepositoryAndReturnFalse() {
        boolean result = notificationService.sendNotification(null);

        verify(notificationRepository, never()).send(anyString());
        assertFalse(result);
    }

    @Test
    void sendNotification_WithEmptyMessage_ShouldNotCallRepositoryAndReturnFalse() {

        boolean result = notificationService.sendNotification("");

        verify(notificationRepository, never()).send(anyString());
        assertFalse(result);
    }
}
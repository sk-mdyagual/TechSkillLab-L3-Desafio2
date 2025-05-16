package org.example.notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class NotificationServiceTest {

    @InjectMocks
    private NotificationService notificationService;

    @Mock
    private NotificationRepository notificationRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendNotification_when_message_is_not_null_return_true() {
        String message = "Test notification";
        boolean expectedResult = true;

        // Mock the behavior of the notificationRepository
        when(notificationRepository.send(message)).thenReturn(expectedResult);

        // Call the method under test
        boolean result = notificationService.sendNotification(message);

        assertTrue(result);

        Mockito.verify(notificationRepository).send(message);
    }

    @Test
    void sendNotification_when_message_blank_return_false() {
        String message = "";
        boolean expectedResult = true;

        // Mock the behavior of the notificationRepository
        when(notificationRepository.send(message)).thenReturn(expectedResult);

        // Call the method under test
        boolean result = notificationService.sendNotification(message);

        assertFalse(result);

        Mockito.verify(notificationRepository, Mockito.never()).send(message);
    }

    @Test
    void sendNotification_when_message_is_null_return_false() {
        String message = null;
        boolean expectedResult = true;

        // Mock the behavior of the notificationRepository
        when(notificationRepository.send(message)).thenReturn(expectedResult);

        // Call the method under test
        boolean result = notificationService.sendNotification(message);

        // Verify the result
        assertFalse(result);

        Mockito.verify(notificationRepository, Mockito.never()).send(message);
    }
}
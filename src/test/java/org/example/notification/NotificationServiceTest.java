package org.example.notification;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Tests for NotificationService with NotificationRepository included.
 * These tests validate the sendNotification method responses.
 */
class NotificationServiceTest {
    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationService notificationService;

    private String mockMessage;
    private String mockEmptyMessage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMessage = "Hello Mockito";
        mockEmptyMessage = "";
    }

    @Test
    void testSendNotificationWithValidMessage(){
        Mockito.when(notificationRepository.send(Mockito.any())).thenReturn(true);

        boolean mockResponse = notificationService.sendNotification(mockMessage);

        Assertions.assertTrue(mockResponse);

        Mockito.verify(notificationRepository, Mockito.times(1)).send(mockMessage);
    }

    @Test
    void testNotSendNotificationWithNullableMessage(){
        boolean mockResponse = notificationService.sendNotification(mockEmptyMessage);

        Assertions.assertFalse(mockResponse);

        Mockito.verify(notificationRepository, Mockito.never()).send(mockEmptyMessage);
    }
}
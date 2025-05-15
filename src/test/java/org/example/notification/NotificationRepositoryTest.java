package org.example.notification;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NotificationRepositoryTest {

    private NotificationRepository notificationRepository;
    private String message;

    @BeforeEach
    void setUp() {
        notificationRepository = new NotificationRepository();
        message = "¡Orden creada correctamente!";
    }

    @Test
    void send() {
        Assertions.assertTrue(notificationRepository.send(message));
    }
}
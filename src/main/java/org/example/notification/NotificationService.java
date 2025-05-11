package org.example.notification;

public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public boolean sendNotification(String message) {
        if (message == null || message.isBlank()) {
            return false;
        }
        return notificationRepository.send(message);
    }

}

package org.example.notification;

public class NotificationRepository {

    public boolean send(String message) {
        System.out.println("Notificación enviada: " + message);
        return true;
    }

}

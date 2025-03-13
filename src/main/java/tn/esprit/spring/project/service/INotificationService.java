package tn.esprit.spring.project.service;

import java.util.List;

import tn.esprit.spring.project.models.Notification;

public interface INotificationService {
    Notification envoyerNotification(Notification notification);
    List<Notification> getAllNotifications();
}

package com.tripat.instagram.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tripat.instagram.models.Notification;
import com.tripat.instagram.models.User;
import com.tripat.instagram.utils.NotificationType;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    
    List<Notification> findAllByReceiverOrderByUpdatedAtDesc(User receiver);
    Notification findFirstByReceiver(User receiver);
    Notification findFirstByReceiverAndType(User receiver, NotificationType type);
}
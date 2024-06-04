package com.tripat.instagram.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripat.instagram.models.Notification;
import com.tripat.instagram.models.NotificationLinks;
import com.tripat.instagram.models.User;
import com.tripat.instagram.repositories.NotificationRepository;
import com.tripat.instagram.utils.LinkType;
import com.tripat.instagram.utils.NotificationType;

@Service
public class NotificationService {

    @Autowired
    NotificationRepository notificationRepository;
    
    public void createFollowRequestNotification(User receiver, User user){
        Notification notification = new Notification();
        notification.setReceiver(receiver);
        notification.setType(NotificationType.PRIVATE_FOLLOW_REQUEST);
        notification.setMessage(user.getAlias() + " requested to follow you.");
        notification.setUser(user);
        NotificationLinks link = new NotificationLinks();
        link.setStartPointer(0);
        link.setEndPointer(user.getAlias().length());
        link.setNotification(notification);
        link.setType(LinkType.USER);
        link.setTypeId(Long.toString(receiver.getId()));
        List<NotificationLinks> links = new ArrayList<>();
        links.add(link);
        notification.setLinks(links);

        this.notificationRepository.save(notification);
    }

    public void deleteFollowRequestNotification(User receiver, User user){
        Notification notification = this.notificationRepository.findFirstByReceiverAndType(receiver, NotificationType.PRIVATE_FOLLOW_REQUEST);
        this.notificationRepository.delete(notification);
    }

    public void modifyFollowAcceptNotification(User receiver, User user){
        Notification notification = this.notificationRepository.findFirstByReceiverAndType(receiver, NotificationType.PRIVATE_FOLLOW_REQUEST);
        notification.setMessage(user.getAlias() + " accepted your follow request.");
        notification.setType(NotificationType.PRIVATE_FOLLOW_ACCEPT);
        this.notificationRepository.save(notification);
    }
}

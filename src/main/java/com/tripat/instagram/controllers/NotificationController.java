package com.tripat.instagram.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tripat.instagram.models.Notification;
import com.tripat.instagram.models.User;
import com.tripat.instagram.repositories.NotificationRepository;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    NotificationRepository notificationRepository;

    @GetMapping("")
    ResponseEntity<?> getNotifications(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        List<Notification> notifications = this.notificationRepository.findAllByReceiverOrderByUpdatedAtDesc(user);
        return ResponseEntity.ok(notifications);
    }
}
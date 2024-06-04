package com.tripat.instagram.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tripat.instagram.models.NotificationLinks;

public interface NotificationLinkRepository extends JpaRepository<NotificationLinks, Long> {
    
}

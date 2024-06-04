package com.tripat.instagram.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tripat.instagram.models.UserRelationship;
import com.tripat.instagram.models.User;

public interface RelationRepository extends JpaRepository<UserRelationship, Long> {
    UserRelationship findFirstByUserAndRelationWith(User user, User relationWith);
}

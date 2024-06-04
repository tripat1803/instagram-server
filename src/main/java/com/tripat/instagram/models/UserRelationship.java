package com.tripat.instagram.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "_user_relationship")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRelationship {
    @Id
    @GeneratedValue(generator = "gen")
    private Long id;
    private boolean isCloseFriend = false;
    private boolean isGuardian = false;
    private boolean isBestie = false;
    private boolean isFollowedBy = false;
    private boolean isFollowing = false;
    private boolean isBlocked = false;
    private boolean isIncomingRequest = false;
    private boolean isOutgoingRequest = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "relation_with_id")
    @JsonBackReference
    private User relationWith;
}
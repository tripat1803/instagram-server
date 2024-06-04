package com.tripat.instagram.controllers.responses;

import com.tripat.instagram.models.UserRelationship;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class RelationshipResponse {
    public boolean isCloseFriend = false;
    public boolean isGuardian = false;
    public boolean isBestie = false;
    public boolean isFollowedBy = false;
    public boolean isFollowing = false;
    public boolean isBlocked = false;
    public boolean isIncomingRequest = false;
    public boolean isOutgoingRequest = false;

    public RelationshipResponse(UserRelationship relation) {
        if(relation != null){
            this.isFollowing = relation.isFollowing();
            this.isBestie = relation.isBestie();
            this.isCloseFriend = relation.isCloseFriend();
            this.isFollowedBy = relation.isFollowedBy();
            this.isGuardian = relation.isGuardian();
            this.isBlocked = relation.isBlocked();
            this.isIncomingRequest = relation.isIncomingRequest();
            this.isOutgoingRequest = relation.isOutgoingRequest();
        }
    }
}
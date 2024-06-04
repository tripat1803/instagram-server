package com.tripat.instagram.controllers.responses;

import com.tripat.instagram.models.User;
import com.tripat.instagram.utils.embeddables.Image;

public class UserResponse {
    public String fullname;
    public String mobile;
    public String alias;
    public Image profile;
    public String bio;
    public String role;
    public boolean isAccountPublic;

    public RelationshipResponse relation;

    public UserResponse(User user, RelationshipResponse relation){
        this.fullname = user.getFirstname() + " " + user.getLastname();
        this.mobile = user.getMobile();
        this.alias = user.getAlias();
        this.profile = user.getProfile();
        this.bio = user.getBio();
        this.role = user.getRole().name();
        this.isAccountPublic = user.isAccountPublic();
        this.relation = relation;
    }
}
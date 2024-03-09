package com.tripat.instagram.controllers.responses;

import com.tripat.instagram.models.User;
import com.tripat.instagram.utils.Image;

public class UserResponse {
    public String fullname;
    public String mobile;
    public String alias;
    public Image profile;
    public String bio;
    public String role;

    public UserResponse(User user){
        this.fullname = user.getFirstname() + " " + user.getLastname();
        this.mobile = user.getMobile();
        this.alias = user.getAlias();
        this.profile = user.getProfile();
        this.bio = user.getBio();
        this.role = user.getRole().name();
    }
}
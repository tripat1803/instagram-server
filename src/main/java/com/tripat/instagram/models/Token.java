package com.tripat.instagram.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "_tokens")
public class Token {
    @Id
    @GeneratedValue(generator = "gen")
    private Long id;
    private String refresh_token;
    private String password_change_token;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Token(){}
    public Token(String refresh_token, String password_token, User user){
        this.refresh_token = refresh_token;
        this.password_change_token = password_token;
        this.user = user;
    }

    public String getRefreshToken(){
        return this.refresh_token;
    }
    public void setRefreshToken(String token){
        this.refresh_token = token;
    }
    public String getPasswordChangeToken(){
        return this.password_change_token;
    }
    public void setPasswordChangeToken(String token){
        this.password_change_token = token;
    }
    public User getUser(){
        return this.user;
    }
    public void setUser(User user){
        this.user = user;
    }
}

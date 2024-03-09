package com.tripat.instagram.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "_post_likes")
public class PostLikes {
    @Id
    @GeneratedValue(generator = "gen")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Posts post;

    public PostLikes(){}

    public PostLikes(User user, Posts post){
        this.user = user;
        this.post = post;
    }

    public void setUser(User user){
        this.user = user;
    }
    public void setPost(Posts post){
        this.post = post;
    }
    public User getUser(){
        return this.user;
    }
    public Posts getPost(){
        return this.post;
    }
}
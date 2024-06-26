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
@Table(name = "_post_likes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostLikes {
    @Id
    @GeneratedValue(generator = "gen")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonBackReference
    private Posts post;

    public PostLikes(User user, Posts post){
        this.user = user;
        this.post = post;
    }
}
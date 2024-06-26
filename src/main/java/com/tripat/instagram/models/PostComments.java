package com.tripat.instagram.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "_post_comments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostComments {
    @Id
    @GeneratedValue(generator = "gen")
    private Long id;
    private String comment;
    private Long subComments = 0l;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private PostComments parent;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonBackReference
    private Posts post;

    @OneToMany(mappedBy = "parent")
    @JsonManagedReference
    private List<PostComments> postComments;
}

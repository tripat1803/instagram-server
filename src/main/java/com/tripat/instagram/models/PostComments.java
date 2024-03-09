package com.tripat.instagram.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "_post_comments")
public class PostComments {
    @Id
    private Long id;
    private String comment;
    private Long subComments = 0l;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private PostComments parent;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Posts post;

    @OneToMany(mappedBy = "parent")
    private List<PostComments> postComments;
    
    public String getComment(){
        return this.comment;
    }
    public void setComment(String comment){
        this.comment = comment;
    }
    public Long getSubComment(){
        return this.subComments;
    }
    public void setSubComment(long subComments){
        this.subComments = subComments;
    }
}

package com.tripat.instagram.models;

import java.util.List;

import com.tripat.instagram.utils.Image;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "_posts")
public class Posts {
    @Id
    @GeneratedValue(generator = "gen")
    private Long post_id;
    @Embedded
    private Image image;
    private String description;
    private Boolean enableComments = true;
    private Boolean enableLikeandViewsCount = true;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private Long likes = 0l;
    private Long views = 0l;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostLikes> postLikes;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostComments> postComments;

    public Posts() {
    }

    public Posts(Image image, String description, Boolean enableComments, Boolean enableLikeandViewsCount,
            String city, String state, String postalCode, String country, User user) {
        this.image = image;
        this.description = description;
        this.enableComments = (enableComments != null) ? enableComments : true;
        this.enableLikeandViewsCount = (enableLikeandViewsCount != null) ? enableLikeandViewsCount : true;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.user = user;
    }

    public void setLikes(long data){
        this.likes = data;
    }
    public void setViews(long data){
        this.views = data;
    }
    public void setImageUrl(Image image) {
        this.image = image;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setCity(String city){
        this.city = city;
    }
    public void setState(String state){
        this.state = state;
    }
    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }
    public void setCountry(String country){
        this.country = country;
    }
    public void setEnableComments(Boolean enableComments){
        this.enableComments = enableComments;
    }
    public void setEnableLikeAndViewsCount(Boolean enableLikeAndViewsCount){
        this.enableLikeandViewsCount = enableLikeAndViewsCount;
    }
    public void setPostLikes(List<PostLikes> postlikes){
        this.postLikes = postlikes;
    }
    public void setPostComments(List<PostComments> postComments){
        this.postComments = postComments;
    }
    public void setUser(User user){
        this.user = user;
    }
    public Image getImageUrl() {
        return this.image;
    }
    public String getDescription(){
        return this.description;
    }
    public String getCity(){
        return this.city;
    }
    public String getState(){
        return this.state;
    }
    public String getPostalCode(){
        return this.postalCode;
    }
    public String getCountry(){
        return this.country;
    }
    public Boolean getEnableComments(){
        return this.enableComments;
    }
    public Boolean getEnableLikeAndViewsCount(){
        return this.enableLikeandViewsCount;
    }
    public Long getLikes(){
        return this.likes;
    }
    public Long getViews(){
        return this.views;
    }
    public List<PostLikes> getPostLikes(){
        return this.postLikes;
    }
    public List<PostComments> getPostComments(){
        return this.postComments;
    }
    public User getUser(){
        return this.user;
    }
}
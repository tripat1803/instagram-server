package com.tripat.instagram.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tripat.instagram.utils.embeddables.Image;

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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "_posts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PostLikes> postLikes;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PostComments> postComments;

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
}
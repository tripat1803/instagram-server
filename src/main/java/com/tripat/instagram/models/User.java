package com.tripat.instagram.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tripat.instagram.utils.Image;
import com.tripat.instagram.utils.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "_users")
public class User implements UserDetails {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(generator = "gen")
    private Long id;
    @Column(unique = true)
    private String alias;
    @Column(unique = true)
    private String email;
    private String firstname;
    private String lastname;
    private String bio;
    @Embedded
    private Image profile;
    private String mobile;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean isAccountPrivate = true;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Token token;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Posts> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PostLikes> postLikes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PostComments> postComments; 

    public User(){
        
    }

    public User(String alias, String email, String firstname, String lastname, String bio, Image profile, String mobile, String password, Role role, boolean isAccountPrivate){
        this.alias = alias;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.bio = bio;
        this.profile = profile;
        this.mobile = mobile;
        this.password = password;
        this.role = role;
        this.isAccountPrivate = isAccountPrivate;
    }
    
    public void setAlias(String alias) {
        this.alias = alias;
    }
    public String getAlias() {
        return this.alias;
    }
    public void setAccountPrivate(boolean isAccountPrivate) {
        this.isAccountPrivate = isAccountPrivate;
    }
    public Boolean getAccountPrivate() {
        return this.isAccountPrivate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setPostLikes(List<PostLikes> postlikes){
        this.postLikes = postlikes;
    }

    public void setPosts(List<Posts> posts){
        this.posts = posts;
    }
    
    public void setPostComments(List<PostComments> postComments){
        this.postComments = postComments;
    }

    public List<Posts> getPosts(){
        return this.posts;
    }

    public List<PostLikes> getPostLikes(){
        return this.postLikes;
    }

    public List<PostComments> getPostComments(){
        return this.postComments;
    }

    public Image getProfile() {
        return this.profile;
    }

    public void setProfile(Image profile) {
        this.profile = profile;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
        list.add(new SimpleGrantedAuthority(this.role.name()));
        return list;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

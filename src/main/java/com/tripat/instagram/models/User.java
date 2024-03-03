package com.tripat.instagram.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tripat.instagram.utils.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    private String profile;
    private String mobile;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(){
        
    }

    public User(String alias, String email, String firstname, String lastname, String bio, String profile, String mobile, String password, Role role){
        this.alias = alias;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.bio = bio;
        this.profile = profile;
        this.mobile = mobile;
        this.password = password;
        this.role = role;
    }
    
    public void setAlias(String alias) {
        this.alias = alias;
    }
    public String getAlias() {
        return this.alias;
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

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
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

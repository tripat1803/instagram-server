package com.tripat.instagram.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "_tokens")
public class Token {
    @Id
    @GeneratedValue(generator = "gen")
    private Long id;
    private String refresh_token;
    private String password_change_token;

    private Long userId;
}

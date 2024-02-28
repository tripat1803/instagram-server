package com.tripat.instagram.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tripat.instagram.models.Token;
import com.tripat.instagram.models.User;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findFirstByUser(User user);
}
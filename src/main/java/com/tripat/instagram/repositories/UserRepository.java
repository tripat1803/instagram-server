package com.tripat.instagram.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tripat.instagram.models.User;


public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmail(String email);
    User findFirstByEmailOrUsername(String email, String username);
    User findFirstByEmail(String email);
}

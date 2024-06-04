package com.tripat.instagram.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tripat.instagram.models.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByEmailOrAlias(String email, String alias);
    User findFirstByEmail(String email);
    User findFirstByAlias(String alias);
}

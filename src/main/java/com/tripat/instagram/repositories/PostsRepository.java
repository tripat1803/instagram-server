package com.tripat.instagram.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tripat.instagram.models.Posts;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    
}

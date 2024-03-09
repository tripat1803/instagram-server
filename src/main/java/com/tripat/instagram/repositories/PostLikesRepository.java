package com.tripat.instagram.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tripat.instagram.models.PostLikes;

public interface PostLikesRepository extends JpaRepository<PostLikes, Long> {
    
}

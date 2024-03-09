package com.tripat.instagram.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tripat.instagram.models.PostComments;

public interface PostCommentsRepository extends JpaRepository<PostComments, Long> {
    
}

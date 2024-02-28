package com.tripat.instagram.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @GetMapping("/")
    ResponseEntity<?> getUser(){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(authentication.getPrincipal());
    }
}

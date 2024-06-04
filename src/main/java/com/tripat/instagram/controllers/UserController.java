package com.tripat.instagram.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tripat.instagram.controllers.responses.UserResponse;
import com.tripat.instagram.models.User;
import com.tripat.instagram.repositories.UserRepository;
import com.tripat.instagram.services.JwtService;
import com.tripat.instagram.services.TokenService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    JwtService jwtService;

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;
    
    @GetMapping("/")
    ResponseEntity<?> getUser(){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        UserResponse res = new UserResponse((User) authentication.getPrincipal(), null);
        return ResponseEntity.ok(res);
    }
}

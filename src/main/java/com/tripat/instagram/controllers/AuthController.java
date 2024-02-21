package com.tripat.instagram.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tripat.instagram.models.User;
import com.tripat.instagram.repositories.UserRepository;
import com.tripat.instagram.services.JwtService;
import com.tripat.instagram.utils.AuthenticationRequest;
import com.tripat.instagram.utils.AuthenticationResponse;
import com.tripat.instagram.utils.RegisterRequest;
import com.tripat.instagram.utils.Role;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtService jwtService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping("")
    public String testAuth() {
        return "Hello";
    }

    @PostMapping("/register")
    ResponseEntity<?> registerUser(@RequestBody RegisterRequest request, HttpServletResponse response) {
        User user = new User();
        user.setFirstname(request.firstname);
        user.setLastname(request.lastname);
        user.setEmail(request.email);
        user.setMobile(request.mobile);
        user.setUsername(request.username);
        user.setPassword(passwordEncoder.encode(request.password));
        user.setRole(Role.USER);
        User savedUser = this.userRepository.save(user);
        AuthenticationResponse res = new AuthenticationResponse();
        res.access_token = jwtService.getnerateAccessToken(savedUser);
        res.refresh_token = jwtService.getnerateRefreshToken(savedUser);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/login")
    ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest request, HttpServletResponse response) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email, request.password));
        User user = this.userRepository.findFirstByEmail(request.email);
        AuthenticationResponse res = new AuthenticationResponse();
        res.access_token = jwtService.getnerateAccessToken(user);
        res.refresh_token = jwtService.getnerateRefreshToken(user);
        return ResponseEntity.ok(res);
    }
}

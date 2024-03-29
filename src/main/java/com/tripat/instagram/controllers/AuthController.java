package com.tripat.instagram.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tripat.instagram.controllers.requests.AuthenticationRequest;
import com.tripat.instagram.controllers.requests.RegisterRequest;
import com.tripat.instagram.controllers.responses.AuthenticationResponse;
import com.tripat.instagram.models.User;
import com.tripat.instagram.repositories.UserRepository;
import com.tripat.instagram.services.JwtService;
import com.tripat.instagram.services.TokenService;
import com.tripat.instagram.utils.Role;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = {"http://localhost:3000"}, allowCredentials = "true")
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
    @Autowired TokenService tokenService;

    @PostMapping("/register")
    ResponseEntity<?> registerUser(@RequestBody RegisterRequest request, HttpServletResponse response) {
        User user = new User();
        user.setFirstname(request.firstname);
        user.setLastname(request.lastname);
        user.setEmail(request.email);
        user.setMobile(request.mobile);
        user.setAlias(request.alias);
        user.setPassword(passwordEncoder.encode(request.password));
        user.setRole(Role.USER);
        User savedUser = this.userRepository.save(user);
        String access_token = this.jwtService.generateAccessToken(savedUser);
        String refresh_token = this.jwtService.getnerateRefreshToken(savedUser);
        this.tokenService.saveUserToken(savedUser, refresh_token);
        AuthenticationResponse res = new AuthenticationResponse(access_token, refresh_token, response);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/login")
    ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest request, HttpServletResponse response) {
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email, request.password));
        User user = this.userRepository.findFirstByEmail(request.email);
        String access_token = this.jwtService.generateAccessToken(user);
        String refresh_token = this.jwtService.getnerateRefreshToken(user);
        this.tokenService.saveUserToken(user, refresh_token);
        AuthenticationResponse res = new AuthenticationResponse(access_token, refresh_token, response);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/refresh-token")
    ResponseEntity<?> refreshUserToken(@CookieValue(name = "x-auth-rt") String token, HttpServletRequest request, HttpServletResponse response){
        try {
            String email = this.jwtService.extractUsername(token);
            User user = this.userRepository.findFirstByEmail(email);
            if(user == null || !this.jwtService.isTokenValid(token, user)){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            String access_token = this.jwtService.generateAccessToken(user);
            String refresh_token = this.jwtService.getnerateRefreshToken(user);
            this.tokenService.saveUserToken(user, refresh_token);
            AuthenticationResponse res = new AuthenticationResponse(access_token, refresh_token, response);
            return ResponseEntity.ok(res);
        } catch(Exception e){
            Cookie cookie = new Cookie("x-auth-rt", null);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
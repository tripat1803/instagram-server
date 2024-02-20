package com.tripat.instagram.services;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    private String secret = "ODRmMmU1MjgyY2FjYmNmZWMzYzRmNzg0MDg0OGI4ZWY=";
    private long expiration = 86400000;
    private long refreshExpiration = 604800000;

    public String extractUsername(String token){
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    public Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        Claims claim =  extractAllClaims(token);
        return claimsResolver.apply(claim);
    }

    public String getnerateAccessToken(UserDetails userDetails){
        return generateToken(userDetails, expiration);
    }
    
    public String getnerateRefreshToken(UserDetails userDetails){
        return generateToken(userDetails, refreshExpiration);
    }

    public String generateToken(UserDetails userDetails, long expiration){
        return Jwts
                .builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSecretKey())
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !extractClaim(token, Claims::getExpiration).before(new Date()));
    }

    private Key getSecretKey(){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}

package com.tripat.instagram.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tripat.instagram.services.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if(request.getServletPath().contains("/api/upload")){
            filterChain.doFilter(request, response);
            return;
        }
        if(request.getServletPath().contains("/api/auth")){
            filterChain.doFilter(request, response);
            return;
        }
        String token = request.getHeader("x-auth-token");
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }
        String email = jwtService.extractUsername(token);
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userdetails = this.userDetailsService.loadUserByUsername(email);
            if (jwtService.isTokenValid(token, userdetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userdetails, null, userdetails.getAuthorities());
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}

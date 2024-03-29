package com.tripat.instagram.controllers.responses;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationResponse {
    public String access_token;
    private Cookie refresh_cookie;

    public AuthenticationResponse(){}
    public AuthenticationResponse(String access_token, String refresh_token, HttpServletResponse response){
        this.access_token = access_token;
        this.refresh_cookie = new Cookie("x-auth-rt", refresh_token);
        this.refresh_cookie.setHttpOnly(true);
        this.refresh_cookie.setMaxAge(24 * 60 * 60 * 7);
        response.addCookie(this.refresh_cookie);
    }
}
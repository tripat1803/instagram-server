package com.tripat.instagram.utils;

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
        response.addCookie(this.refresh_cookie);
    }
}
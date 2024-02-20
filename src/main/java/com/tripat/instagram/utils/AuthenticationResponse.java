package com.tripat.instagram.utils;

import jakarta.servlet.http.Cookie;

public class AuthenticationResponse {
    private Cookie accessCookie;
    private Cookie refreshCookie;

    public AuthenticationResponse(){}

    public AuthenticationResponse(String access_token, String refresh_token){
        accessCookie = new Cookie("x-auth-token", access_token);
        accessCookie.setHttpOnly(true);
        accessCookie.setMaxAge(60*60*24*1);
        refreshCookie = new Cookie("x-auth-refresh-token", refresh_token);
        refreshCookie.setHttpOnly(true);
        refreshCookie.setMaxAge(60*60*24*7);
    }

    public Cookie getAccessCookie() {
        return accessCookie;
    }
    public Cookie getRefreshCookie() {
        return refreshCookie;
    }
}

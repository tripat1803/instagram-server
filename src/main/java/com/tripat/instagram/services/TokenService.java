package com.tripat.instagram.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripat.instagram.models.Token;
import com.tripat.instagram.models.User;
import com.tripat.instagram.repositories.TokenRepository;

@Service
public class TokenService {
    @Autowired
    TokenRepository tokenRepository;

    public void saveUserToken(User user, String token){
        Token userToken = this.tokenRepository.findFirstByUser(user);
        if(userToken != null){
            userToken.setRefreshToken(token);
        } else {
            userToken = new Token(token, null, user);
        }
        this.tokenRepository.save(userToken);
    }

    // public Token getUserToken(String token){
    //     Token userToken = this.tokenRepository.findFirstByUser(user);
    //     if(userToken != null){
    //         userToken.setRefreshToken(token);
    //     } else {
    //         userToken = new Token(token, null, user);
    //     }
    //     this.tokenRepository.save(userToken);
    // }
}
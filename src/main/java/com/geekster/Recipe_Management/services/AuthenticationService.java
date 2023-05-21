package com.geekster.Recipe_Management.services;

import com.geekster.Recipe_Management.models.AuthenticationToken;
import com.geekster.Recipe_Management.repositories.IAuthenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AuthenticationService {

    @Autowired
    IAuthenticationRepo tokenRepo;

    public void saveToken(AuthenticationToken token) {
        tokenRepo.save(token);
    }

    @Transactional
    public boolean authenticate(String email, String token) {

        if(token==null && email==null){
            return false;
        }

        AuthenticationToken authToken = tokenRepo.findByToken(token);

        if(authToken==null){
            return false;
        }

        String expectedEmail = authToken.getUser().getUserEmail();


        return expectedEmail.equals(email);
    }

    @Transactional
    public void deleteToken(String token) {

        AuthenticationToken token1 = tokenRepo.findByToken(token);

        tokenRepo.deleteById(token1.getTokenId());
    }

}

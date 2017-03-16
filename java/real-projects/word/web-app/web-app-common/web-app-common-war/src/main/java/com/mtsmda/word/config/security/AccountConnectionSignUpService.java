package com.mtsmda.word.config.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by dminzat on 3/16/2017.
 */
@Component
@Deprecated //TODO: will be finished in future
public class AccountConnectionSignUpService /*implements ConnectionSignUp*/ {

    /**
     * userId
     providerId
     providerUserId
     rank
     accessToken
     * */
    /*@Override
    public String execute(Connection<?> connection) {
        String userId = UUID.randomUUID().toString();
        return userId;
    }*/
}
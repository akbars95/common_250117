package com.mtsmda.spring.helper.helper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by dminzat on 3/7/2017.
 */
public class BCryptPasswordEncoderHelper {

    public static String getBCryptPasswordEncoder(String password){
        return new BCryptPasswordEncoder().encode(password);
    }

}
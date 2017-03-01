package com.mtsmda.encrypt;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by dminzat on 2/27/2017.
 */
public class Base64Encoder {

    public static final String USERNAME_EXCEPTION_MESSAGE = "username cannot be null or empty!";
    public static final String PASSWORD_EXCEPTION_MESSAGE = "password cannot be null or empty!";

    public static final String getEncodeBase64(String username, String password){
        if(StringUtils.isBlank(username)){
            throw new RuntimeException(USERNAME_EXCEPTION_MESSAGE);
        }
        if(StringUtils.isBlank(password)){
            throw new RuntimeException(PASSWORD_EXCEPTION_MESSAGE);
        }
        return new String(Base64.encodeBase64(new String(username + ":" + password).getBytes()));
    }

}
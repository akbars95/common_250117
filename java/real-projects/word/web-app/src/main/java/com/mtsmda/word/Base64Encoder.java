package com.mtsmda.word;


import org.apache.commons.codec.binary.Base64;

/**
 * Created by dminzat on 2/27/2017.
 */
public class Base64Encoder {

    public static final String getEncodeBase64(String username, String password){
        return new String(Base64.encodeBase64(new String(username + ":" + password).getBytes()));
    }

}
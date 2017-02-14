package com.mtsmda.apache.camel.withoutspring.bean;

/**
 * Created by dminzat on 2/14/2017.
 */
public class StringBean {

    public String translatorUpper(String inWord){
        return "U-" + inWord.toUpperCase();
    }

    public String translatorLower(String inWord){
        return "L-" + inWord.toLowerCase();
    }

}
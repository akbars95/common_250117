package com.mtsmda.apache.camel.withoutspring.bean;

/**
 * Created by dminzat on 2/14/2017.
 */
public class SimpleBean {

    public String translator(String inWord){
        return "[" + inWord + "]" + inWord.toUpperCase();
    }

}
package com.mtsmda.apache.camel.withoutspring.bean;

/**
 * Created by dminzat on 2/14/2017.
 */
public class ObjectBean {

    public String translatorUpper(Object in) {
        return in.toString();
    }

}
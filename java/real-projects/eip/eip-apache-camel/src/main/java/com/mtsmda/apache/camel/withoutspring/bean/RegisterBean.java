package com.mtsmda.apache.camel.withoutspring.bean;

/**
 * Created by dminzat on 2/14/2017.
 */
public class RegisterBean {

    public void log(String body){
        System.out.println(this.getClass().getCanonicalName() + ".log(" + body + ");");
    }

}
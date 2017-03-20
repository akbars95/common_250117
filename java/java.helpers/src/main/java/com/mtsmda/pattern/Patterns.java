package com.mtsmda.pattern;

/**
 * Created by dminzat on 9/1/2016.
 */
public class Patterns {

    public static final String USERNAME = "^[\\w-\\.]{7,50}$";
    public static final String PASSWORD = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%-.,]).{5,50})";
    public static final String FIRST_LAST_MIDDLE_NAME = "^[\\w-']{1,75}$";
    public static final String GENDER = "^([Mm]|[Ff]){1,1}$";
    public static final String EMAIL_PATTERN = "^[\\w-\\+]+(\\.[\\w-]+)*@[A-Za-z\\d-]+(\\.[A-Za-z\\d]+)*(\\.[A-Za-z]{2,})$";
    public static final String MOLDOVA_PHONE_NUMBER = "^(([\\d]{3}-[\\d]{3}-[\\d]{3})|([\\d]{1}-[\\d]{3}-[\\d]{2}-[\\d]{3}))$";
    public static final String SITE_NAME = "^((http|https)://(www.)?)?([\\w-\\.]+)+\\.([\\w-]+)$";

}
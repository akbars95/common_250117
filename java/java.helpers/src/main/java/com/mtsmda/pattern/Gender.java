package com.mtsmda.pattern;

/**
 * Created by dminzat on 2/19/2017.
 */
public enum Gender {

    M("male"), F("female");
    private String name;

    Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
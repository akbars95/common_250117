package com.mtsmda.real.project.user.model;

import java.io.Serializable;

/**
 * Created by dminzat on 2/19/2017.
 */
public class Account implements Serializable{

    private User user;
    private String accountUsername;
    private String accountPassword;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Integer userMaxAttempts;

    public Account() {

    }

    public Account(User user) {
        this.user = user;
    }

    public Account(User user, String accountUsername, String accountPassword, Boolean accountNonExpired, Boolean accountNonLocked, Boolean credentialsNonExpired, Integer userMaxAttempts) {
        this.user = user;
        this.accountUsername = accountUsername;
        this.accountPassword = accountPassword;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.userMaxAttempts = userMaxAttempts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAccountUsername() {
        return accountUsername;
    }

    public void setAccountUsername(String accountUsername) {
        this.accountUsername = accountUsername;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Integer getUserMaxAttempts() {
        return userMaxAttempts;
    }

    public void setUserMaxAttempts(Integer userMaxAttempts) {
        this.userMaxAttempts = userMaxAttempts;
    }
}
package com.mtsmda.real.project.user.model;

import java.io.Serializable;
import java.util.Set;

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
    private Integer userCountDaysNeedChangePass;
    private Set<PasswordHistory> passwordHistories;
    private UserAttempt userAttempt;

    public Account() {

    }

    public Account(User user) {
        this.user = user;
    }

    public Account(String accountUsername) {
        this.accountUsername = accountUsername;
    }

    public Account(String accountUsername, String accountPassword) {
        this(accountUsername);
        this.accountPassword = accountPassword;
    }

    public Account(User user, String accountUsername, String accountPassword, Boolean accountNonExpired, Boolean accountNonLocked, Boolean credentialsNonExpired, Integer userMaxAttempts) {
        this(accountUsername, accountPassword);
        this.user = user;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.userMaxAttempts = userMaxAttempts;
    }

    public User getUser() {
        return user;
    }

    public Account setUser(User user) {
        this.user = user;
        return this;
    }

    public String getAccountUsername() {
        return accountUsername;
    }

    public Account setAccountUsername(String accountUsername) {
        this.accountUsername = accountUsername;
        return this;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public Account setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
        return this;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public Account setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
        return this;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public Account setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
        return this;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public Account setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
        return this;
    }

    public Integer getUserMaxAttempts() {
        return userMaxAttempts;
    }

    public Account setUserMaxAttempts(Integer userMaxAttempts) {
        this.userMaxAttempts = userMaxAttempts;
        return this;
    }

    public Integer getUserCountDaysNeedChangePass() {
        return userCountDaysNeedChangePass;
    }

    public Account setUserCountDaysNeedChangePass(Integer userCountDaysNeedChangePass) {
        this.userCountDaysNeedChangePass = userCountDaysNeedChangePass;
        return this;
    }

    public Set<PasswordHistory> getPasswordHistories() {
        return passwordHistories;
    }

    public Account setPasswordHistories(Set<PasswordHistory> passwordHistories) {
        this.passwordHistories = passwordHistories;
        return this;
    }

    public UserAttempt getUserAttempt() {
        return userAttempt;
    }

    public Account setUserAttempt(UserAttempt userAttempt) {
        this.userAttempt = userAttempt;
        return this;
    }
}
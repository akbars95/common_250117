package com.mtsmda.real.project.user.model;

import com.mtsmda.real.project.user.annotation.Column;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by dminzat on 3/1/2017.
 */
public class UserAttempt implements Serializable {

    @Column(name = "USER_ATTEMPT_ID")
    private Integer userAttemptId;

    @Column(name = "ACCOUNT_USER_ID")
    private Account account;

    @Column(name = "ATTEMPTS")
    private Integer attempts;

    @Column(name = "LAST_MODIFIED")
    private LocalDateTime lastModified;

    public UserAttempt() {

    }

    public UserAttempt(Integer userAttemptId, Account account, Integer attempts, LocalDateTime lastModified) {
        this.userAttemptId = userAttemptId;
        this.account = account;
        this.attempts = attempts;
        this.lastModified = lastModified;
    }

    public Integer getUserAttemptId() {
        return userAttemptId;
    }

    public UserAttempt setUserAttemptId(Integer userAttemptId) {
        this.userAttemptId = userAttemptId;
        return this;
    }

    public Account getAccount() {
        return account;
    }

    public UserAttempt setAccount(Account user) {
        this.account = user;
        return this;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public UserAttempt setAttempts(Integer attempts) {
        this.attempts = attempts;
        return this;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public UserAttempt setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
        return this;
    }
}
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

    public void setUserAttemptId(Integer userAttemptId) {
        this.userAttemptId = userAttemptId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account user) {
        this.account = user;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }
}
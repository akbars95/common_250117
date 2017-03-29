package com.mtsmda.real.project.user.model;

import java.time.LocalDateTime;

/**
 * Created by dminzat on 3/27/2017.
 */
public class AccountStatusType {

    private User user;
    private StatusType statusType;
    private LocalDateTime actionTimestamp;

    public AccountStatusType() {

    }

    public AccountStatusType(User user, StatusType statusType, LocalDateTime actionTimestamp) {
        this.user = user;
        this.statusType = statusType;
        this.actionTimestamp = actionTimestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
    }

    public LocalDateTime getActionTimestamp() {
        return actionTimestamp;
    }

    public void setActionTimestamp(LocalDateTime actionTimestamp) {
        this.actionTimestamp = actionTimestamp;
    }
}
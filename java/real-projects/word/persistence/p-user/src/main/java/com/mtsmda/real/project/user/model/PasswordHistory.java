package com.mtsmda.real.project.user.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by dminzat on 3/2/2017.
 */
public class PasswordHistory implements Serializable{

    private Account account;
    private String accountPassword;
    private LocalDateTime accountPasswordChangeLocalDateTime;

    public PasswordHistory() {

    }

    public PasswordHistory(Account account, String accountPassword, LocalDateTime accountPasswordChangeLocalDateTime) {
        this.account = account;
        this.accountPassword = accountPassword;
        this.accountPasswordChangeLocalDateTime = accountPasswordChangeLocalDateTime;
    }

    public Account getAccount() {
        return account;
    }

    public PasswordHistory setAccount(Account account) {
        this.account = account;
        return this;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public PasswordHistory setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
        return this;
    }

    public LocalDateTime getAccountPasswordChangeLocalDateTime() {
        return accountPasswordChangeLocalDateTime;
    }

    public PasswordHistory setAccountPasswordChangeLocalDateTime(LocalDateTime accountPasswordChangeLocalDateTime) {
        this.accountPasswordChangeLocalDateTime = accountPasswordChangeLocalDateTime;
        return this;
    }
}
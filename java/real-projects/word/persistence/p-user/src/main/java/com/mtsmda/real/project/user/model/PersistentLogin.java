package com.mtsmda.real.project.user.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by dminzat on 3/7/2017.
 */
public class PersistentLogin implements Serializable {

    private Account account;
    private String series;
    private String token;
    private LocalDateTime lastUsed;

    public PersistentLogin() {

    }

    public PersistentLogin(String series, String token, LocalDateTime lastUsed) {
        this.series = series;
        this.token = token;
        this.lastUsed = lastUsed;
    }

    public PersistentLogin(Account account, String series, String token, LocalDateTime lastUsed) {
        this.account = account;
        this.series = series;
        this.token = token;
        this.lastUsed = lastUsed;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(LocalDateTime lastUsed) {
        this.lastUsed = lastUsed;
    }
}
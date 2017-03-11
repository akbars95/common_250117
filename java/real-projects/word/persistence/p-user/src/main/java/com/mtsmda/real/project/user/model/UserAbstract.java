package com.mtsmda.real.project.user.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by dminzat on 2/19/2017.
 */
public abstract class UserAbstract implements Serializable{

    private Integer userId;
    private String userFirstName;
    private String userLastName;
    private String userMiddleName;
    private String userEmail;
    private String userPhone;
    private Gender userGender;
    private LocalDate userDateOfBirth;
    private Boolean userIsActive;
    private String userSiteURL;
    private LocalDateTime addUserLocalDateTime;

    public UserAbstract() {

    }

    public UserAbstract(Integer userId, String userFirstName, String userLastName, String userMiddleName, String userEmail, String userPhone, Gender userGender, LocalDate userDateOfBirth, Boolean userIsActive, String userSiteURL) {
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userMiddleName = userMiddleName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userGender = userGender;
        this.userDateOfBirth = userDateOfBirth;
        this.userIsActive = userIsActive;
        this.userSiteURL = userSiteURL;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserMiddleName() {
        return userMiddleName;
    }

    public void setUserMiddleName(String userMiddleName) {
        this.userMiddleName = userMiddleName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Gender getUserGender() {
        return userGender;
    }

    public void setUserGender(Gender userGender) {
        this.userGender = userGender;
    }

    public LocalDate getUserDateOfBirth() {
        return userDateOfBirth;
    }

    public void setUserDateOfBirth(LocalDate userDateOfBirth) {
        this.userDateOfBirth = userDateOfBirth;
    }

    public Boolean getUserIsActive() {
        return userIsActive;
    }

    public void setUserIsActive(Boolean userIsActive) {
        this.userIsActive = userIsActive;
    }

    public String getUserSiteURL() {
        return userSiteURL;
    }

    public void setUserSiteURL(String userSiteURL) {
        this.userSiteURL = userSiteURL;
    }

    public LocalDateTime getAddUserLocalDateTime() {
        return addUserLocalDateTime;
    }

    public void setAddUserLocalDateTime(LocalDateTime addUserLocalDateTime) {
        this.addUserLocalDateTime = addUserLocalDateTime;
    }

}
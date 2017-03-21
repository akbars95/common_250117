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

    public UserAbstract(String userFirstName, String userLastName, String userMiddleName, String userEmail, String userPhone, Gender userGender, LocalDate userDateOfBirth, Boolean userIsActive, String userSiteURL) {
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

    public UserAbstract(Integer userId, String userFirstName, String userLastName, String userMiddleName, String userEmail, String userPhone, Gender userGender, LocalDate userDateOfBirth, Boolean userIsActive, String userSiteURL) {
        this(userFirstName, userLastName, userMiddleName, userEmail, userPhone, userGender, userDateOfBirth, userIsActive, userSiteURL);
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public UserAbstract setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public UserAbstract setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
        return this;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public UserAbstract setUserLastName(String userLastName) {
        this.userLastName = userLastName;
        return this;
    }

    public String getUserMiddleName() {
        return userMiddleName;
    }

    public UserAbstract setUserMiddleName(String userMiddleName) {
        this.userMiddleName = userMiddleName;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public UserAbstract setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public UserAbstract setUserPhone(String userPhone) {
        this.userPhone = userPhone;
        return this;
    }

    public Gender getUserGender() {
        return userGender;
    }

    public UserAbstract setUserGender(Gender userGender) {
        this.userGender = userGender;
        return this;
    }

    public LocalDate getUserDateOfBirth() {
        return userDateOfBirth;
    }

    public UserAbstract setUserDateOfBirth(LocalDate userDateOfBirth) {
        this.userDateOfBirth = userDateOfBirth;
        return this;
    }

    public Boolean getUserIsActive() {
        return userIsActive;
    }

    public UserAbstract setUserIsActive(Boolean userIsActive) {
        this.userIsActive = userIsActive;
        return this;
    }

    public String getUserSiteURL() {
        return userSiteURL;
    }

    public UserAbstract setUserSiteURL(String userSiteURL) {
        this.userSiteURL = userSiteURL;
        return this;
    }

    public LocalDateTime getAddUserLocalDateTime() {
        return addUserLocalDateTime;
    }

    public UserAbstract setAddUserLocalDateTime(LocalDateTime addUserLocalDateTime) {
        this.addUserLocalDateTime = addUserLocalDateTime;
        return this;
    }

}
package com.mtsmda.word.nonConfig.dto;

import com.mtsmda.pattern.Patterns;
import com.mtsmda.real.project.user.model.User;
import com.mtsmda.validation.structure.constraint.*;
import com.mtsmda.word.nonConfig.common.ConvertTo;
import com.mtsmda.word.nonConfig.common.Converter;
import com.mtsmda.word.nonConfig.validation.order.RegistrationOrder;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.io.Serializable;

import static com.mtsmda.validation.structure.constraint.DateEnum.LOCAL_DATE;
import static com.mtsmda.validation.structure.constraint.DateEnum.PAST;

/**
 * Created by dminzat on 3/16/2017.
 */
@PasswordEquals(className = RegistrationDTO.class, properties = {"password", "password2"}, groups = RegistrationOrder.class)
public class RegistrationDTO implements Serializable, ConvertTo<User> {

    @NotNull(groups = Default.class)
    @Size(min = 8, max = 50, groups = RegistrationOrder.class)
    @Pattern(regexp = Patterns.USERNAME, groups = RegistrationOrder.class)
    private String username;

    @NotNull(groups = Default.class)
    @Size(min = 8, max = 50, groups = RegistrationOrder.class)
    @Pattern(regexp = Patterns.PASSWORD, groups = RegistrationOrder.class)
    private String password;

    @NotNull(groups = Default.class)
    @Size(min = 8, max = 50, groups = RegistrationOrder.class)
    @Pattern(regexp = Patterns.PASSWORD, groups = RegistrationOrder.class)
    private String password2;

    @NotNull(groups = Default.class)
    @Size(min = 1, max = 75, groups = RegistrationOrder.class)
    @Pattern(regexp = Patterns.FIRST_LAST_MIDDLE_NAME, groups = RegistrationOrder.class)
    private String firstname;

    @NotNull(groups = Default.class)
    @Size(min = 1, max = 75, groups = RegistrationOrder.class)
    @Pattern(regexp = Patterns.FIRST_LAST_MIDDLE_NAME, groups = RegistrationOrder.class)
    private String lastname;

    @IsNotNull(groups = Default.class)
    @Size(min = 1, max = 75, groups = RegistrationOrder.class)
    @Pattern(regexp = Patterns.FIRST_LAST_MIDDLE_NAME, groups = RegistrationOrder.class)
    private String middlename;

    @NotNull(groups = Default.class)
    @Size(min = 10, max = 128, groups = RegistrationOrder.class)
    @Email(regexp = Patterns.EMAIL_PATTERN, groups = RegistrationOrder.class)
    private String email;

    @NotNull(groups = Default.class)
    @Size(min = 11, max = 12, groups = RegistrationOrder.class)
    @Email(regexp = Patterns.MOLDOVA_PHONE_NUMBER, groups = RegistrationOrder.class)
    private String phone;

    @NotNull(groups = Default.class)
    @Size(min = 1, max = 1, groups = RegistrationOrder.class)
    @Gender(groups = RegistrationOrder.class)
    private String gender;

    @NotNull(groups = Default.class)
    @Size(min = 10, max = 10, groups = RegistrationOrder.class)
    @CheckLocalDateTime(beginTime = false, dateType = LOCAL_DATE, datePeriod = PAST, groups = RegistrationOrder.class)
    private String dateOfBirth;

    @IsNotNull(groups = Default.class)
    @Size(min = 3, max = 100, groups = RegistrationOrder.class)
    @Pattern(regexp = Patterns.SITE_NAME, groups = RegistrationOrder.class)
    private String siteUrl;

    public RegistrationDTO() {

    }

    public RegistrationDTO(String username, String password, String password2, String firstname, String lastname, String middlename, String email, String phone, String gender, String dateOfBirth, String siteUrl) {
        this.username = username;
        this.password = password;
        this.password2 = password2;
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.siteUrl = siteUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    @Override
    public String toString() {
        return "RegistrationDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", password2='" + password2 + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", middlename='" + middlename + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", siteUrl='" + siteUrl + '\'' +
                '}';
    }

    @Override
    public User convert() {
        return new User();
    }
}
package com.mtsmda.real.project.user.model;

import java.time.LocalDate;
import java.util.Set;

/**
 * Created by dminzat on 2/19/2017.
 */
public class User extends UserAbstract {

    private Account account;
    private Set<Role> roles;
    private Set<Privilege> privileges;
    private Group group;

    public User() {

    }

    public User(Integer userId) {
        super();
        this.setUserId(userId);
    }

    public User(String userFirstName, String userLastName, String userMiddleName, String userEmail, String userPhone, Gender userGender, LocalDate userDateOfBirth, Boolean userIsActive, String userSiteURL, Account account, Set<Role> roles, Set<Privilege> privileges, Group group) {
        super(userFirstName, userLastName, userMiddleName, userEmail, userPhone, userGender, userDateOfBirth, userIsActive, userSiteURL);
        this.account = account;
        this.roles = roles;
        this.privileges = privileges;
        this.group = group;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
package com.mtsmda.real.project.user.model;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by dminzat on 2/19/2017.
 */
public class Privilege implements Serializable{

    private Integer privilegeId;
    private String privilegeName;
    private Set<Role> roles;
    private Set<User> users;

    public Privilege() {

    }

    public Privilege(Integer privilegeId, String privilegeName) {
        this.privilegeId = privilegeId;
        this.privilegeName = privilegeName;
    }

    public Integer getPrivilegeId() {
        return privilegeId;
    }

    public Privilege setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
        return this;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public Privilege setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
        return this;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public Privilege setRoles(Set<Role> roles) {
        this.roles = roles;
        return this;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Privilege setUsers(Set<User> users) {
        this.users = users;
        return this;
    }
}
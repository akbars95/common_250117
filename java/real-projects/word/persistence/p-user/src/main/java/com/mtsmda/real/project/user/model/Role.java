package com.mtsmda.real.project.user.model;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by dminzat on 2/19/2017.
 */
public class Role implements Serializable{

    private Integer roleId;
    private String roleName;
    private Set<Privilege> privileges;
    private Set<User> users;
    private Set<Group> groups;

    public Role() {

    }

    public Role(Integer roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public Role setRoleId(Integer roleId) {
        this.roleId = roleId;
        return this;
    }

    public String getRoleName() {
        return roleName;
    }

    public Role setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public Role setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
        return this;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Role setUsers(Set<User> users) {
        this.users = users;
        return this;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public Role setGroups(Set<Group> groups) {
        this.groups = groups;
        return this;
    }
}
package com.mtsmda.real.project.user.model;

import java.util.Set;

/**
 * Created by dminzat on 3/11/2017.
 */
public class Group {

    private Integer groupId;
    private String groupName;
    private Set<Role> roles;
    private Set<User> users;

    public Group() {

    }

    public Group(Integer groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public Group setGroupId(Integer groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public Group setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public Group setRoles(Set<Role> roles) {
        this.roles = roles;
        return this;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Group setUsers(Set<User> users) {
        this.users = users;
        return this;
    }

}
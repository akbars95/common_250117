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

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
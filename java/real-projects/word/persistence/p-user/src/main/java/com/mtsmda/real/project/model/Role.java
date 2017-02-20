package com.mtsmda.real.project.model;

import java.io.Serializable;

/**
 * Created by dminzat on 2/19/2017.
 */
public class Role implements Serializable{

    private Integer roleId;
    private String roleName;

    public Role() {

    }

    public Role(Integer roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
package com.mtsmda.real.project.model;

import java.io.Serializable;

/**
 * Created by dminzat on 2/19/2017.
 */
public class Privilege implements Serializable{

    private Integer privilegeId;
    private String privilegeName;

    public Privilege() {

    }

    public Privilege(Integer privilegeId, String privilegeName) {
        this.privilegeId = privilegeId;
        this.privilegeName = privilegeName;
    }

    public Integer getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }
}
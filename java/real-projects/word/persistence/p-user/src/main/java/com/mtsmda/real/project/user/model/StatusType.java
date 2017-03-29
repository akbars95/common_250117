package com.mtsmda.real.project.user.model;

/**
 * Created by dminzat on 3/27/2017.
 */
public enum StatusType {

    CREATE_ACCOUNT_BUT_NOT_ACTIVE,
    CREATE_ACCOUNT_SENT_ACTIVATION_CODE,
    CREATE_ACCOUNT_ACTIVE;

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static void main(String[] args) {
        StatusType.CREATE_ACCOUNT_ACTIVE.setId("sadasd");
        System.out.println(StatusType.CREATE_ACCOUNT_ACTIVE.getId());
        System.out.println(StatusType.CREATE_ACCOUNT_SENT_ACTIVATION_CODE.getId());
    }

}
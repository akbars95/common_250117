package com.mtsmda.real.project.user.rowmapper;

/**
 * Created by dminzat on 3/2/2017.
 */
public class TableAndFieldsName {

    public static class RoleT{
        public static final String T_ROLES = "T_ROLES";
        public static final String T_ROLES_F_ROLE_ID = "ROLE_ID";
        public static final String T_ROLES_F_ROLE_NAME = "ROLE_NAME";
    }

    public static class PrivilegeT{
        public static final String T_PRIVILEGES = "T_PRIVILEGES";
        public static final String T_PRIVILEGES_F_PRIVILEGE_ID = "PRIVILEGE_ID";
        public static final String T_PRIVILEGES_F_PRIVILEGE_NAME = "PRIVILEGE_NAME";
    }

    public static class UserT{
        public static final String T_USERS = "T_USERS";
        public static final String T_USERS_F_USER_ID = "USER_ID";
        public static final String T_USERS_F_USER_FIRSTNAME = "USER_FIRSTNAME";
        public static final String T_USERS_F_USER_LASTNAME = "USER_LASTNAME";
        public static final String T_USERS_F_USER_MIDDLENAME = "USER_MIDDLENAME";
        public static final String T_USERS_F_USER_EMAIL = "USER_EMAIL";
        public static final String T_USERS_F_USER_PHONE = "USER_PHONE";
        public static final String T_USERS_F_USER_GENDER = "USER_GENDER";
        public static final String T_USERS_F_USER_DATE_OF_BIRTH = "USER_DATE_OF_BIRTH";
        public static final String T_USERS_F_USER_ACTIVE = "USER_ACTIVE";
        public static final String T_USERS_F_USER_SITE = "USER_SITE";
        public static final String T_USERS_F_ADD_USER_DATE_TIME = "ADD_USER_DATE_TIME";
    }

    public static class UserHistoryT{
        public static final String T_USERS_HISTORY = UserT.T_USERS + "_HISTORY";
        public static final String T_USERS_HISTORY_F_USER_ID = UserT.T_USERS_F_USER_ID;
        public static final String T_USERS_HISTORY_F_USER_FIRSTNAME = UserT.T_USERS_F_USER_FIRSTNAME;
        public static final String T_USERS_HISTORY_F_USER_LASTNAME = UserT.T_USERS_F_USER_LASTNAME;
        public static final String T_USERS_HISTORY_F_USER_MIDDLENAME = UserT.T_USERS_F_USER_MIDDLENAME;
        public static final String T_USERS_HISTORY_F_USER_EMAIL = UserT.T_USERS_F_USER_EMAIL;
        public static final String T_USERS_HISTORY_F_USER_PHONE = UserT.T_USERS_F_USER_PHONE;
        public static final String T_USERS_HISTORY_F_USER_GENDER = UserT.T_USERS_F_USER_GENDER;
        public static final String T_USERS_HISTORY_F_USER_DATE_OF_BIRTH = UserT.T_USERS_F_USER_DATE_OF_BIRTH;
        public static final String T_USERS_HISTORY_F_USER_ACTIVE = UserT.T_USERS_F_USER_ACTIVE;
        public static final String T_USERS_HISTORY_F_USER_SITE = UserT.T_USERS_F_USER_SITE;
        public static final String T_USERS_HISTORY_F_ADD_USER_DATE_TIME = UserT.T_USERS_F_ADD_USER_DATE_TIME;
    }

    public static class AccountT {
        public static final String T_ACCOUNTS = "T_ACCOUNTS";
        public static final String T_ACCOUNTS_F_ACCOUNT_USER_ID = "ACCOUNT_USER_ID";
        public static final String T_ACCOUNTS_F_ACCOUNT_USERNAME = "ACCOUNT_USERNAME";
        public static final String T_ACCOUNTS_F_ACCOUNT_PASSWORD = "ACCOUNT_PASSWORD";
        public static final String T_ACCOUNTS_F_ACCOUNT_NON_EXPIRED = "ACCOUNT_NON_EXPIRED";
        public static final String T_ACCOUNTS_F_ACCOUNT_NON_LOCKED = "ACCOUNT_NON_LOCKED";
        public static final String T_ACCOUNTS_F_CREDENTIALS_NON_EXPIRED = "CREDENTIALS_NON_EXPIRED";
        public static final String T_ACCOUNTS_F_USER_MAX_ATTEMPTS = "USER_MAX_ATTEMPTS";
    }

    public static class PasswordHistoryT {
        public static final String T_PASSWORD_HISTORY = "T_PASSWORD_HISTORY";
        public static final String T_PASSWORD_HISTORY_F_ACCOUNT_USER_ID = "ACCOUNT_USER_ID";
        public static final String T_PASSWORD_HISTORY_F_ACCOUNT_PASSWORD = "ACCOUNT_PASSWORD";
        public static final String T_PASSWORD_HISTORY_F_AC_PAS_CHANGE_DATE_TIME = "AC_PAS_CHANGE_DATE_TIME";
    }

    public static class UserAttemptT{
        public static final String T_USER_ATTEMPTS = "T_USER_ATTEMPTS";
        public static final String T_USER_ATTEMPTS_F_USER_ATTEMPT_ID = "USER_ATTEMPT_ID";
        public static final String T_USER_ATTEMPTS_F_ACCOUNT_USER_ID = "ACCOUNT_USER_ID";
        public static final String T_USER_ATTEMPTS_F_ATTEMPTS = "ATTEMPTS";
        public static final String T_USER_ATTEMPTS_F_LAST_MODIFIED = "LAST_MODIFIED";
    }

}
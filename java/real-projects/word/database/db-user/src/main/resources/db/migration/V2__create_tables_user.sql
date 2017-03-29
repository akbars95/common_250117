--CREATE TABLE
CREATE TABLE T_ROLES(
  ROLE_ID NUMBER(10,0) DEFAULT SEQ_ROLES.NEXTVAL NOT NULL,
  ROLE_NAME VARCHAR2(50 BYTE) NOT NULL ,
  CONSTRAINT ROLE_ID_PK PRIMARY KEY (ROLE_ID)
);
COMMENT ON TABLE T_ROLES IS 'This table store roles';
COMMENT ON COLUMN T_ROLES.ROLE_ID IS 'This column store role id, which generated via SEQ_ROLES';
COMMENT ON COLUMN T_ROLES.ROLE_NAME IS 'This column store role name';

CREATE TABLE T_PRIVILEGES(
  PRIVILEGE_ID NUMBER(10,0) DEFAULT SEQ_PRIVILEGES.NEXTVAL NOT NULL,
  PRIVILEGE_NAME VARCHAR2(50 BYTE) NOT NULL ,
  CONSTRAINT PRIVILEGE_ID_PK PRIMARY KEY (PRIVILEGE_ID)
);
COMMENT ON TABLE T_PRIVILEGES IS 'This table store privileges';
COMMENT ON COLUMN T_PRIVILEGES.PRIVILEGE_ID IS 'This column store privilege id, which generated via SEQ_PRIVILEGES';
COMMENT ON COLUMN T_PRIVILEGES.PRIVILEGE_NAME IS 'This column store privilege name';

CREATE TABLE T_ROLE_PRIVILEGES(
  ROLE_ID NUMBER(10,0) NOT NULL,
  PRIVILEGE_ID NUMBER(10,0) NOT NULL,
  CONSTRAINT RP_ROLE_ID_FK FOREIGN KEY (ROLE_ID) REFERENCES T_ROLES(ROLE_ID),
  CONSTRAINT RP_PRIVILEGE_ID_FK FOREIGN KEY (PRIVILEGE_ID) REFERENCES T_PRIVILEGES(PRIVILEGE_ID)
);
COMMENT ON TABLE T_ROLE_PRIVILEGES IS 'This table store reference role with privilege';
COMMENT ON COLUMN T_ROLE_PRIVILEGES.ROLE_ID IS 'This column store role id';
COMMENT ON COLUMN T_ROLE_PRIVILEGES.PRIVILEGE_ID IS 'This column store privilege id';

CREATE TABLE T_USERS(
  USER_ID NUMBER(10, 0) DEFAULT SEQ_USERS.NEXTVAL NOT NULL,
  USER_FIRSTNAME VARCHAR2(75 BYTE) NOT NULL,
  USER_LASTNAME VARCHAR2(75 BYTE) NOT NULL,
  USER_MIDDLENAME VARCHAR2(75 BYTE) NULL,
  USER_EMAIL VARCHAR2(128 BYTE) NOT NULL,
  USER_PHONE VARCHAR2(30 BYTE) NOT NULL,
  USER_GENDER VARCHAR2(1 BYTE) NOT NULL,
  USER_DATE_OF_BIRTH DATE NOT NULL,
  USER_ACTIVE VARCHAR2(1 BYTE) DEFAULT 'F' NOT NULL,
  USER_SITE VARCHAR2(100 BYTE) NULL,
  ADD_USER_DATE_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  CONSTRAINT USER_ID_FK PRIMARY KEY (USER_ID),
  CONSTRAINT USER_GENDER_CHECK CHECK (USER_GENDER IN('F', 'M')),
  CONSTRAINT USER_ACTIVE_CHECK CHECK (USER_ACTIVE IN('T', 'F'))
);
COMMENT ON TABLE T_USERS IS 'This table store users';
COMMENT ON COLUMN T_USERS.USER_ID IS 'This column store user id';
COMMENT ON COLUMN T_USERS.USER_FIRSTNAME IS 'This column store user first name';
COMMENT ON COLUMN T_USERS.USER_LASTNAME IS 'This column store user last name';
COMMENT ON COLUMN T_USERS.USER_MIDDLENAME IS 'This column store user middle name';
COMMENT ON COLUMN T_USERS.USER_EMAIL IS 'This column store user email';
COMMENT ON COLUMN T_USERS.USER_PHONE IS 'This column store user phone number';
COMMENT ON COLUMN T_USERS.USER_GENDER IS 'This column store user gender';
COMMENT ON COLUMN T_USERS.USER_DATE_OF_BIRTH IS 'This column store user date of birth';
COMMENT ON COLUMN T_USERS.USER_ACTIVE IS 'This column store user is active or not';
COMMENT ON COLUMN T_USERS.USER_SITE IS 'This column store user site url';
COMMENT ON COLUMN T_USERS.ADD_USER_DATE_TIME IS 'This column store user date and time when user registered';

CREATE TABLE T_USERS_HISTORY(
  USER_ID NUMBER(10, 0) NOT NULL,
  USER_FIRSTNAME VARCHAR2(75 BYTE) NOT NULL,
  USER_LASTNAME VARCHAR2(75 BYTE) NOT NULL,
  USER_MIDDLENAME VARCHAR2(75 BYTE) NULL,
  USER_EMAIL VARCHAR2(128 BYTE) NOT NULL,
  USER_PHONE VARCHAR2(30 BYTE) NOT NULL,
  USER_GENDER VARCHAR2(1 BYTE) NOT NULL,
  USER_DATE_OF_BIRTH DATE NOT NULL,
  USER_ACTIVE VARCHAR2(1 BYTE) NOT NULL,
  USER_SITE VARCHAR2(100 BYTE) NOT NULL,
  ADD_USER_DATE_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  CONSTRAINT UH_USER_ID_FK FOREIGN KEY (USER_ID) REFERENCES T_USERS(USER_ID),
  CONSTRAINT UH_USER_GENDER_CHECK CHECK (USER_GENDER IN('F', 'M')),
  CONSTRAINT UH_USER_ACTIVE_CHECK CHECK (USER_ACTIVE IN('T', 'F'))
);
COMMENT ON TABLE T_USERS_HISTORY IS 'This table store users history changes';
COMMENT ON COLUMN T_USERS_HISTORY.USER_ID IS 'This column store user id';
COMMENT ON COLUMN T_USERS_HISTORY.USER_FIRSTNAME IS 'This column store user first name';
COMMENT ON COLUMN T_USERS_HISTORY.USER_LASTNAME IS 'This column store user last name';
COMMENT ON COLUMN T_USERS_HISTORY.USER_MIDDLENAME IS 'This column store user middle name';
COMMENT ON COLUMN T_USERS_HISTORY.USER_EMAIL IS 'This column store user email';
COMMENT ON COLUMN T_USERS_HISTORY.USER_PHONE IS 'This column store user phone number';
COMMENT ON COLUMN T_USERS_HISTORY.USER_GENDER IS 'This column store user gender';
COMMENT ON COLUMN T_USERS_HISTORY.USER_DATE_OF_BIRTH IS 'This column store user date of birth';
COMMENT ON COLUMN T_USERS_HISTORY.USER_ACTIVE IS 'This column store user is active or not';
COMMENT ON COLUMN T_USERS_HISTORY.USER_SITE IS 'This column store user site url';
COMMENT ON COLUMN T_USERS.ADD_USER_DATE_TIME IS 'This column store user date and time when user changed';

CREATE TABLE T_ACCOUNTS(
  ACCOUNT_USER_ID NUMBER(10,0) NOT NULL,
  ACCOUNT_USERNAME VARCHAR2(50 BYTE) NOT NULL,
  ACCOUNT_PASSWORD VARCHAR2(256 BYTE) NOT NULL,
  ACCOUNT_NON_EXPIRED NUMBER(1,0) DEFAULT 1 NOT NULL,
  ACCOUNT_NON_LOCKED NUMBER(1,0) DEFAULT 1 NOT NULL,
  CREDENTIALS_NON_EXPIRED NUMBER(1,0) DEFAULT 1 NOT NULL,
  USER_MAX_ATTEMPTS NUMBER(2,0) DEFAULT 3 NOT NULL,
  COUNT_DAY_NEED_CHANGE_PASS NUMBER(3, 0) DEFAULT 90 NOT NULL,
  CONSTRAINT A_ACCOUNT_USER_ID_FK FOREIGN KEY (ACCOUNT_USER_ID) REFERENCES T_USERS(USER_ID),
  CONSTRAINT ACCOUNT_USER_ID_UNIQUE UNIQUE (ACCOUNT_USER_ID),
  CONSTRAINT ACCOUNT_USERNAME_UNIQUE UNIQUE (ACCOUNT_USERNAME),
  CONSTRAINT COUNT_DAY_NEED_CHANGE_PASS_CH CHECK(COUNT_DAY_NEED_CHANGE_PASS > 0 AND COUNT_DAY_NEED_CHANGE_PASS < 366)
);
COMMENT ON TABLE T_ACCOUNTS IS 'This table store users account info';
COMMENT ON COLUMN T_ACCOUNTS.ACCOUNT_USER_ID IS 'This column store user id';
COMMENT ON COLUMN T_ACCOUNTS.ACCOUNT_USERNAME IS 'This column store user account username';
COMMENT ON COLUMN T_ACCOUNTS.ACCOUNT_PASSWORD IS 'This column store user account password';
COMMENT ON COLUMN T_ACCOUNTS.ACCOUNT_NON_EXPIRED IS 'This column store user account expired state or not';
COMMENT ON COLUMN T_ACCOUNTS.ACCOUNT_NON_LOCKED IS 'This column store user account locked state or not';
COMMENT ON COLUMN T_ACCOUNTS.CREDENTIALS_NON_EXPIRED IS 'This column store user account credentials expired state or not';
COMMENT ON COLUMN T_ACCOUNTS.USER_MAX_ATTEMPTS IS 'This column store user account max attempts';
COMMENT ON COLUMN T_ACCOUNTS.COUNT_DAY_NEED_CHANGE_PASS IS 'This column store when user need to change account password or account has been locked';

CREATE TABLE T_PASSWORD_HISTORY(
  ACCOUNT_USER_ID NUMBER(10,0) NOT NULL,
  ACCOUNT_PASSWORD VARCHAR2(256 BYTE) NOT NULL,
  AC_PAS_CHANGE_DATE_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  CONSTRAINT PH_ACCOUNT_USER_ID_FK FOREIGN KEY (ACCOUNT_USER_ID) REFERENCES T_USERS(USER_ID)
);
COMMENT ON TABLE T_PASSWORD_HISTORY IS 'This table store users password history';
COMMENT ON COLUMN T_PASSWORD_HISTORY.ACCOUNT_USER_ID IS 'This column store user id';
COMMENT ON COLUMN T_PASSWORD_HISTORY.ACCOUNT_PASSWORD IS 'This column store user password, but not current, which last was';
COMMENT ON COLUMN T_PASSWORD_HISTORY.AC_PAS_CHANGE_DATE_TIME IS 'This column store user changed password date & time';

CREATE TABLE T_USER_ROLES(
  USER_ID NUMBER(10,0) NOT NULL,
  ROLE_ID NUMBER(10,0) NOT NULL,
  CONSTRAINT UR_USER_ID_FK FOREIGN KEY (USER_ID) REFERENCES T_USERS(USER_ID),
  CONSTRAINT UR_ROLE_ID_FK FOREIGN KEY (ROLE_ID) REFERENCES T_ROLES(ROLE_ID)
);
COMMENT ON TABLE T_USER_ROLES IS 'This table store reference user with role';
COMMENT ON COLUMN T_USER_ROLES.USER_ID IS 'This column store user id';
COMMENT ON COLUMN T_USER_ROLES.ROLE_ID IS 'This column store role id';

CREATE TABLE T_USER_PRIVILEGES(
  USER_ID NUMBER(10,0) NOT NULL,
  PRIVILEGE_ID NUMBER(10,0) NOT NULL,
  CONSTRAINT UP_USER_ID_FK FOREIGN KEY (USER_ID) REFERENCES T_USERS(USER_ID),
  CONSTRAINT UP_PRIVILEGE_ID_FK FOREIGN KEY (PRIVILEGE_ID) REFERENCES T_PRIVILEGES(PRIVILEGE_ID)
);
COMMENT ON TABLE T_USER_PRIVILEGES IS 'This table store reference user with privilege';
COMMENT ON COLUMN T_USER_PRIVILEGES.USER_ID IS 'This column store user id';
COMMENT ON COLUMN T_USER_PRIVILEGES.PRIVILEGE_ID IS 'This column store privilege id';

CREATE TABLE T_USER_ATTEMPTS(
  USER_ATTEMPT_ID NUMBER(10,0) DEFAULT SEQ_USER_ATTEMPTS.NEXTVAL NOT NULL,
  ACCOUNT_USER_ID NUMBER(10,0) NOT NULL,
  ATTEMPTS NUMBER(3,0) DEFAULT 0 NOT NULL,
  LAST_MODIFIED TIMESTAMP NULL,
  CONSTRAINT USER_ATTEMPT_ID_FK PRIMARY KEY (USER_ATTEMPT_ID),
  CONSTRAINT UA_ACCOUNT_USER_ID_FK FOREIGN KEY (ACCOUNT_USER_ID) REFERENCES T_ACCOUNTS(ACCOUNT_USER_ID)
);
COMMENT ON TABLE T_USER_ATTEMPTS IS 'This table store user attempts';
COMMENT ON COLUMN T_USER_ATTEMPTS.USER_ATTEMPT_ID IS 'This column store user attempts id';
COMMENT ON COLUMN T_USER_ATTEMPTS.ACCOUNT_USER_ID IS 'This column store user account id';
COMMENT ON COLUMN T_USER_ATTEMPTS.ATTEMPTS IS 'This column store attempt count';
COMMENT ON COLUMN T_USER_ATTEMPTS.LAST_MODIFIED IS 'This column store last attempt date time';

CREATE TABLE T_GROUPS(
  GROUP_ID NUMBER(10,0) DEFAULT SEQ_GROUPS.NEXTVAL NOT NULL,
  GROUP_NAME VARCHAR2(50 BYTE) NOT NULL,
  CONSTRAINT GROUP_ID_FK PRIMARY KEY (GROUP_ID),
  CONSTRAINT GROUP_NAME_UNIQUE UNIQUE (GROUP_NAME)
);
COMMENT ON TABLE T_GROUPS IS 'This table store group names';
COMMENT ON COLUMN T_GROUPS.GROUP_ID IS 'This column store group id';
COMMENT ON COLUMN T_GROUPS.GROUP_NAME IS 'This column store group name';

CREATE TABLE T_GROUP_ROLES(
  GROUP_ID NUMBER(10,0) NOT NULL,
  ROLE_ID NUMBER(10,0) NOT NULL,
  CONSTRAINT GR_GROUP_ID_FK FOREIGN KEY (GROUP_ID) REFERENCES T_GROUPS(GROUP_ID),
  CONSTRAINT GR_ROLE_ID_FK FOREIGN KEY (ROLE_ID) REFERENCES T_ROLES(ROLE_ID)
);
COMMENT ON TABLE T_GROUP_ROLES IS 'This table store join group with role';
COMMENT ON COLUMN T_GROUP_ROLES.GROUP_ID IS 'This column store group id';
COMMENT ON COLUMN T_GROUP_ROLES.ROLE_ID IS 'This column store role id';

CREATE TABLE T_GROUP_USERS(
  GROUP_ID NUMBER(10,0) NOT NULL,
  USER_ID NUMBER(10,0) NOT NULL,
  CONSTRAINT GU_GROUP_ID_FK FOREIGN KEY (GROUP_ID) REFERENCES T_GROUPS(GROUP_ID),
  CONSTRAINT GU_USER_ID_FK FOREIGN KEY (USER_ID) REFERENCES T_USERS(USER_ID)
);
COMMENT ON TABLE T_GROUP_USERS IS 'This table store join group with user';
COMMENT ON COLUMN T_GROUP_USERS.GROUP_ID IS 'This column store group id';
COMMENT ON COLUMN T_GROUP_USERS.USER_ID IS 'This column store user id';

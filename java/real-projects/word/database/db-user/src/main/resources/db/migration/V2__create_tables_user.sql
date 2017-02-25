--CREATE TABLE
CREATE TABLE T_ROLES(
  ROLE_ID NUMBER(10,0) DEFAULT SEQ_ROLES.NEXTVAL NOT NULL,
  ROLE_NAME VARCHAR2(50 BYTE) NOT NULL ,
  CONSTRAINT ROLE_ID_PK PRIMARY KEY (ROLE_ID)
);

CREATE TABLE T_PRIVILEGES(
  PRIVILEGE_ID NUMBER(10,0) DEFAULT SEQ_PRIVILEGES.NEXTVAL NOT NULL,
  PRIVILEGE_NAME VARCHAR2(50 BYTE) NOT NULL ,
  CONSTRAINT PRIVILEGE_ID_PK PRIMARY KEY (PRIVILEGE_ID)
);

CREATE TABLE T_ROLE_PRIVILEGES(
  ROLE_ID NUMBER(10,0) NOT NULL,
  PRIVILEGE_ID NUMBER(10,0) NOT NULL,
  CONSTRAINT RP_ROLE_ID_FK FOREIGN KEY (ROLE_ID) REFERENCES T_ROLES(ROLE_ID),
  CONSTRAINT RP_PRIVILEGE_ID_FK FOREIGN KEY (PRIVILEGE_ID) REFERENCES T_PRIVILEGES(PRIVILEGE_ID)
);

CREATE TABLE T_USERS(
  USER_ID NUMBER(10, 0) DEFAULT SEQ_USERS.NEXTVAL NOT NULL,
  USER_FIRSTNAME VARCHAR2(75 BYTE) NOT NULL,
  USER_LASTNAME VARCHAR2(75 BYTE) NOT NULL,
  USER_MIDDLENAME VARCHAR2(75 BYTE) NULL,
  USER_EMAIL VARCHAR2(128 BYTE) NOT NULL,
  USER_PHONE VARCHAR2(30 BYTE) NOT NULL,
  USER_GENDER VARCHAR2(1 BYTE) NOT NULL,
  USER_DATE_OF_BIRTH DATE NOT NULL,
  USER_ACTIVE VARCHAR2(1 BYTE) NOT NULL,
  USER_SITE VARCHAR2(100 BYTE)  NULL,
  ADD_USER_DATE_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  CONSTRAINT USER_ID_FK PRIMARY KEY (USER_ID),
  CONSTRAINT USER_GENDER_CHECK CHECK (USER_GENDER IN('F', 'M')),
  CONSTRAINT USER_ACTIVE_CHECK CHECK (USER_ACTIVE IN('T', 'F'))
);

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
  CONSTRAINT UH_USER_ID_FK FOREIGN KEY (USER_ID) REFERENCES T_USERS(USER_ID),
  CONSTRAINT UH_USER_GENDER_CHECK CHECK (USER_GENDER IN('F', 'M')),
  CONSTRAINT UH_USER_ACTIVE_CHECK CHECK (USER_ACTIVE IN('T', 'F'))
);

CREATE TABLE T_ACCOUNTS(
  ACCOUNT_USER_ID NUMBER(10,0) NOT NULL,
  ACCOUNT_USERNAME VARCHAR2(50 BYTE) NOT NULL,
  ACCOUNT_PASSWORD VARCHAR2(256 BYTE) NOT NULL,
  CONSTRAINT A_ACCOUNT_USER_ID_FK FOREIGN KEY (ACCOUNT_USER_ID) REFERENCES T_USERS(USER_ID)
);

CREATE TABLE T_PASSWORD_HISTORY(
  ACCOUNT_USER_ID NUMBER(10,0) NOT NULL,
  ACCOUNT_PASSWORD VARCHAR2(256 BYTE) NOT NULL,
  CONSTRAINT PH_ACCOUNT_USER_ID_FK FOREIGN KEY (ACCOUNT_USER_ID) REFERENCES T_USERS(USER_ID)
);

CREATE TABLE T_USER_ROLES(
  USER_ID NUMBER(10,0) NOT NULL,
  ROLE_ID NUMBER(10,0) NOT NULL,
  CONSTRAINT UR_USER_ID_FK FOREIGN KEY (USER_ID) REFERENCES T_USERS(USER_ID),
  CONSTRAINT UR_ROLE_ID_FK FOREIGN KEY (ROLE_ID) REFERENCES T_ROLES(ROLE_ID)
);

CREATE TABLE T_USER_PRIVILEGES(
  USER_ID NUMBER(10,0) NOT NULL,
  PRIVILEGE_ID NUMBER(10,0) NOT NULL,
  CONSTRAINT UP_USER_ID_FK FOREIGN KEY (USER_ID) REFERENCES T_USERS(USER_ID),
  CONSTRAINT UP_PRIVILEGE_ID_FK FOREIGN KEY (PRIVILEGE_ID) REFERENCES T_PRIVILEGES(PRIVILEGE_ID)
);

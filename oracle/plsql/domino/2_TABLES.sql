---------------------------------------------------------------------------SEQUENCES
PROMPT--*****************SEQUENCES***********************
DROP SEQUENCE SEQ_DOMINO;
DROP SEQUENCE SEQ_GAME_ATTENDEE;
DROP SEQUENCE SEQ_USER;
DROP SEQUENCE SEQ_GAME;

CREATE SEQUENCE SEQ_DOMINO MINVALUE 1 MAXVALUE 36 START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE SEQ_USER MINVALUE 1 MAXVALUE 99999999999 START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE SEQ_GAME MINVALUE 1 MAXVALUE 99999999999 START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE SEQ_GAME_ATTENDEE MINVALUE 1 MAXVALUE 99999999999 START WITH 1 INCREMENT BY 1 NOCACHE;

---------------------------------------------------------------------------TABLES
PROMPT--*****************TABLES***********************
DROP TABLE T_GAME_USER_DOMINOS;
DROP TABLE T_GAME_PROGRESS;
DROP TABLE T_GAME_RESULTS;
DROP TABLE T_GAMES_ATTENDEES;
DROP TABLE T_GAMES;
DROP TABLE T_DOMINOS;
DROP TABLE T_ACCOUNTS;
DROP TABLE T_USERS;

CREATE TABLE T_DOMINOS(
  DOMINO_ID NUMBER(10,0) DEFAULT SEQ_DOMINO.NEXTVAL NOT NULL,
  DOMINO_LEFT_PIECE NUMBER(1,0) NOT NULL,
  DOMINO_RIGHT_PIECE NUMBER(1,0) NOT NULL,
  CONSTRAINT DOMINO_ID_PK PRIMARY KEY (DOMINO_ID),
  CONSTRAINT DOMINO_VALUE_UNIQUE UNIQUE (DOMINO_LEFT_PIECE,DOMINO_RIGHT_PIECE),
  CONSTRAINT DOMINO_LEFT_PIECE_CHECK CHECK (DOMINO_LEFT_PIECE BETWEEN 0 AND 6),
  CONSTRAINT DOMINO_RIGHT_PIECE_CHECK CHECK (DOMINO_RIGHT_PIECE BETWEEN 0 AND 6)
);

CREATE TABLE T_USERS(
    USER_ID NUMBER(10,0) DEFAULT SEQ_USER.NEXTVAL NOT NULL,
    USER_FIRST_NAME VARCHAR(75) NOT NULL,
    USER_LAST_NAME VARCHAR(75) NOT NULL,
    USER_MIDDLE_NAME VARCHAR(75) NULL,
    USER_DATE_OF_BIRTH DATE NOT NULL,
    USER_GENDER VARCHAR(1) DEFAULT 'M' NOT NULL,
    CONSTRAINT USER_ID_PK PRIMARY KEY (USER_ID),
    CONSTRAINT USER_UNIQUE UNIQUE (USER_FIRST_NAME,USER_LAST_NAME, USER_MIDDLE_NAME, USER_DATE_OF_BIRTH, USER_GENDER),
    CONSTRAINT USER_GENDER_CHECK CHECK (USER_GENDER IN('F','M'))
);

ALTER TABLE T_USERS
ADD USER_EMAIL VARCHAR(128) NOT NULL;
ALTER TABLE T_USERS
ADD CONSTRAINT USER_EMAIL_UNIQUE UNIQUE (USER_EMAIL);

CREATE TABLE T_ACCOUNTS(
    ACCOUNT_USER_ID NUMBER(10,0) NOT NULL,
    ACCOUNT_USERNAME VARCHAR(50) NOT NULL,
    ACCOUNT_PASSWORD VARCHAR(256) NOT NULL,
    CONSTRAINT T_A_ACCOUNT_USER_ID_FK FOREIGN KEY (ACCOUNT_USER_ID) REFERENCES T_USERS(USER_ID),
    CONSTRAINT USER_ACCOUNT_UNIQUE UNIQUE (ACCOUNT_USER_ID,ACCOUNT_USERNAME, ACCOUNT_PASSWORD)
);

CREATE TABLE T_GAMES(
  GAME_ID NUMBER(10,0) DEFAULT SEQ_GAME.NEXTVAL NOT NULL,
  GAME_NAME VARCHAR(50) NOT NULL,
  CREATED_BY_USER_ID NUMBER(10,0) NOT NULL,
  CREATED_TIMESTAMP DATE NOT NULL,
  START_TIMESTAMP DATE NULL,
  END_TIMESTAMP DATE NULL,
  CONSTRAINT GAME_ID_PK PRIMARY KEY (GAME_ID),
  CONSTRAINT GAME_NAME_UNIQUE UNIQUE (GAME_NAME),
  CONSTRAINT T_G_CREATED_BY_USER_ID_FK FOREIGN KEY (CREATED_BY_USER_ID) REFERENCES T_USERS(USER_ID)
);

CREATE TABLE T_GAMES_ATTENDEES(
  GAME_ATTENDEE_ID NUMBER(10,0) DEFAULT SEQ_GAME_ATTENDEE.NEXTVAL NOT NULL,
  GAME_ID NUMBER(10,0) NOT NULL,
  USER_ID NUMBER(10,0) NOT NULL,
  CONSTRAINT GAME_ATTENDEE_ID_PK PRIMARY KEY (GAME_ATTENDEE_ID),
  CONSTRAINT T_GT_GAME_ID_FK FOREIGN KEY (GAME_ID) REFERENCES T_GAMES(GAME_ID),
  CONSTRAINT T_GT_USER_ID_FK FOREIGN KEY (USER_ID) REFERENCES T_USERS(USER_ID)
);

ALTER TABLE T_GAMES_ATTENDEES
ADD CONSTRAINT GAME_ATTENDEE_UNIQUE UNIQUE(GAME_ID, USER_ID);

CREATE TABLE T_GAME_RESULTS(
  GAME_ATTENDEE_ID NUMBER(10,0) NOT NULL,
  COUNT_OF_SCORES NUMBER(3,0) NOT NULL,
  CONSTRAINT T_GR_GAME_ATTENDEE_ID_FK FOREIGN KEY (GAME_ATTENDEE_ID) REFERENCES T_GAMES_ATTENDEES(GAME_ATTENDEE_ID)
);

CREATE TABLE T_GAME_PROGRESS(
  GAME_ATTENDEE_ID NUMBER(10,0) NOT NULL,
  GAME_PROGRESS_TIMESTAMP DATE NOT NULL,
  DOMINO_ID NUMBER(10,0) NOT NULL,
  CONSTRAINT T_GP_GAME_ATTENDEE_ID_FK FOREIGN KEY (GAME_ATTENDEE_ID) REFERENCES T_GAMES_ATTENDEES(GAME_ATTENDEE_ID),
  CONSTRAINT T_GP_DOMINO_ID_FK FOREIGN KEY (DOMINO_ID) REFERENCES T_DOMINOS(DOMINO_ID)
);

CREATE TABLE T_GAME_USER_DOMINOS(
  GAME_ATTENDEE_ID NUMBER(10,0) NOT NULL,
  DOMINO_ID NUMBER(10,0) NOT NULL,
  CONSTRAINT T_GUD_GAME_ATTENDEE_ID_FK FOREIGN KEY (GAME_ATTENDEE_ID) REFERENCES T_GAMES_ATTENDEES(GAME_ATTENDEE_ID),
  CONSTRAINT T_GUD_DOMINO_ID_FK FOREIGN KEY (DOMINO_ID) REFERENCES T_DOMINOS(DOMINO_ID)
);

ALTER TABLE T_GAME_USER_DOMINOS
ADD ACTIVE_DOMINO INTEGER DEFAULT 0 NOT NULL;

---------------------------------------------------------------------------VIEWS
PROMPT--*****************VIEWS***********************
DROP VIEW V_DOMINOS;
CREATE OR REPLACE VIEW V_DOMINOS AS
  SELECT * FROM T_DOMINOS;
  
DROP VIEW V_USERS;
CREATE OR REPLACE VIEW V_USERS AS
  SELECT * FROM T_USERS;
  
DROP VIEW V_ACCOUNTS;
CREATE OR REPLACE VIEW V_ACCOUNTS AS
  SELECT * FROM T_ACCOUNTS;

DROP VIEW V_GAMES;
CREATE OR REPLACE VIEW V_GAMES AS
  SELECT * FROM T_GAMES;
  
DROP VIEW V_GAMES_ATTENDEES;
CREATE OR REPLACE VIEW V_GAMES_ATTENDEES AS
  SELECT * FROM T_GAMES_ATTENDEES;

DROP VIEW V_GAME_RESULTS;
CREATE OR REPLACE VIEW V_GAME_RESULTS AS
  SELECT * FROM T_GAME_RESULTS;
  
DROP VIEW V_GAME_PROGRESS;
CREATE OR REPLACE VIEW V_GAME_PROGRESS AS
  SELECT * FROM T_GAME_PROGRESS;
  
DROP VIEW V_GAME_USER_DOMINOS;
CREATE OR REPLACE VIEW V_GAME_USER_DOMINOS AS
  SELECT * FROM T_GAME_USER_DOMINOS;
  
DROP VIEW V_USER_ACCOUNTS;
CREATE OR REPLACE VIEW V_USER_ACCOUNTS AS
  select U.*, A.ACCOUNT_USERNAME, A.ACCOUNT_PASSWORD
  FROM T_USERS U INNER JOIN T_ACCOUNTS A ON U.USER_ID = A.ACCOUNT_USER_ID;
  
DROP VIEW V_USERS_CREATED_GAMES;
CREATE OR REPLACE VIEW V_USERS_CREATED_GAMES AS
  SELECT G.*, U.USER_FIRST_NAME, U.USER_LAST_NAME, U.USER_EMAIL
  FROM T_GAMES G INNER JOIN T_USERS U ON G.CREATED_BY_USER_ID = U.USER_ID;

DROP VIEW V_ALL_CREATED_GAMES;
CREATE OR REPLACE VIEW V_ALL_CREATED_GAMES AS
  SELECT G.*
  FROM T_GAMES G
  WHERE G.CREATED_TIMESTAMP IS NOT NULL;

DROP VIEW V_ALL_STARTED_GAMES;
CREATE OR REPLACE VIEW V_ALL_STARTED_GAMES AS
  SELECT G.*
  FROM T_GAMES G
  WHERE G.CREATED_TIMESTAMP IS NOT NULL AND G.START_TIMESTAMP IS NOT NULL;
  
DROP VIEW V_ALL_END_GAMES;
CREATE OR REPLACE VIEW V_ALL_END_GAMES AS
  SELECT G.*
  FROM T_GAMES G
  WHERE G.CREATED_TIMESTAMP IS NOT NULL AND G.START_TIMESTAMP IS NOT NULL AND G.END_TIMESTAMP IS NOT NULL;
  
DROP VIEW V_GAME_ATTENDEES_USERS;
CREATE OR REPLACE VIEW V_GAME_ATTENDEES_USERS AS
  SELECT GA.GAME_ATTENDEE_ID, G.GAME_NAME, U.USER_FIRST_NAME || ' ' || U.USER_LAST_NAME AS USER_FL, A.ACCOUNT_USERNAME
  FROM T_GAMES_ATTENDEES GA INNER JOIN T_GAMES G ON GA.GAME_ID = G.GAME_ID
  INNER JOIN T_USERS U ON U.USER_ID = GA.USER_ID
  INNER JOIN T_ACCOUNTS A ON A.ACCOUNT_USER_ID = GA.USER_ID;

---------------------------------------------------------------------------MATERIALIZED VIEWS
PROMPT--*****************MATERIALIZED VIEWS***********************
DROP MATERIALIZED VIEW MV_DOMINOS;
CREATE MATERIALIZED VIEW MV_DOMINOS
BUILD IMMEDIATE
REFRESH
COMPLETE
ON COMMIT
AS
  SELECT * FROM T_DOMINOS;
  

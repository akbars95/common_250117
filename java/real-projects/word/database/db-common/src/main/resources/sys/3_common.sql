PROMPT---DROP ROLE ROLE_COMMON_DB;
--DROP ROLE
DROP ROLE ROLE_COMMON_DB;

PROMPT ----------------DROP USER USER_WORD_DB;
--DROP USER
DROP USER USER_WORD_DB CASCADE;
DROP USER USER_USER_DB CASCADE;
DROP USER USER_COMMON_DB CASCADE;

PROMPT -----------------DROP PROFILE PR_USER_WORD_DB;
--DROP PROFILE
DROP PROFILE PR_USER_WORD_DB;


/*
PROMPT DROP TABLESPACE TEMP_TBL_USER_WORD_DB;
DROP TABLESPACE TEMP_TBL_USER_WORD_DB;
--DROP TABLESPACE
DROP TABLESPACE TEMP_TBL_USER_WORD_DB
INCLUDING CONTENTS CASCADE CONSTRAINTS;

PROMPT DROP TABLESPACE TBL_USER_WORD_DB;
DROP TABLESPACE TBL_USER_WORD_DB
INCLUDING CONTENTS CASCADE CONSTRAINTS;
*/
SHOW ERRORS;


PROMPT -------------CREATE TABLESPACE TBL_USER_WORD_DB-------------;
--CREATE TABLESPACE

/*
CREATE TABLESPACE TBL_USER_WORD_DB
DATAFILE '${project.tablespace.path}' SIZE 500M REUSE
AUTOEXTEND ON NEXT 5M MAXSIZE 750M;

PROMPT -------------CREATE TEMPORARY TABLESPACE TEMP_TBL_USER_WORLD_DB-------------;
CREATE TEMPORARY TABLESPACE TEMP_TBL_USER_WORD_DB
TEMPFILE '${project.temp.tablespace.path}' SIZE 32M
AUTOEXTEND ON NEXT 32M MAXSIZE 512M;
*/

--CREATE USER
PROMPT ---------CREATE USER USER_WORD_DB-------------;
CREATE USER USER_WORD_DB
IDENTIFIED BY USER_WORD_DB_999
DEFAULT TABLESPACE TBL_USER_WORD_DB --QUOTA 100M ON TBL_USER_WORLD_DB
TEMPORARY TABLESPACE TEMP_TBL_USER_WORD_DB; --QUOTA 10M ON TEMP_TBL_USER_WORLD_DB;

--ALTER USER
ALTER USER USER_WORD_DB IDENTIFIED BY USER_WORD_DB_999_N;
ALTER USER USER_WORD_DB QUOTA 100M ON TBL_USER_WORD_DB;

PROMPT ---------CREATE USER USER_USER_DB-------------;
CREATE USER USER_USER_DB
IDENTIFIED BY USER_USER_DB_999
DEFAULT TABLESPACE TBL_USER_WORD_DB
TEMPORARY TABLESPACE TEMP_TBL_USER_WORD_DB;

ALTER USER USER_USER_DB QUOTA 100M ON TBL_USER_WORD_DB;

PROMPT ---------CREATE USER USER_COMMON_DB-------------;
CREATE USER USER_COMMON_DB
IDENTIFIED BY USER_COMMON_DB_999
DEFAULT TABLESPACE TBL_USER_WORD_DB
TEMPORARY TABLESPACE TEMP_TBL_USER_WORD_DB;

ALTER USER USER_COMMON_DB QUOTA 100M ON TBL_USER_WORD_DB;

PROMPT -------------CREATE PROFILE-------------;
--CREATE PROFILE
CREATE PROFILE PR_USER_WORD_DB LIMIT PASSWORD_LIFE_TIME 180;

--ALTER USER ADD PROFILE
ALTER USER USER_WORD_DB PROFILE PR_USER_WORD_DB;
ALTER USER USER_USER_DB PROFILE PR_USER_WORD_DB;
ALTER USER USER_COMMON_DB PROFILE PR_USER_WORD_DB;

PROMPT -------------CREATE ROLE-------------;
--CREATE ROLE
CREATE ROLE ROLE_COMMON_DB;

--assign user role
GRANT ROLE_COMMON_DB TO USER_WORD_DB;
GRANT ROLE_COMMON_DB TO USER_USER_DB;
GRANT ROLE_COMMON_DB TO USER_COMMON_DB;
GRANT UNLIMITED TABLESPACE TO USER_WORD_DB;
GRANT UNLIMITED TABLESPACE TO ROLE_COMMON_DB;

PROMPT -------------ADD GRANT PRIVILEGES-------------;
GRANT CREATE ANY PROCEDURE TO ROLE_COMMON_DB;
GRANT CREATE ANY SEQUENCE TO ROLE_COMMON_DB;
GRANT CREATE ANY SYNONYM TO ROLE_COMMON_DB;
GRANT CREATE ANY TABLE TO ROLE_COMMON_DB;
GRANT CREATE ANY TRIGGER TO ROLE_COMMON_DB;
GRANT CREATE ANY TYPE TO ROLE_COMMON_DB;
GRANT CREATE ANY VIEW TO ROLE_COMMON_DB;
GRANT CREATE ANY INDEX TO ROLE_COMMON_DB;

GRANT CREATE SESSION TO ROLE_COMMON_DB;
GRANT CREATE TABLE TO ROLE_COMMON_DB;
GRANT CREATE PROCEDURE TO ROLE_COMMON_DB;
GRANT CREATE TRIGGER TO ROLE_COMMON_DB;
GRANT CREATE VIEW TO ROLE_COMMON_DB;
GRANT CREATE ROLE TO ROLE_COMMON_DB;
GRANT CREATE SEQUENCE TO ROLE_COMMON_DB;
GRANT CREATE TABLESPACE TO ROLE_COMMON_DB;
GRANT CREATE SYNONYM TO ROLE_COMMON_DB;
GRANT CREATE PUBLIC SYNONYM TO ROLE_COMMON_DB;

GRANT ALTER ANY TABLE TO ROLE_COMMON_DB;
GRANT ALTER ANY PROCEDURE TO ROLE_COMMON_DB;
GRANT ALTER ANY TRIGGER TO ROLE_COMMON_DB;
GRANT ALTER ANY ROLE TO ROLE_COMMON_DB;
GRANT ALTER ANY SEQUENCE TO ROLE_COMMON_DB;
GRANT ALTER ANY INDEX TO ROLE_COMMON_DB;
GRANT ALTER PROFILE TO ROLE_COMMON_DB;
GRANT ALTER SESSION TO ROLE_COMMON_DB;
GRANT ALTER DATABASE TO ROLE_COMMON_DB;
GRANT ALTER USER TO ROLE_COMMON_DB;
GRANT ALTER TABLESPACE TO ROLE_COMMON_DB;

GRANT DELETE ANY TABLE TO ROLE_COMMON_DB;
GRANT DROP ANY TABLE TO ROLE_COMMON_DB;
GRANT DROP ANY PROCEDURE TO ROLE_COMMON_DB;
GRANT DROP ANY SEQUENCE TO ROLE_COMMON_DB;
GRANT DROP ANY TRIGGER TO ROLE_COMMON_DB;
GRANT DROP ANY VIEW TO ROLE_COMMON_DB;
GRANT DROP ANY INDEX TO ROLE_COMMON_DB;
GRANT DROP PROFILE TO ROLE_COMMON_DB;
GRANT DROP ANY ROLE TO ROLE_COMMON_DB;
GRANT DROP TABLESPACE TO ROLE_COMMON_DB;
GRANT DROP ANY SYNONYM TO ROLE_COMMON_DB;
GRANT DROP PUBLIC SYNONYM TO ROLE_COMMON_DB;

GRANT EXECUTE ANY PROCEDURE TO ROLE_COMMON_DB;
GRANT GRANT ANY PRIVILEGE TO ROLE_COMMON_DB;
GRANT GRANT ANY ROLE TO ROLE_COMMON_DB;
GRANT INSERT ANY TABLE TO ROLE_COMMON_DB;
GRANT LOCK ANY TABLE TO ROLE_COMMON_DB;
GRANT SELECT ANY TABLE TO ROLE_COMMON_DB;
GRANT SELECT ANY SEQUENCE TO ROLE_COMMON_DB;
GRANT MANAGE TABLESPACE TO ROLE_COMMON_DB;

GRANT CREATE JOB TO ROLE_COMMON_DB;
GRANT CREATE ANY JOB TO ROLE_COMMON_DB;

--see all sys privileges
/*
select * from SYS.USER_SYS_PRIVS USP
WHERE USP.PRIVILEGE LIKE '%TABLESP%';

select * from SYS.USER_SYS_PRIVS USP
WHERE USP.PRIVILEGE LIKE '%SYN%';
*/

/*
select * from SYS.USER_SYS_PRIVS USP
WHERE USP.PRIVILEGE LIKE '%REF%';
*/

SHOW ERRORS;
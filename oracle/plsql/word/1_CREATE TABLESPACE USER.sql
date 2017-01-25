PROMPT -------------CREATE TABLESPACE TBL_USER_WORLD_DB-------------;
--CREATE TABLESPACE
/*
CREATE TABLESPACE TBL_USER_WORLD_DB
DATAFILE 'D:\work\TBL_USER_WORLD_DB.DBF' SIZE 500M REUSE
AUTOEXTEND ON NEXT 5M MAXSIZE 750M;
*/

PROMPT -------------CREATE TEMPORARY TABLESPACE TEMP_TBL_USER_WORLD_DB-------------;
CREATE TEMPORARY TABLESPACE TEMP_TBL_USER_WORLD_DB
TEMPFILE 'D:\work\TEMP_TBL_USER_WORLD_DB.DBF' SIZE 32M
AUTOEXTEND ON NEXT 32M MAXSIZE 512M;

--CREATE USER
PROMPT ---------CREATE USER-------------;
CREATE USER USER_WORLD_DB 
IDENTIFIED BY USER_WORLD_DB_999
DEFAULT TABLESPACE TBL_USER_WORLD_DB --QUOTA 100M ON TBL_USER_WORLD_DB
TEMPORARY TABLESPACE TEMP_TBL_USER_WORLD_DB; --QUOTA 10M ON TEMP_TBL_USER_WORLD_DB;

--ALTER USER
ALTER USER USER_WORLD_DB IDENTIFIED BY USER_WORLD_DB_999_N;

PROMPT -------------CREATE PROFILE-------------;
--CREATE PROFILE 
CREATE PROFILE PR_USER_WORLD_DB LIMIT PASSWORD_LIFE_TIME 180;

--ALTER USER ADD PROFILE
ALTER USER USER_WORLD_DB PROFILE PR_USER_WORLD_DB;
ALTER USER USER_WORLD_DB QUOTA 100M ON TBL_USER_WORLD_DB;

ALTER USER USER_WORLD_DB ACCOUNT LOCK;
ALTER USER USER_WORLD_DB ACCOUNT UNLOCK;

/*SELECT *
FROM dba_data_files;

select *
from dba_users;
*/
----------------------------------
/*GRANT CREATE SESSION TO USER_WORLD_DB;
GRANT CREATE TABLE TO USER_WORLD_DB;
GRANT CREATE PROCEDURE TO USER_WORLD_DB;
GRANT CREATE TRIGGER TO USER_WORLD_DB;
GRANT CREATE VIEW TO USER_WORLD_DB;
GRANT CREATE SEQUENCE TO USER_WORLD_DB;

GRANT ALTER ANY TABLE TO USER_WORLD_DB;
GRANT ALTER ANY PROCEDURE TO USER_WORLD_DB;
GRANT ALTER ANY TRIGGER TO USER_WORLD_DB;
GRANT ALTER PROFILE TO USER_WORLD_DB;

GRANT DELETE ANY TABLE TO USER_WORLD_DB;
GRANT DROP ANY TABLE TO USER_WORLD_DB;
GRANT DROP ANY PROCEDURE TO USER_WORLD_DB;
GRANT DROP ANY TRIGGER TO USER_WORLD_DB;
GRANT DROP ANY VIEW TO USER_WORLD_DB;
GRANT DROP PROFILE TO USER_WORLD_DB;
*/
PROMPT -------------CREATE ROLE-------------;
--CREATE ROLE
CREATE ROLE ROLE_USER_WORLD_DB;
GRANT ROLE_USER_WORLD_DB TO USER_WORLD_DB;
GRANT UNLIMITED TABLESPACE TO USER_WORLD_DB;

PROMPT -------------ADD GRANT PRIVILEGES-------------;
GRANT CREATE ANY PROCEDURE TO ROLE_USER_WORLD_DB;
GRANT CREATE ANY SEQUENCE TO ROLE_USER_WORLD_DB;
GRANT CREATE ANY SYNONYM TO ROLE_USER_WORLD_DB;
GRANT CREATE ANY TABLE TO ROLE_USER_WORLD_DB;
GRANT CREATE ANY TRIGGER TO ROLE_USER_WORLD_DB;
GRANT CREATE ANY TYPE TO ROLE_USER_WORLD_DB;
GRANT CREATE ANY VIEW TO ROLE_USER_WORLD_DB;
GRANT CREATE ANY INDEX TO ROLE_USER_WORLD_DB;

GRANT CREATE SESSION TO ROLE_USER_WORLD_DB;
GRANT CREATE TABLE TO ROLE_USER_WORLD_DB;
GRANT CREATE PROCEDURE TO ROLE_USER_WORLD_DB;
GRANT CREATE TRIGGER TO ROLE_USER_WORLD_DB;
GRANT CREATE VIEW TO ROLE_USER_WORLD_DB;
GRANT CREATE ROLE TO ROLE_USER_WORLD_DB;
GRANT CREATE SEQUENCE TO ROLE_USER_WORLD_DB;
GRANT CREATE TABLESPACE TO ROLE_USER_WORLD_DB;
GRANT CREATE SYNONYM TO ROLE_USER_WORLD_DB;
GRANT CREATE PUBLIC SYNONYM TO ROLE_USER_WORLD_DB;

GRANT ALTER ANY TABLE TO ROLE_USER_WORLD_DB;
GRANT ALTER ANY PROCEDURE TO ROLE_USER_WORLD_DB;
GRANT ALTER ANY TRIGGER TO ROLE_USER_WORLD_DB;
GRANT ALTER ANY ROLE TO ROLE_USER_WORLD_DB;
GRANT ALTER ANY SEQUENCE TO ROLE_USER_WORLD_DB;
GRANT ALTER ANY INDEX TO ROLE_USER_WORLD_DB;
GRANT ALTER PROFILE TO ROLE_USER_WORLD_DB;
GRANT ALTER SESSION TO ROLE_USER_WORLD_DB;
GRANT ALTER DATABASE TO ROLE_USER_WORLD_DB;
GRANT ALTER USER TO ROLE_USER_WORLD_DB;
GRANT ALTER TABLESPACE TO ROLE_USER_WORLD_DB;

GRANT DELETE ANY TABLE TO ROLE_USER_WORLD_DB;
GRANT DROP ANY TABLE TO ROLE_USER_WORLD_DB;
GRANT DROP ANY PROCEDURE TO ROLE_USER_WORLD_DB;
GRANT DROP ANY SEQUENCE TO ROLE_USER_WORLD_DB;
GRANT DROP ANY TRIGGER TO ROLE_USER_WORLD_DB;
GRANT DROP ANY VIEW TO ROLE_USER_WORLD_DB;
GRANT DROP ANY INDEX TO ROLE_USER_WORLD_DB;
GRANT DROP PROFILE TO ROLE_USER_WORLD_DB;
GRANT DROP ANY ROLE TO ROLE_USER_WORLD_DB;
GRANT DROP TABLESPACE TO ROLE_USER_WORLD_DB;
GRANT DROP ANY SYNONYM TO ROLE_USER_WORLD_DB;
GRANT DROP PUBLIC SYNONYM TO ROLE_USER_WORLD_DB;

GRANT EXECUTE ANY PROCEDURE TO ROLE_USER_WORLD_DB;
GRANT GRANT ANY PRIVILEGE TO ROLE_USER_WORLD_DB;
GRANT GRANT ANY ROLE TO ROLE_USER_WORLD_DB;
GRANT INSERT ANY TABLE TO ROLE_USER_WORLD_DB;
GRANT LOCK ANY TABLE TO ROLE_USER_WORLD_DB;
GRANT SELECT ANY TABLE TO ROLE_USER_WORLD_DB;
GRANT SELECT ANY SEQUENCE TO ROLE_USER_WORLD_DB;
GRANT MANAGE TABLESPACE TO ROLE_USER_WORLD_DB;

--see all sys privileges
/*
select * from SYS.USER_SYS_PRIVS USP
WHERE USP.PRIVILEGE LIKE '%TABLESP%';

select * from SYS.USER_SYS_PRIVS USP
WHERE USP.PRIVILEGE LIKE '%SYN%';
*/
select * from SYS.USER_SYS_PRIVS USP
WHERE USP.PRIVILEGE LIKE '%REF%';
/*
http://psoug.org/definition/GRANT.htm
-------------------------------------
System Privileges
Create session
Create table
Create view
Create procedure
Sysdba
Sysoper
Object Privileges Privileges can be assigned to any of the following types of database objects:
Tables: all, alter, debug, delete, flashback, insert, on commit refresh, query rewrite, references, select, update
Views: debug, delete, insert, flashback, references, select, under, update
Sequence: alter, select
Packages, Procedures, Functions: debug, execute
Materialized Views: delete, flashback, insert, select, update
Directories: read, write
Libraries: execute
User Defined Types: debug, execute, under
Operators: execute
Indextypes: execute
Full List Of Oracle Grants

System Privilege Name	Operations Authorized
Advisor Framework Privileges: All of the advisor framework privileges are part of the DBA role.
ADVISOR	Access the advisor framework through PL/SQL packages such as DBMS_ADVISOR and DBMS_SQLTUNE.
ADMINISTER SQL TUNING SET	Create, drop, select (read), load (write), and delete a SQL tuning set owned by the grantee through the DBMS_SQLTUNE package
ADMINISTER ANY SQL TUNING SET	Create, drop, select (read), load (write), and delete a SQL tuning set owned by any user through the DBMS_SQLTUNE package
CREATE ANY SQL PROFILE	Accept a SQL Profile recommended by the SQL Tuning Advisor, which is accessed through Enterprise Manager or by the DBMS_SQLTUNE package.
DROP ANY SQL PROFILE	Drop an existing SQL Profile
ALTER ANY SQL PROFILE	Alter the attributes of an existing SQL Profile
CLUSTERS:
CREATE CLUSTER	Create clusters in the grantee's schema
CREATE ANY CLUSTER	Create a cluster in any schema. Behaves similarly to CREATE ANY TABLE.
ALTER ANY CLUSTER	Alter clusters in any schema
DROP ANY CLUSTER	Drop clusters in any schema
CONTEXTS:
CREATE ANY CONTEXT	Create any context namespace
DROP ANY CONTEXT	Drop any context namespace
DATABASE:
ALTER DATABASE	Alter the database
ALTER SYSTEM	Issue ALTER SYSTEM statements
AUDIT SYSTEM	Issue AUDIT statements
DATABASE LINKS:
CREATE DATABASE LINK	Create private database links in the grantee's schema
CREATE PUBLIC DATABASE LINK	Create public database links
DROP PUBLIC DATABASE LINK	Drop public database links
DEBUGGING:
DEBUG CONNECT SESSION	Connect the current session to a debugger.
DEBUG ANY PROCEDURE	Debug all PL/SQL and Java code in any database object. Display information on all SQL statements executed by the application.
Note: Granting this privilege is equivalent to granting the DEBUG object privilege on all applicable objects in the database.

DIMENSIONS:
CREATE DIMENSION	Create dimensions in the grantee's schema
CREATE ANY DIMENSION	Create dimensions in any schema
ALTER ANY DIMENSION	Alter dimensions in any schema
DROP ANY DIMENSION	Drop dimensions in any schema
DIRECTORIES
CREATE ANY DIRECTORY	Create directory database objects
DROP ANY DIRECTORY	Drop directory database objects
INDEXTYPES:
CREATE INDEXTYPE	Create an indextype in the grantee's schema
CREATE ANY INDEXTYPE	Create an indextype in any schema
ALTER ANY INDEXTYPE	Modify indextypes in any schema
DROP ANY INDEXTYPE	Drop an indextype in any schema
EXECUTE ANY INDEXTYPE	Reference an indextype in any schema
INDEXES:
CREATE ANY INDEX	Create in any schema a domain index or an index on any table in any schema
ALTER ANY INDEX	Alter indexes in any schema
DROP ANY INDEX	Drop indexes in any schema
JOB SCHEDULER OBJECTS:	The following privileges are needed to execute procedures in the DBMS_SCHEDULER package.
CREATE JOB	Create jobs, schedules, or programs in the grantee's schema
CREATE ANY JOB	Create, alter, or drop jobs, schedules, or programs in any schema
Note: This extremely powerful privilege allows the grantee to execute code as any other user. It should be granted with caution.

EXECUTE ANY PROGRAM	Use any program in a job in the grantee's schema
EXECUTE ANY CLASS	Specify any job class in a job in the grantee's schema
MANAGE SCHEDULER	Create, alter, or drop any job class, window, or window group
LIBRARIES:
CREATE LIBRARY	Create external procedure or function libraries in the grantee's schema
CREATE ANY LIBRARY	Create external procedure or function libraries in any schema
DROP ANY LIBRARY	Drop external procedure or function libraries in any schema
MATERIALIZED VIEWS:
CREATE MATERIALIZED VIEW	Create a materialized view in the grantee's schema
CREATE ANY MATERIALIZED VIEW	Create materialized views in any schema
ALTER ANY MATERIALIZED VIEW	Alter materialized views in any schema
DROP ANY MATERIALIZED VIEW	Drop materialized views in any schema
QUERY REWRITE	This privilege has been deprecated. No specify privileges are needed for a user to enable rewrite for a materialized view that references tables or views in the user's own schema.
GLOBAL QUERY REWRITE	Enable rewrite using a materialized view when that materialized view references tables or views in any schema
ON COMMIT REFRESH	Create a refresh-on-commit materialized view on any table in the database
Alter a refresh-on-demand materialized on any table in the database to refresh-on-commit

FLASHBACK ANY TABLE	Issue a SQL Flashback Query on any table, view, or materialized view in any schema. This privilege is not needed to execute the DBMS_FLASHBACK procedures.
OPERATORS:
CREATE OPERATOR	Create an operator and its bindings in the grantee's schema
CREATE ANY OPERATOR	Create an operator and its bindings in any schema
ALTER ANY OPERATOR	Modify an operator in any schema
DROP ANY OPERATOR	Drop an operator in any schema
EXECUTE ANY OPERATOR	Reference an operator in any schema
OUTLINES:
CREATE ANY OUTLINE	Create public outlines that can be used in any schema that uses outlines
ALTER ANY OUTLINE	Modify outlines
DROP ANY OUTLINE	Drop outlines
PROCEDURES:
CREATE PROCEDURE	Create stored procedures, functions, and packages in the grantee's schema
CREATE ANY PROCEDURE	Create stored procedures, functions, and packages in any schema
ALTER ANY PROCEDURE	Alter stored procedures, functions, or packages in any schema
DROP ANY PROCEDURE	Drop stored procedures, functions, or packages in any schema
EXECUTE ANY PROCEDURE	Execute procedures or functions, either standalone or packaged
Reference public package variables in any schema

PROFILES:
CREATE PROFILE	Create profiles
ALTER PROFILE	Alter profiles
DROP PROFILE	Drop profiles
ROLES:
CREATE ROLE	Create roles
ALTER ANY ROLE	Alter any role in the database
DROP ANY ROLE	Drop roles
GRANT ANY ROLE	Grant any role in the database
ROLLBACK SEGMENTS:
CREATE ROLLBACK SEGMENT	Create rollback segments
ALTER ROLLBACK SEGMENT	Alter rollback segments
DROP ROLLBACK SEGMENT	Drop rollback segments
SEQUENCES:
CREATE SEQUENCE	Create sequences in the grantee's schema
CREATE ANY SEQUENCE	Create sequences in any schema
ALTER ANY SEQUENCE	Alter any sequence in the database
DROP ANY SEQUENCE	Drop sequences in any schema
SELECT ANY SEQUENCE	Reference sequences in any schema
SESSIONS:
CREATE SESSION	Connect to the database
ALTER RESOURCE COST	Set costs for session resources
ALTER SESSION	Issue ALTER SESSION statements
RESTRICTED SESSION	Logon after the instance is started using the SQL*Plus STARTUP RESTRICT statement
SNAPSHOTS: See MATERIALIZED VIEWS
SYNONYMS:
CREATE SYNONYM	Create synonyms in the grantee's schema
CREATE ANY SYNONYM	Create private synonyms in any schema
CREATE PUBLIC SYNONYM	Create public synonyms
DROP ANY SYNONYM	Drop private synonyms in any schema
DROP PUBLIC SYNONYM	Drop public synonyms
TABLES:
Note: For external tables, the only valid privileges are CREATE ANY TABLE, ALTER ANY TABLE, DROP ANY TABLE, and SELECT ANY TABLE.

CREATE TABLE	Create tables in the grantee's schema
CREATE ANY TABLE	Create tables in any schema. The owner of the schema containing the table must have space quota on the tablespace to contain the table.
ALTER ANY TABLE	Alter any table or view in any schema
BACKUP ANY TABLE	Use the Export utility to incrementally export objects from the schema of other users
DELETE ANY TABLE	Delete rows from tables, table partitions, or views in any schema
DROP ANY TABLE	Drop or truncate tables or table partitions in any schema
INSERT ANY TABLE	Insert rows into tables and views in any schema
LOCK ANY TABLE	Lock tables and views in any schema
SELECT ANY TABLE	Query tables, views, or materialized views in any schema
FLASHBACK ANY TABLE	Issue a SQL Flashback Query on any table, view, or materialized view in any schema. This privilege is not needed to execute the DBMS_FLASHBACK procedures.
UPDATE ANY TABLE	Update rows in tables and views in any schema
TABLESPACES:
CREATE TABLESPACE	Create tablespaces
ALTER TABLESPACE	Alter tablespaces
DROP TABLESPACE	Drop tablespaces
MANAGE TABLESPACE	Take tablespaces offline and online and begin and end tablespace backups
UNLIMITED TABLESPACE	Use an unlimited amount of any tablespace. This privilege overrides any specific quotas assigned. If you revoke this privilege from a user, then the user's schema objects remain but further tablespace allocation is denied unless authorized by specific tablespace quotas. You cannot grant this system privilege to roles.
TRIGGERS:
CREATE TRIGGER	Create a database trigger in the grantee's schema
CREATE ANY TRIGGER	Create database triggers in any schema
ALTER ANY TRIGGER	Enable, disable, or compile database triggers in any schema
DROP ANY TRIGGER	Drop database triggers in any schema
ADMINISTER DATABASE TRIGGER	Create a trigger on DATABASE. You must also have the CREATE TRIGGER or CREATE ANY TRIGGER system privilege.
TYPES:
CREATE TYPE	Create object types and object type bodies in the grantee's schema
CREATE ANY TYPE	Create object types and object type bodies in any schema
ALTER ANY TYPE	Alter object types in any schema
DROP ANY TYPE	Drop object types and object type bodies in any schema
EXECUTE ANY TYPE	Use and reference object types and collection types in any schema, and invoke methods of an object type in any schema if you make the grant to a specific user. If you grant EXECUTE ANY TYPE to a role, then users holding the enabled role will not be able to invoke methods of an object type in any schema.
UNDER ANY TYPE	Create subtypes under any nonfinal object types.
USERS:
CREATE USER	Create users. This privilege also allows the creator to:
Assign quotas on any tablespace

Set default and temporary tablespaces

Assign a profile as part of a CREATE USER statement

ALTER USER	Alter any user. This privilege authorizes the grantee to:
Change another user's password or authentication method

Assign quotas on any tablespace

Set default and temporary tablespaces

Assign a profile and default roles

DROP USER	Drop users
VIEWS:
CREATE VIEW	Create views in the grantee's schema
CREATE ANY VIEW	Create views in any schema
DROP ANY VIEW	Drop views in any schema
UNDER ANY VIEW	Create subviews under any object views
FLASHBACK ANY TABLE	Issue a SQL Flashback Query on any table, view, or materialized view in any schema. This privilege is not needed to execute the DBMS_FLASHBACK procedures.
MISCELLANEOUS:
ANALYZE ANY	Analyze any table, cluster, or index in any schema
AUDIT ANY	Audit any object in any schema using AUDIT schema_objects statements
COMMENT ANY TABLE	Comment on any table, view, or column in any schema
EXEMPT ACCESS POLICY	Bypass fine-grained access control
Caution: This is a very powerful system privilege, as it lets the grantee bypass application-driven security policies. Database administrators should use caution when granting this privilege.

FORCE ANY TRANSACTION	Force the commit or rollback of any in-doubt distributed transaction in the local database
Induce the failure of a distributed transaction

FORCE TRANSACTION	Force the commit or rollback of the grantee's in-doubt distributed transactions in the local database
GRANT ANY OBJECT PRIVILEGE	Grant any object privilege
Revoke any object privilege that was granted by the object owner or by some other user with the GRANT ANY OBJECT PRIVILEGE privilege

GRANT ANY PRIVILEGE	Grant any system privilege
RESUMABLE	Enable resumable space allocation
SELECT ANY DICTIONARY	Query any data dictionary object in the SYS schema. This privilege lets you selectively override the default FALSE setting of the O7_DICTIONARY_ACCESSIBILITY initialization parameter.
SYSDBA	Perform STARTUP and SHUTDOWN operations
ALTER DATABASE: open, mount, back up, or change character set

CREATE DATABASE

ARCHIVELOG and RECOVERY

CREATE SPFILE

Includes the RESTRICTED SESSION privilege

SYSOPER	Perform STARTUP and SHUTDOWN operations
ALTER DATABASE: open, mount, or back up

ARCHIVELOG and RECOVERY

CREATE SPFILE

Includes the RESTRICTED SESSION privilege

CONNECT, RESOURCE, and DBA	These roles are provided for compatibility with previous versions of Oracle Database. You can determine the privileges encompassed by these roles by querying the DBA_SYS_PRIVS data dictionary view.
Note: Oracle recommends that you design your own roles for database security rather than relying on these roles. These roles may not be created automatically by future versions of Oracle Database.

DELETE_CATALOG_ROLE EXECUTE_CATALOG_ROLE SELECT_CATALOG_ROLE	These roles are provided for accessing data dictionary views and packages.
EXP_FULL_DATABASE 
IMP_FULL_DATABASE
These roles are provided for convenience in using the import and export utilities.
AQ_USER_ROLE
AQ_ADMINISTRATOR_ROLE
You need these roles to use Oracle Advanced Queuing.
SNMPAGENT	This role is used by the Enterprise Manager Intelligent Agent.
RECOVERY_CATALOG_OWNER	You need this role to create a user who owns a recovery catalog.

Oracle Database Predefined Roles
Predefined Role	Purpose
HS_ADMIN_ROLE	A DBA using Oracle Database heterogeneous services needs this role to access appropriate tables in the data dictionary.
SCHEDULER_ADMIN	This role allows the grantee to execute the procedures of the DBMS_SCHEDULER package. It includes all of the job scheduler system privileges and is included in the DBA role.


*/

SHOW ERRORS;
DROP USER FOOTBALL_USER CASCADE;

CREATE USER FOOTBALL_USER IDENTIFIED BY FOOTBALL_USER;
GRANT CREATE SESSION, CONNECT, RESOURCE TO FOOTBALL_USER;

ALTER USER FOOTBALL_USER QUOTA 100 M ON USERS;
DECLARE
  ROLE_ADMIN T_ROLES.ROLE_NAME%TYPE;
  ROLE_USER T_ROLES.ROLE_NAME%TYPE;

  USER_ADMIN T_USERS%ROWTYPE;
  USER_SIMPLE_USER T_USERS%ROWTYPE;

  ACCOUNT_ADMIN T_ACCOUNTS%ROWTYPE;
  ACCOUNT_SIMPLE_USER T_ACCOUNTS%ROWTYPE;

  USER_ROLE_ADMIN T_USER_ROLES%ROWTYPE;
  USER_ROLE_SIMPLE_USER T_USER_ROLES%ROWTYPE;
BEGIN
  --CLEAN UP
  PKG_USER_ROLES.DELETE_ALL;
  PKG_ACCOUNTS.DELETE_ALL;
  PKG_USERS.DELETE_ALL;
  PKG_ROLE.DELETE_ALL;

  --SET UP
  ROLE_ADMIN := 'ROLE_ADMIN';
  ROLE_USER := 'ROLE_USER';

  PKG_ROLE.INSERT_ROLE(ROLE_ADMIN);
  PKG_ROLE.INSERT_ROLE(ROLE_USER);
  USER_ROLE_ADMIN.ROLE_ID := PKG_ROLE.GET_ROLE_ID_BY_NAME(ROLE_ADMIN);
  USER_ROLE_SIMPLE_USER.ROLE_ID := PKG_ROLE.GET_ROLE_ID_BY_NAME(ROLE_USER);

  USER_ADMIN := PKG_USERS.CREATE_USER('Ivanov', 'Ivan', 'Ivanovic', 'ivanov.ivan@gmail.com', '069-853-425', 'M',
  TO_DATE('15.02.1983', 'dd.mm.yyyy'), 'T', '', NULL);
  USER_SIMPLE_USER := PKG_USERS.CREATE_USER('Durov', 'Daniil', 'Dmitrievic', 'durov.daniil@gmail.com', '069-835-245', 'M',
  TO_DATE('15.02.1989', 'dd.mm.yyyy'), 'T', '', NULL);
  PKG_USERS.INSERT_USER(USER_ADMIN);
  USER_ADMIN.USER_ID := PKG_USERS.GET_LAST_USER_ID;
  PKG_USERS.INSERT_USER(USER_SIMPLE_USER);
  USER_SIMPLE_USER.USER_ID := PKG_USERS.GET_LAST_USER_ID;

  ACCOUNT_ADMIN := PKG_ACCOUNTS.GET_ACCOUNT(USER_ADMIN.USER_ID, 'ivanov.ivan', 'ivanov.ivan15932');
  PKG_ACCOUNTS.INSERT_ACCOUNT(ACCOUNT_ADMIN);
  ACCOUNT_SIMPLE_USER := PKG_ACCOUNTS.GET_ACCOUNT(USER_SIMPLE_USER.USER_ID, 'durov.daniil', 'durov.daniil15932');
  PKG_ACCOUNTS.INSERT_ACCOUNT(ACCOUNT_SIMPLE_USER);

  USER_ROLE_ADMIN.USER_ID := USER_ADMIN.USER_ID;
  USER_ROLE_SIMPLE_USER.USER_ID := USER_SIMPLE_USER.USER_ID;
  PKG_USER_ROLES.INSERT_USER_ROLE(USER_ROLE_ADMIN);
  PKG_USER_ROLES.INSERT_USER_ROLE(USER_ROLE_SIMPLE_USER);

END;
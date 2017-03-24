set SERVEROUTPUT ON;
declare
  l_user_id T_USERS.USER_ID%TYPE;
begin
  l_user_id := &user_id;
  delete from T_USER_ATTEMPTS where ACCOUNT_USER_ID = l_user_id;
  delete from T_PASSWORD_HISTORY where ACCOUNT_USER_ID = l_user_id;
  delete from T_ACCOUNTS where ACCOUNT_USER_ID = l_user_id;
  delete from T_USER_ROLES where USER_ID = l_user_id;
  delete from T_GROUP_USERS where USER_ID = l_user_id;
  delete from T_USERS where USER_ID = l_user_id;
  COMMIT;
  DBMS_OUTPUT.PUT_LINE('success!');
  exception
    when others then
    ROLLBACK;
    DBMS_OUTPUT.PUT_LINE('error!');
    DBMS_OUTPUT.PUT_LINE('exception - ' || SQLCODE || ' -ERROR- '|| SQLERRM);
end;
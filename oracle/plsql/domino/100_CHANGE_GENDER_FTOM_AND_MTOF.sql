UPDATE T_USERS SET USER_GENDER = (SELECT CASE 
                  WHEN USER_GENDER = 'M' THEN 'F'
                  ELSE 'M' END
                FROM DUAL);
                
                
SELECT table_name,
CASE
  WHEN owner='SYS' THEN 'The owner is SYS'
  WHEN owner='SYSTEM' THEN 'The owner is SYSTEM'
  ELSE 'The owner is another value'
END
FROM all_tables;
SET SERVEROUTPUT ON;
DECLARE
	CURSOR CUR_USER_ALL_OBJECTS IS
		SELECT *
		FROM (
			SELECT OBJECT_TYPE, OBJECT_NAME, 1 AS ORDER_NUMBER
			FROM USER_OBJECTS
			WHERE OBJECT_TYPE = 'PACKAGE'
			UNION
			SELECT OBJECT_TYPE, OBJECT_NAME, 2 AS ORDER_NUMBER
			FROM USER_OBJECTS
			WHERE OBJECT_TYPE = 'VIEW'
			/*UNION
			SELECT OBJECT_TYPE, OBJECT_NAME, 3 AS ORDER_NUMBER
			FROM USER_OBJECTS
			WHERE OBJECT_TYPE = 'TABLE'*/
			UNION
			SELECT OBJECT_TYPE, OBJECT_NAME, 4 AS ORDER_NUMBER
			FROM USER_OBJECTS
			WHERE OBJECT_TYPE = 'SEQUENCE'
		)
		ORDER BY ORDER_NUMBER;
	
	L_CURRENT_OBJECT CUR_USER_ALL_OBJECTS%ROWTYPE;
  L_STRING VARCHAR2(50);
BEGIN
	OPEN CUR_USER_ALL_OBJECTS;
	
	LOOP
		FETCH CUR_USER_ALL_OBJECTS INTO L_CURRENT_OBJECT;
      L_STRING := 'DROP ' || L_CURRENT_OBJECT.OBJECT_TYPE || ' ' || L_CURRENT_OBJECT.OBJECT_NAME;
			dbms_output.put_line(L_STRING);
      EXECUTE IMMEDIATE L_STRING;
		EXIT WHEN CUR_USER_ALL_OBJECTS%NOTFOUND;
	END LOOP;
	CLOSE CUR_USER_ALL_OBJECTS;
END;
---------------------------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE PKG_STATUS_TYPE
IS
  PROCEDURE INSERT_STATUS_TYPE(STATUS_TYPE_IN IN T_STATUS_TYPES.STATUS_TYPE%TYPE);
  PROCEDURE INSERT_NEXT_STATUS_TYPE(STATUS_TYPE_IN IN T_STATUS_TYPES.STATUS_TYPE%TYPE,
                                    NEXT_STATUS_TYPE_ID_IN IN T_STATUS_TYPES.NEXT_STATUS_TYPE_ID%TYPE);
  FUNCTION GET_STATUS_TYPE_ID_BY_NAME(STATUS_TYPE_IN IN T_STATUS_TYPES.STATUS_TYPE%TYPE)
  RETURN T_STATUS_TYPES.STATUS_TYPE_ID%TYPE;
END PKG_STATUS_TYPE;
/

CREATE OR REPLACE PACKAGE BODY PKG_STATUS_TYPE
IS
  PROCEDURE INSERT_STATUS_TYPE(STATUS_TYPE_IN IN T_STATUS_TYPES.STATUS_TYPE%TYPE)
  AS
  BEGIN
    INSERT INTO T_STATUS_TYPES(STATUS_TYPE) VALUES(STATUS_TYPE_IN);
    COMMIT;
  END INSERT_STATUS_TYPE;

  PROCEDURE INSERT_NEXT_STATUS_TYPE(STATUS_TYPE_IN IN T_STATUS_TYPES.STATUS_TYPE%TYPE,
                                    NEXT_STATUS_TYPE_ID_IN IN T_STATUS_TYPES.NEXT_STATUS_TYPE_ID%TYPE)
  AS
  BEGIN
    UPDATE T_STATUS_TYPES SET NEXT_STATUS_TYPE_ID = NEXT_STATUS_TYPE_ID_IN WHERE STATUS_TYPE = STATUS_TYPE_IN;
    COMMIT;
  END INSERT_NEXT_STATUS_TYPE;

  FUNCTION GET_STATUS_TYPE_ID_BY_NAME(STATUS_TYPE_IN IN T_STATUS_TYPES.STATUS_TYPE%TYPE)
  RETURN T_STATUS_TYPES.STATUS_TYPE_ID%TYPE
  AS
    RETURN_RESULT T_STATUS_TYPES.STATUS_TYPE_ID%TYPE;
  BEGIN
    SELECT STATUS_TYPE_ID INTO RETURN_RESULT
    FROM T_STATUS_TYPES
    WHERE STATUS_TYPE = STATUS_TYPE_IN;
    RETURN RETURN_RESULT;
  END GET_STATUS_TYPE_ID_BY_NAME;

END PKG_STATUS_TYPE;
---------------------------------------------------------------------------------------------------
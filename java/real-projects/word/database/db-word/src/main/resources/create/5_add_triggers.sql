PROMPT--------------CREATE TRIGGERS--------------;
--CREATE TRIGGERS
CREATE OR REPLACE TRIGGER TRIGGER_AUDIT_LANGUAGES
AFTER INSERT OR UPDATE /*OR DELETE*/ ON T_LANGUAGES
FOR EACH ROW
DECLARE
  current_timestamp_l TIMESTAMP := CURRENT_TIMESTAMP;
--  L_OPERATION_ID T_C_OPERATIONS.OPERATION_ID%TYPE;
BEGIN
  IF INSERTING THEN
--    L_OPERATION_ID := 1;
    INSERT INTO AUDIT_LANGUAGES(LANGUAGE_ID, CREATED_DATE_TIME, UPDATED_DATE_TIME, OPERATION_ID)
    VALUES(:NEW.LANGUAGE_ID, current_timestamp_l, current_timestamp_l, 1);
  ELSIF UPDATING THEN
    INSERT INTO AUDIT_LANGUAGES(LANGUAGE_ID, UPDATED_DATE_TIME, OPERATION_ID)
    VALUES(:NEW.LANGUAGE_ID, current_timestamp_l, 2);
--    L_OPERATION_ID := 2;
--  ELSE
--    L_OPERATION_ID := 3;
  END IF;

--  IF L_OPERATION_ID = 3 THEN
--    INSERT INTO AUDIT_LANGUAGES(LANGUAGE_ID, CREATED_DATE_TIME, UPDATED_DATE_TIME, OPERATION_ID)
--    VALUES(:OLD.LANGUAGE_ID, current_timestamp_l, current_timestamp_l, L_OPERATION_ID);
--  ELSE

--  END IF;
END;
/

CREATE OR REPLACE TRIGGER TRIGGER_AUDIT_PART_OF_SPEECHES
AFTER INSERT OR UPDATE /*OR DELETE*/ ON T_PART_OF_SPEECHES
FOR EACH ROW
DECLARE
  current_timestamp_l TIMESTAMP := CURRENT_TIMESTAMP;
--  L_OPERATION_ID T_C_OPERATIONS.OPERATION_ID%TYPE;
BEGIN
  IF INSERTING THEN
--    L_OPERATION_ID := 1;
    INSERT INTO AUDIT_PART_OF_SPEECHES (PART_OF_SPEECH_ID, CREATED_DATE_TIME, UPDATED_DATE_TIME, OPERATION_ID)
    VALUES(:NEW.PART_OF_SPEECH_ID, current_timestamp_l, current_timestamp_l, 1);
  ELSIF UPDATING THEN
    INSERT INTO AUDIT_PART_OF_SPEECHES (PART_OF_SPEECH_ID, UPDATED_DATE_TIME, OPERATION_ID)
    VALUES(:NEW.PART_OF_SPEECH_ID, current_timestamp_l, 2);
--    L_OPERATION_ID := 2;
--  ELSIF DELETING THEN
--    L_OPERATION_ID := 3;
  END IF;

--  IF L_OPERATION_ID = 3 THEN
--    INSERT INTO AUDIT_PART_OF_SPEECHES (PART_OF_SPEECH_ID, CREATED_DATE_TIME, UPDATED_DATE_TIME, OPERATION_ID)
--    VALUES(:OLD.PART_OF_SPEECH_ID, current_timestamp_l, current_timestamp_l, L_OPERATION_ID);
--  ELSE

--  END IF;
END;
/

CREATE OR REPLACE TRIGGER TRIGGER_AUDIT_TRANSLATES
AFTER INSERT OR UPDATE /*OR DELETE*/ ON T_TRANSLATES
FOR EACH ROW
DECLARE
  current_timestamp_l TIMESTAMP := CURRENT_TIMESTAMP;
--  L_OPERATION_ID T_C_OPERATIONS.OPERATION_ID%TYPE;
BEGIN
  IF INSERTING THEN
--    L_OPERATION_ID := 1;
    INSERT INTO AUDIT_TRANSLATES (TRANSLATE_ID, CREATED_DATE_TIME, UPDATED_DATE_TIME, OPERATION_ID)
    VALUES(:NEW.TRANSLATE_ID, current_timestamp_l, current_timestamp_l, 1);
  ELSIF UPDATING THEN
--    L_OPERATION_ID := 2;
    INSERT INTO AUDIT_TRANSLATES (TRANSLATE_ID, UPDATED_DATE_TIME, OPERATION_ID)
    VALUES(:NEW.TRANSLATE_ID, current_timestamp_l, 2);
--  ELSIF DELETING THEN
--    L_OPERATION_ID := 3;
  END IF;

--  IF L_OPERATION_ID = 3 THEN
--    INSERT INTO AUDIT_TRANSLATES (TRANSLATE_ID, CREATED_DATE_TIME, UPDATED_DATE_TIME, OPERATION_ID)
--    VALUES(:OLD.TRANSLATE_ID, current_timestamp_l, current_timestamp_l, L_OPERATION_ID);
--  ELSE
--  END IF;
END;
/

CREATE OR REPLACE TRIGGER TRIGGER_AUDIT_WORDS
AFTER INSERT OR UPDATE /*OR DELETE*/ ON T_WORDS
FOR EACH ROW
DECLARE
  current_timestamp_l TIMESTAMP := CURRENT_TIMESTAMP;
--  L_OPERATION_ID T_C_OPERATIONS.OPERATION_ID%TYPE;
BEGIN
  IF INSERTING THEN
--    L_OPERATION_ID := 1;
    INSERT INTO AUDIT_WORDS (WORD_ID, CREATED_DATE_TIME, UPDATED_DATE_TIME, OPERATION_ID)
    VALUES(:NEW.WORD_ID, current_timestamp_l, current_timestamp_l, 1);
  ELSIF UPDATING THEN
    INSERT INTO AUDIT_WORDS (WORD_ID, UPDATED_DATE_TIME, OPERATION_ID)
    VALUES(:NEW.WORD_ID, current_timestamp_l, 2);
--    L_OPERATION_ID := 2;
--  ELSE
--    L_OPERATION_ID := 3;
  END IF;

--  IF L_OPERATION_ID = 3 THEN
--    INSERT INTO AUDIT_WORDS (WORD_ID, CREATED_DATE_TIME, UPDATED_DATE_TIME, OPERATION_ID)
--    VALUES(:OLD.WORD_ID, current_timestamp_l, current_timestamp_l, L_OPERATION_ID);
--  ELSE

--  END IF;
END;
/*
/
SHOW ERRORS;*/

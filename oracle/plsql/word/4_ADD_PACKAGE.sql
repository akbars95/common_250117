PROMPT --------------CREATE OR REPLACE PACKAGE PACKAGE_CONVERTER--------------;
--CREATE OR REPLACE PACKAGE PACKAGE_CONVERTER
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE PACKAGE_CONVERTER IS
	
	FUNCTION LANGUAGE_TO_WORD(T_LANGUAGES_IN IN T_LANGUAGES%ROWTYPE)
	RETURN T_WORDS%ROWTYPE;
	
	FUNCTION WORDS_PART_OF_SPEECHES_TO_TRAN(T_WORDS_PART_OF_SPEECHES_F_IN IN T_WORDS_PART_OF_SPEECHES%ROWTYPE,
											T_WORDS_PART_OF_SPEECHES_S_IN IN T_WORDS_PART_OF_SPEECHES%ROWTYPE)
	RETURN T_TRANSLATES%ROWTYPE;
	
END PACKAGE_CONVERTER;
/

CREATE OR REPLACE PACKAGE BODY PACKAGE_CONVERTER IS
	
	FUNCTION LANGUAGE_TO_WORD(T_LANGUAGES_IN IN T_LANGUAGES%ROWTYPE)
	RETURN T_WORDS%ROWTYPE
	AS
		L_T_WORDS T_WORDS%ROWTYPE;
	BEGIN
		L_T_WORDS.LANGUAGE_ID := T_LANGUAGES_IN.LANGUAGE_ID;
		RETURN L_T_WORDS;
	END LANGUAGE_TO_WORD;
	
	FUNCTION WORDS_PART_OF_SPEECHES_TO_TRAN(T_WORDS_PART_OF_SPEECHES_F_IN IN T_WORDS_PART_OF_SPEECHES%ROWTYPE,
											T_WORDS_PART_OF_SPEECHES_S_IN IN T_WORDS_PART_OF_SPEECHES%ROWTYPE)
	RETURN T_TRANSLATES%ROWTYPE
	AS
		LT_TRANSLATES T_TRANSLATES%ROWTYPE;
	BEGIN
		LT_TRANSLATES.PART_OF_SPEECH_WORD_ID_F := T_WORDS_PART_OF_SPEECHES_F_IN.PART_OF_SPEECH_WORD_ID;
		LT_TRANSLATES.PART_OF_SPEECH_WORD_ID_S := T_WORDS_PART_OF_SPEECHES_S_IN.PART_OF_SPEECH_WORD_ID;
		RETURN LT_TRANSLATES;
	END WORDS_PART_OF_SPEECHES_TO_TRAN;
	
END PACKAGE_CONVERTER;
/

PROMPT --------------CREATE OR REPLACE PACKAGE PACKAGE_WORDS_LANGUAGES--------------;
--CREATE OR REPLACE PACKAGE PACKAGE_WORDS_LANGUAGES
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE PACKAGE_WORDS_LANGUAGES IS
  PROCEDURE INSERT_LANGUAGE(LANGUAGE_NAME_IN IN T_LANGUAGES.LANGUAGE_NAME%TYPE);
  PROCEDURE UPDATE_LANGUAGE(LANGUAGE_NAME_IN IN T_LANGUAGES.LANGUAGE_NAME%TYPE, LANGUAGE_ID_IN IN T_LANGUAGES.LANGUAGE_ID%TYPE);
  PROCEDURE DELETE_LANGUAGE(LANGUAGE_NAME_IN IN T_LANGUAGES.LANGUAGE_NAME%TYPE, LANGUAGE_ID_IN IN T_LANGUAGES.LANGUAGE_ID%TYPE,
                            DELETE_AUDIT_TABLE_IN IN INTEGER DEFAULT 1);
  PROCEDURE SELECT_LANGUAGE_BY_ID(LANGUAGE_ID_IN IN T_LANGUAGES.LANGUAGE_ID%TYPE, LANGUAGE_NAME_OUT OUT T_LANGUAGES.LANGUAGE_NAME%TYPE);
  PROCEDURE SELECT_LANGUAGE_BY_NAME(LANGUAGE_NAME_IN IN T_LANGUAGES.LANGUAGE_NAME%TYPE, LANGUAGE_ID_OUT OUT T_LANGUAGES.LANGUAGE_ID%TYPE);
  PROCEDURE DELETE_ALL_LANGUAGE(DELETE_AUDIT_TABLE_IN IN INTEGER DEFAULT 1);
  PROCEDURE GET_ALL_LANGUAGE(SYS_REFCURSOR_OUT OUT SYS_REFCURSOR);
END PACKAGE_WORDS_LANGUAGES;
/

PROMPT --------------CREATE OR REPLACE PACKAGE BODY PACKAGE_WORDS_LANGUAGES---------;
--CREATE OR REPLACE PACKAGE BODY PACKAGE_WORDS_LANGUAGES
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE BODY PACKAGE_WORDS_LANGUAGES IS
  
  --PROCEDURE INSERT_LANGUAGE
  PROCEDURE INSERT_LANGUAGE(LANGUAGE_NAME_IN IN T_LANGUAGES.LANGUAGE_NAME%TYPE)
  AS
  BEGIN
    INSERT INTO T_LANGUAGES(LANGUAGE_NAME) VALUES(LANGUAGE_NAME_IN);
    COMMIT;
  END INSERT_LANGUAGE;
  
  --PROCEDURE UPDATE_LANGUAGE
  PROCEDURE UPDATE_LANGUAGE(LANGUAGE_NAME_IN IN T_LANGUAGES.LANGUAGE_NAME%TYPE, LANGUAGE_ID_IN IN T_LANGUAGES.LANGUAGE_ID%TYPE)
  AS
  BEGIN
    UPDATE T_LANGUAGES SET LANGUAGE_NAME = LANGUAGE_NAME_IN WHERE LANGUAGE_ID = LANGUAGE_ID_IN;
    COMMIT;
  END UPDATE_LANGUAGE;
  
  --PROCEDURE DELETE_LANGUAGE
  PROCEDURE DELETE_LANGUAGE(LANGUAGE_NAME_IN IN T_LANGUAGES.LANGUAGE_NAME%TYPE, LANGUAGE_ID_IN IN T_LANGUAGES.LANGUAGE_ID%TYPE,
                            DELETE_AUDIT_TABLE_IN IN INTEGER DEFAULT 1)
  AS
  BEGIN
    IF DELETE_AUDIT_TABLE_IN = 1 THEN
      IF LANGUAGE_NAME_IN IS NOT NULL THEN
        DELETE FROM AUDIT_LANGUAGES WHERE LANGUAGE_ID = (SELECT L.LANGUAGE_ID FROM T_LANGUAGES L WHERE L.LANGUAGE_NAME = LANGUAGE_NAME_IN);
      ELSIF LANGUAGE_ID_IN IS NOT NULL THEN
        DELETE FROM AUDIT_LANGUAGES WHERE LANGUAGE_ID = LANGUAGE_ID_IN;
      END IF;
    END IF;
    
    IF LANGUAGE_NAME_IN IS NOT NULL THEN
      DELETE FROM T_LANGUAGES WHERE LANGUAGE_NAME = LANGUAGE_NAME_IN;
    ELSIF LANGUAGE_ID_IN IS NOT NULL THEN
      DELETE FROM T_LANGUAGES WHERE LANGUAGE_ID = LANGUAGE_ID_IN;
    END IF;
    
    
    COMMIT;
  END DELETE_LANGUAGE;
  
  PROCEDURE SELECT_LANGUAGE_BY_ID(LANGUAGE_ID_IN IN T_LANGUAGES.LANGUAGE_ID%TYPE, LANGUAGE_NAME_OUT OUT T_LANGUAGES.LANGUAGE_NAME%TYPE)
  AS
  BEGIN
    SELECT L.LANGUAGE_NAME INTO LANGUAGE_NAME_OUT
    FROM V_LANGUAGES L
    WHERE L.LANGUAGE_ID = LANGUAGE_ID_IN;
  END SELECT_LANGUAGE_BY_ID;
  
  PROCEDURE SELECT_LANGUAGE_BY_NAME(LANGUAGE_NAME_IN IN T_LANGUAGES.LANGUAGE_NAME%TYPE, LANGUAGE_ID_OUT OUT T_LANGUAGES.LANGUAGE_ID%TYPE)
  AS
  BEGIN
    SELECT L.LANGUAGE_ID INTO LANGUAGE_ID_OUT
    FROM V_LANGUAGES L
    WHERE L.LANGUAGE_NAME = LANGUAGE_NAME_IN;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      LANGUAGE_ID_OUT := NULL;
  END SELECT_LANGUAGE_BY_NAME;
  
  --PROCEDURE DELETE_ALL_LANGUAGE
  PROCEDURE DELETE_ALL_LANGUAGE(DELETE_AUDIT_TABLE_IN IN INTEGER DEFAULT 1)
  AS
  BEGIN
    IF DELETE_AUDIT_TABLE_IN = 1 THEN
      DELETE FROM AUDIT_LANGUAGES;
      COMMIT;
    END IF;
    DELETE FROM T_LANGUAGES;
    COMMIT;
  END DELETE_ALL_LANGUAGE;
  
--GET_ALL_LANGUAGE
  PROCEDURE GET_ALL_LANGUAGE(SYS_REFCURSOR_OUT OUT SYS_REFCURSOR)
  AS  
  BEGIN
    OPEN SYS_REFCURSOR_OUT FOR
      SELECT * FROM T_LANGUAGES ORDER BY LANGUAGE_ID;
  END GET_ALL_LANGUAGE;
  
END PACKAGE_WORDS_LANGUAGES;
/

PROMPT --------------CREATE OR REPLACE PACKAGE PACKAGE_WORDS_PART_OF_SPEECHES--------------;
--CREATE OR REPLACE PACKAGE PACKAGE_WORDS_PART_OF_SPEECHES
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE PACKAGE_WORDS_PART_OF_SPEECHES IS
  PROCEDURE INSERT_PART_OF_SPEECH(PART_OF_SPEECH_IN IN T_PART_OF_SPEECHES.PART_OF_SPEECH%TYPE);
  PROCEDURE UPDATE_PART_OF_SPEECH(PART_OF_SPEECH_IN IN T_PART_OF_SPEECHES.PART_OF_SPEECH%TYPE,
                                  PART_OF_SPEECH_ID_IN IN T_PART_OF_SPEECHES.PART_OF_SPEECH_ID%TYPE);
  PROCEDURE DELETE_PART_OF_SPEECH(PART_OF_SPEECH_IN IN T_PART_OF_SPEECHES.PART_OF_SPEECH%TYPE,
                                  PART_OF_SPEECH_ID_IN IN T_PART_OF_SPEECHES.PART_OF_SPEECH_ID%TYPE,
                                  DELETE_AUDIT_TABLE_IN IN INTEGER DEFAULT 1);
  PROCEDURE SELECT_PART_OF_SPEECH_BY_ID(PART_OF_SPEECH_ID_IN IN T_PART_OF_SPEECHES.PART_OF_SPEECH_ID%TYPE,
                                        PART_OF_SPEECH_OUT OUT T_PART_OF_SPEECHES.PART_OF_SPEECH%TYPE);
  PROCEDURE SELECT_PART_OF_SPEECH_BY_NAME(PART_OF_SPEECH_IN IN T_PART_OF_SPEECHES.PART_OF_SPEECH%TYPE,
                                          PART_OF_SPEECH_ID_OUT OUT T_PART_OF_SPEECHES.PART_OF_SPEECH_ID%TYPE);
  PROCEDURE DELETE_ALL_PART_OF_SPEECH(DELETE_AUDIT_TABLE_IN IN INTEGER DEFAULT 1);
  PROCEDURE GET_ALL_LANGUAGE(SYS_REFCURSOR_OUT OUT SYS_REFCURSOR);
END PACKAGE_WORDS_PART_OF_SPEECHES;
/

PROMPT --------------CREATE OR REPLACE PACKAGE BODY PACKAGE_WORDS_PART_OF_SPEECHES--------------;
--CREATE OR REPLACE PACKAGE BODY PACKAGE_WORDS_PART_OF_SPEECHES
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE BODY PACKAGE_WORDS_PART_OF_SPEECHES IS
  
  --PROCEDURE INSERT_PART_OF_SPEECH
  PROCEDURE INSERT_PART_OF_SPEECH(PART_OF_SPEECH_IN IN T_PART_OF_SPEECHES.PART_OF_SPEECH%TYPE)
  AS
  BEGIN
    INSERT INTO T_PART_OF_SPEECHES(PART_OF_SPEECH) VALUES(PART_OF_SPEECH_IN);
    COMMIT;
  END INSERT_PART_OF_SPEECH;
  
  --PROCEDURE UPDATE_PART_OF_SPEECH
  PROCEDURE UPDATE_PART_OF_SPEECH(PART_OF_SPEECH_IN IN T_PART_OF_SPEECHES.PART_OF_SPEECH%TYPE, PART_OF_SPEECH_ID_IN IN T_PART_OF_SPEECHES.PART_OF_SPEECH_ID%TYPE)
  AS
  BEGIN
    UPDATE T_PART_OF_SPEECHES SET PART_OF_SPEECH = PART_OF_SPEECH_IN WHERE PART_OF_SPEECH_ID = PART_OF_SPEECH_ID_IN;
    COMMIT;
  END UPDATE_PART_OF_SPEECH;
  
  --PROCEDURE DELETE_PART_OF_SPEECH
  PROCEDURE DELETE_PART_OF_SPEECH(PART_OF_SPEECH_IN IN T_PART_OF_SPEECHES.PART_OF_SPEECH%TYPE,
                                  PART_OF_SPEECH_ID_IN IN T_PART_OF_SPEECHES.PART_OF_SPEECH_ID%TYPE,
                                  DELETE_AUDIT_TABLE_IN IN INTEGER DEFAULT 1)
  AS
  BEGIN
    IF DELETE_AUDIT_TABLE_IN = 1 THEN
      IF PART_OF_SPEECH_ID_IN IS NOT NULL THEN
        DELETE FROM AUDIT_PART_OF_SPEECHES WHERE PART_OF_SPEECH_ID = PART_OF_SPEECH_ID_IN;
      ELSIF PART_OF_SPEECH_IN IS NOT NULL THEN
        DELETE FROM AUDIT_PART_OF_SPEECHES WHERE PART_OF_SPEECH_ID = (SELECT POS.PART_OF_SPEECH_ID FROM T_PART_OF_SPEECHES POS WHERE POS.PART_OF_SPEECH = PART_OF_SPEECH_IN);
      END IF;
    END IF;
  
    IF PART_OF_SPEECH_ID_IN IS NOT NULL THEN
      DELETE FROM T_PART_OF_SPEECHES WHERE PART_OF_SPEECH_ID = PART_OF_SPEECH_ID_IN;
    ELSIF PART_OF_SPEECH_IN IS NOT NULL THEN
      DELETE FROM T_PART_OF_SPEECHES WHERE PART_OF_SPEECH = PART_OF_SPEECH_IN;
    END IF;
    COMMIT;
  END DELETE_PART_OF_SPEECH;
  
  --PROCEDURE SELECT_PART_OF_SPEECH_BY_ID
  PROCEDURE SELECT_PART_OF_SPEECH_BY_ID(PART_OF_SPEECH_ID_IN IN T_PART_OF_SPEECHES.PART_OF_SPEECH_ID%TYPE, PART_OF_SPEECH_OUT OUT T_PART_OF_SPEECHES.PART_OF_SPEECH%TYPE)
  AS
  BEGIN
    SELECT POS.PART_OF_SPEECH INTO PART_OF_SPEECH_OUT
    FROM T_PART_OF_SPEECHES POS
    WHERE POS.PART_OF_SPEECH_ID = PART_OF_SPEECH_ID_IN;
  END SELECT_PART_OF_SPEECH_BY_ID;
  
  --PROCEDURE SELECT_PART_OF_SPEECH_BY_NAME
  PROCEDURE SELECT_PART_OF_SPEECH_BY_NAME(PART_OF_SPEECH_IN IN T_PART_OF_SPEECHES.PART_OF_SPEECH%TYPE, PART_OF_SPEECH_ID_OUT OUT T_PART_OF_SPEECHES.PART_OF_SPEECH_ID%TYPE)
  AS
  BEGIN
    SELECT POS.PART_OF_SPEECH_ID INTO PART_OF_SPEECH_ID_OUT
    FROM T_PART_OF_SPEECHES POS
    WHERE POS.PART_OF_SPEECH = PART_OF_SPEECH_IN;  
  END SELECT_PART_OF_SPEECH_BY_NAME;
  
  --PROCEDURE DELETE_ALL_PART_OF_SPEECH
  PROCEDURE DELETE_ALL_PART_OF_SPEECH(DELETE_AUDIT_TABLE_IN IN INTEGER DEFAULT 1)
  AS
  BEGIN
    IF DELETE_AUDIT_TABLE_IN = 1 THEN
      DELETE FROM AUDIT_PART_OF_SPEECHES;
    END IF;
    DELETE FROM T_PART_OF_SPEECHES;
    COMMIT;
  END DELETE_ALL_PART_OF_SPEECH;
  
  PROCEDURE GET_ALL_LANGUAGE(SYS_REFCURSOR_OUT OUT SYS_REFCURSOR)
  AS
  BEGIN
    OPEN SYS_REFCURSOR_OUT FOR
      SELECT * FROM T_PART_OF_SPEECHES ORDER BY PART_OF_SPEECH_ID;
  END;
  
END PACKAGE_WORDS_PART_OF_SPEECHES;
/

PROMPT --------------CREATE OR REPLACE PACKAGE PACKAGE_WORDS_WORDS--------------;
--CREATE OR REPLACE PACKAGE PACKAGE_WORDS_WORDS
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE PACKAGE_WORDS_WORDS IS
  PROCEDURE INSERT_WORD(WORD_IN IN T_WORDS.WORD%TYPE,
                        LANGUAGE_ID_IN IN T_WORDS.LANGUAGE_ID%TYPE);
  PROCEDURE INSERT_WORD(T_WORDS_IN IN T_WORDS%ROWTYPE);
  PROCEDURE UPDATE_WORD(WORD_ID_IN IN T_WORDS.WORD_ID%TYPE,
                        WORD_IN_OUT IN OUT T_WORDS.WORD%TYPE,
                        LANGUAGE_ID_IN_OUT IN OUT T_WORDS.LANGUAGE_ID%TYPE);
  PROCEDURE DELETE_WORD(WORD_ID_IN IN T_WORDS.WORD_ID%TYPE,
                        WORD_IN IN T_WORDS.WORD%TYPE,
                        DELETE_AUDIT_TABLE_IN IN INTEGER DEFAULT 1);
  PROCEDURE SELECT_WORD_BY_ID(WORD_ID_IN IN T_WORDS.WORD_ID%TYPE,
                              WORD_OUT OUT T_WORDS%ROWTYPE);
  FUNCTION GET_SELECT_WORD_BY_WORD(WORD_IN IN T_WORDS.WORD%TYPE)
  RETURN T_WORDS.WORD_ID%TYPE;
  PROCEDURE DELETE_ALL_WORD(DELETE_AUDIT_TABLE_IN IN INTEGER DEFAULT 1);
  PROCEDURE GET_ALL_WORDS(SYS_REFCURSOR_OUT OUT SYS_REFCURSOR);
END PACKAGE_WORDS_WORDS;
/

PROMPT --------------CREATE OR REPLACE PACKAGE BODY PACKAGE_WORDS_WORDS--------------;
--CREATE OR REPLACE PACKAGE BODY PACKAGE_WORDS_WORDS
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE BODY PACKAGE_WORDS_WORDS IS
  --PROCEDURE INSERT_WORD  
  PROCEDURE INSERT_WORD(WORD_IN IN T_WORDS.WORD%TYPE,
                        LANGUAGE_ID_IN IN T_WORDS.LANGUAGE_ID%TYPE)
  AS
  BEGIN
    INSERT INTO T_WORDS(WORD, LANGUAGE_ID) VALUES(WORD_IN, LANGUAGE_ID_IN);
    COMMIT;
  END INSERT_WORD;
  
  PROCEDURE INSERT_WORD(T_WORDS_IN IN T_WORDS%ROWTYPE)
  AS
  BEGIN
	INSERT_WORD(T_WORDS_IN.WORD, T_WORDS_IN.LANGUAGE_ID);
  END INSERT_WORD;
  
--PROCEDURE UPDATE_WORD
  PROCEDURE UPDATE_WORD(WORD_ID_IN IN T_WORDS.WORD_ID%TYPE,
                        WORD_IN_OUT IN OUT T_WORDS.WORD%TYPE,
                        LANGUAGE_ID_IN_OUT IN OUT T_WORDS.LANGUAGE_ID%TYPE)
  AS
    L_WORD T_WORDS%ROWTYPE;
  BEGIN
    PACKAGE_WORDS_WORDS.SELECT_WORD_BY_ID(WORD_ID_IN, L_WORD);
    
    IF WORD_IN_OUT IS NULL THEN
      WORD_IN_OUT := L_WORD.WORD;
    END IF;
    IF LANGUAGE_ID_IN_OUT IS NULL THEN
      LANGUAGE_ID_IN_OUT := L_WORD.LANGUAGE_ID;
    END IF;
    
    UPDATE T_WORDS SET WORD = WORD_IN_OUT, LANGUAGE_ID = LANGUAGE_ID_IN_OUT
    WHERE WORD_ID = WORD_ID_IN;
    COMMIT;
    
  END UPDATE_WORD;
  
--PROCEDURE DELETE_WORD
  PROCEDURE DELETE_WORD(WORD_ID_IN IN T_WORDS.WORD_ID%TYPE,
                        WORD_IN IN T_WORDS.WORD%TYPE,
                        DELETE_AUDIT_TABLE_IN IN INTEGER DEFAULT 1)
  AS
  BEGIN
    IF DELETE_AUDIT_TABLE_IN = 1 THEN
      IF WORD_ID_IN IS NOT NULL THEN
        DELETE FROM AUDIT_WORDS WHERE WORD_ID = WORD_ID_IN;
      ELSE
        DELETE FROM AUDIT_WORDS WHERE WORD_ID = (SELECT WORD_ID FROM T_WORDS WHERE WORD = WORD_IN);
      END IF;
    END IF;
    
    IF WORD_ID_IN IS NOT NULL THEN
      DELETE FROM T_WORDS WHERE WORD_ID = WORD_ID_IN;
    ELSE
      DELETE FROM T_WORDS WHERE WORD = WORD_IN;
    END IF;
      
    COMMIT;
  END DELETE_WORD;
  
--PROCEDURE SELECT_WORD_BY_ID
  PROCEDURE SELECT_WORD_BY_ID(WORD_ID_IN IN T_WORDS.WORD_ID%TYPE,
                              WORD_OUT OUT T_WORDS%ROWTYPE)
  AS
  BEGIN
    SELECT * INTO WORD_OUT
    FROM T_WORDS
    WHERE WORD_ID = WORD_ID_IN;
  END SELECT_WORD_BY_ID;
  
  FUNCTION GET_SELECT_WORD_BY_WORD(WORD_IN IN T_WORDS.WORD%TYPE)
  RETURN T_WORDS.WORD_ID%TYPE
  AS
	L_WORD_ID T_WORDS.WORD_ID%TYPE;
  BEGIN
	SELECT WORD_ID INTO L_WORD_ID
	FROM T_WORDS
	WHERE WORD = WORD_IN;
	RETURN L_WORD_ID;
  END GET_SELECT_WORD_BY_WORD;
  
--PROCEDURE SELECT_WORD_BY_WORD
  PROCEDURE SELECT_WORD_BY_WORD(WORD_IN IN T_WORDS.WORD%TYPE,
                                WORD_OUT OUT T_WORDS%ROWTYPE)
  AS
  BEGIN 
    SELECT * INTO WORD_OUT
    FROM T_WORDS
    WHERE WORD = WORD_IN;
  END SELECT_WORD_BY_WORD;
  
--PROCEDURE DELETE_ALL_WORD
  PROCEDURE DELETE_ALL_WORD(DELETE_AUDIT_TABLE_IN IN INTEGER DEFAULT 1)
  AS
  BEGIN
    IF DELETE_AUDIT_TABLE_IN = 1 THEN
      DELETE FROM AUDIT_WORDS;
    END IF;
    
    DELETE FROM T_WORDS;
    COMMIT;
  END DELETE_ALL_WORD;
  
--PROCEDURE GET_ALL_WORDS
  PROCEDURE GET_ALL_WORDS(SYS_REFCURSOR_OUT OUT SYS_REFCURSOR)
  AS
  BEGIN
    OPEN SYS_REFCURSOR_OUT FOR
      SELECT * FROM T_WORDS ORDER BY WORD_ID;
  END GET_ALL_WORDS;
END PACKAGE_WORDS_WORDS;
/

PROMPT --------------CREATE OR REPLACE PACKAGE PACKAGE_WORDS_W_PART_OF_SPE--------------;
--CREATE OR REPLACE PACKAGE PACKAGE_WORDS_W_PART_OF_SPE
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE PACKAGE_WORDS_W_PART_OF_SPE IS
	
	PROCEDURE INSERT_WORDS_PART_OF_SPEECHES(WORD_ID_IN IN T_WORDS_PART_OF_SPEECHES.WORD_ID%TYPE,
											PART_OF_SPEECH_ID_IN IN T_WORDS_PART_OF_SPEECHES.PART_OF_SPEECH_ID%TYPE);
	PROCEDURE INSERT_WORDS_PART_OF_SPEECHES(T_WORDS_PART_OF_SPEECHES_IN IN T_WORDS_PART_OF_SPEECHES%ROWTYPE);
	FUNCTION SELECT_WORDS_PART_OF_SPEECHES(WORD_ID_IN IN T_WORDS_PART_OF_SPEECHES.WORD_ID%TYPE,
											PART_OF_SPEECH_ID_IN IN T_WORDS_PART_OF_SPEECHES.PART_OF_SPEECH_ID%TYPE)
	RETURN T_WORDS_PART_OF_SPEECHES.PART_OF_SPEECH_WORD_ID%TYPE;
	PROCEDURE DELETE_ALL_W_PART_OF_SPEECHES;
	
END PACKAGE_WORDS_W_PART_OF_SPE;
/

CREATE OR REPLACE PACKAGE BODY PACKAGE_WORDS_W_PART_OF_SPE IS
	
	PROCEDURE INSERT_WORDS_PART_OF_SPEECHES(WORD_ID_IN IN T_WORDS_PART_OF_SPEECHES.WORD_ID%TYPE,
											PART_OF_SPEECH_ID_IN IN T_WORDS_PART_OF_SPEECHES.PART_OF_SPEECH_ID%TYPE)
	AS
	BEGIN
		INSERT INTO T_WORDS_PART_OF_SPEECHES(WORD_ID, PART_OF_SPEECH_ID) VALUES(WORD_ID_IN, PART_OF_SPEECH_ID_IN);
		COMMIT;
	END INSERT_WORDS_PART_OF_SPEECHES;
	
	PROCEDURE INSERT_WORDS_PART_OF_SPEECHES(T_WORDS_PART_OF_SPEECHES_IN IN T_WORDS_PART_OF_SPEECHES%ROWTYPE)
	AS
	BEGIN
		/*INSERT INTO T_WORDS_PART_OF_SPEECHES(WORD_ID, PART_OF_SPEECH_ID) VALUES(T_WORDS_PART_OF_SPEECHES_IN.WORD_ID, T_WORDS_PART_OF_SPEECHES_IN.PART_OF_SPEECH_ID);
		COMMIT;*/
		INSERT_WORDS_PART_OF_SPEECHES(T_WORDS_PART_OF_SPEECHES_IN.WORD_ID, T_WORDS_PART_OF_SPEECHES_IN.PART_OF_SPEECH_ID);
	END INSERT_WORDS_PART_OF_SPEECHES;
	
	FUNCTION SELECT_WORDS_PART_OF_SPEECHES(WORD_ID_IN IN T_WORDS_PART_OF_SPEECHES.WORD_ID%TYPE,
											PART_OF_SPEECH_ID_IN IN T_WORDS_PART_OF_SPEECHES.PART_OF_SPEECH_ID%TYPE)
	RETURN T_WORDS_PART_OF_SPEECHES.PART_OF_SPEECH_WORD_ID%TYPE
	AS
		L_PART_OF_SPEECH_WORD_ID T_WORDS_PART_OF_SPEECHES.PART_OF_SPEECH_WORD_ID%TYPE;
	BEGIN
		SELECT PART_OF_SPEECH_WORD_ID INTO L_PART_OF_SPEECH_WORD_ID
		FROM T_WORDS_PART_OF_SPEECHES
		WHERE WORD_ID = WORD_ID_IN AND PART_OF_SPEECH_ID = PART_OF_SPEECH_ID_IN;
	RETURN L_PART_OF_SPEECH_WORD_ID;
	END SELECT_WORDS_PART_OF_SPEECHES;
											
	PROCEDURE DELETE_ALL_W_PART_OF_SPEECHES
	AS
	BEGIN
		DELETE FROM T_WORDS_PART_OF_SPEECHES;
	END DELETE_ALL_W_PART_OF_SPEECHES;
	
END PACKAGE_WORDS_W_PART_OF_SPE;
/

PROMPT --------------CREATE OR REPLACE PACKAGE PACKAGE_WORDS_T_TRANSLATES--------------;
--CREATE OR REPLACE PACKAGE PACKAGE_WORDS_T_TRANSLATES
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE PACKAGE_WORDS_T_TRANSLATES IS
	
	PROCEDURE INSERT_TRANSLATES(T_TRANSLATES_IN IN T_TRANSLATES%ROWTYPE);
	PROCEDURE DELETE_ALL_TRANSLATES;
	
END PACKAGE_WORDS_T_TRANSLATES;
/

CREATE OR REPLACE PACKAGE BODY PACKAGE_WORDS_T_TRANSLATES IS
	
	PROCEDURE INSERT_TRANSLATES(T_TRANSLATES_IN IN T_TRANSLATES%ROWTYPE)
	AS
	BEGIN
		INSERT INTO T_TRANSLATES(PART_OF_SPEECH_WORD_ID_F, PART_OF_SPEECH_WORD_ID_S) VALUES(T_TRANSLATES_IN.PART_OF_SPEECH_WORD_ID_F, T_TRANSLATES_IN.PART_OF_SPEECH_WORD_ID_S);
		COMMIT;
	END INSERT_TRANSLATES;
	
	PROCEDURE DELETE_ALL_TRANSLATES
	AS
	BEGIN
		DELETE FROM T_TRANSLATES;
	END DELETE_ALL_TRANSLATES;
	
END PACKAGE_WORDS_T_TRANSLATES;
/

SHOW ERRORS;
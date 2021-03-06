PROMPT --------------CREATE PACKAGE--------------;
--CREATE PACKAGE
CREATE OR REPLACE PACKAGE PACKAGE_WORDS_LANGUAGES IS
  PROCEDURE INSERT_LANGUAGE(LANGUAGE_NAME_IN IN T_LANGUAGES.LANGUAGE_NAME%TYPE);
  PROCEDURE UPDATE_LANGUAGE(LANGUAGE_NAME_IN IN T_LANGUAGES.LANGUAGE_NAME%TYPE, LANGUAGE_ID_IN IN T_LANGUAGES.LANGUAGE_ID%TYPE);
  PROCEDURE DELETE_LANGUAGE(LANGUAGE_NAME_IN IN T_LANGUAGES.LANGUAGE_NAME%TYPE, LANGUAGE_ID_IN IN T_LANGUAGES.LANGUAGE_ID%TYPE,
                            DELETE_AUDIT_TABLE_IN IN INTEGER DEFAULT 1);
  PROCEDURE SELECT_LANGUAGE_BY_ID(LANGUAGE_ID_IN IN T_LANGUAGES.LANGUAGE_ID%TYPE, LANGUAGE_NAME_OUT OUT T_LANGUAGES.LANGUAGE_NAME%TYPE);
  PROCEDURE SELECT_LANGUAGE_BY_NAME(LANGUAGE_NAME_IN IN T_LANGUAGES.LANGUAGE_NAME%TYPE, LANGUAGE_ID_OUT OUT T_LANGUAGES.LANGUAGE_ID%TYPE);
  PROCEDURE DELETE_ALL_LANGUAGE(DELETE_AUDIT_TABLE_IN IN INTEGER DEFAULT 1);
  PROCEDURE GET_ALL_LANGUAGE(SYS_REFCURSOR_OUT OUT SYS_REFCURSOR);
  PROCEDURE TRUNCATE_LANGUAGE;
END PACKAGE_WORDS_LANGUAGES;
/

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
  PROCEDURE TRUNCATE_PART_OF_SPEECH;
END PACKAGE_WORDS_PART_OF_SPEECHES;
/

CREATE OR REPLACE PACKAGE PACKAGE_WORDS_WORDS IS
  PROCEDURE INSERT_WORD(WORD_IN IN T_WORDS.WORD%TYPE,
                        LANGUAGE_ID_IN IN T_WORDS.LANGUAGE_ID%TYPE,
                        PART_OF_SPEECH_ID_IN IN T_WORDS.PART_OF_SPEECH_ID%TYPE);
  PROCEDURE UPDATE_WORD(WORD_ID_IN IN T_WORDS.WORD_ID%TYPE,
                        WORD_IN_OUT IN OUT T_WORDS.WORD%TYPE,
                        LANGUAGE_ID_IN_OUT IN OUT T_WORDS.LANGUAGE_ID%TYPE,
                        PART_OF_SPEECH_ID_IN_OUT IN OUT T_WORDS.PART_OF_SPEECH_ID%TYPE);
  PROCEDURE DELETE_WORD(WORD_ID_IN IN T_WORDS.WORD_ID%TYPE,
                        WORD_IN IN T_WORDS.WORD%TYPE,
                        DELETE_AUDIT_TABLE_IN IN INTEGER DEFAULT 1);
  PROCEDURE SELECT_WORD_BY_ID(WORD_ID_IN IN T_WORDS.WORD_ID%TYPE,
                              WORD_OUT OUT T_WORDS%ROWTYPE);
  PROCEDURE SELECT_WORD_BY_WORD(WORD_IN IN T_WORDS.WORD%TYPE,
                                WORD_OUT OUT T_WORDS%ROWTYPE);
  PROCEDURE DELETE_ALL_WORD(DELETE_AUDIT_TABLE_IN IN INTEGER DEFAULT 1);
  PROCEDURE GET_ALL_WORDS(SYS_REFCURSOR_OUT OUT SYS_REFCURSOR);
END PACKAGE_WORDS_WORDS;
/
SHOW ERRORS;
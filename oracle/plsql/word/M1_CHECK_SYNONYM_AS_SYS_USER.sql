SELECT * FROM SYN_T_LANGUAGES;
SELECT * FROM SYN_V_LANGUAGES;

set SERVEROUTPUT ON;
DECLARE
BEGIN
  --SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME = 'T_LANGUAGES';
  --PACKAGE_WORDS_LANGUAGES.INSERT_LANGUAGE('ENG');
  --USER_WORLD_DB.PACKAGE_WORDS_LANGUAGES.INSERT_LANGUAGE('RUS');
  --USER_WORLD_DB.PACKAGE_WORDS_LANGUAGES.INSERT_LANGUAGE('MDL');
  SYN_PACKAGE_WORDS_LANGUAGES.INSERT_LANGUAGE('UK');
  --PACKAGE_WORDS_LANGUAGES.TRUNCATE_LANGUAGE;
  --PACKAGE_WORDS_LANGUAGES.DELETE_ALL_LANGUAGE;
END;
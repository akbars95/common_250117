--user - ${db.user.user_word_db}
CREATE OR REPLACE SYNONYM T_LANGUAGES FOR ${db.user.user_word_db}.T_LANGUAGES;
CREATE OR REPLACE SYNONYM T_PART_OF_SPEECHES FOR ${db.user.user_word_db}.T_PART_OF_SPEECHES;
CREATE OR REPLACE SYNONYM T_WORDS FOR ${db.user.user_word_db}.T_WORDS;
CREATE OR REPLACE SYNONYM T_WORDS_PART_OF_SPEECHES FOR ${db.user.user_word_db}.T_WORDS_PART_OF_SPEECHES;
CREATE OR REPLACE SYNONYM T_TRANSLATES FOR ${db.user.user_word_db}.T_TRANSLATES;

CREATE OR REPLACE SYNONYM V_LANGUAGES FOR ${db.user.user_word_db}.V_LANGUAGES;
CREATE OR REPLACE SYNONYM V_PART_OF_SPEECHES FOR ${db.user.user_word_db}.V_PART_OF_SPEECHES;
CREATE OR REPLACE SYNONYM V_WORDS FOR ${db.user.user_word_db}.V_WORDS;
CREATE OR REPLACE SYNONYM V_WORDS_PART_OF_SPEECHES FOR ${db.user.user_word_db}.V_WORDS_PART_OF_SPEECHES;
CREATE OR REPLACE SYNONYM V_TRANSLATES FOR ${db.user.user_word_db}.V_TRANSLATES;
CREATE OR REPLACE SYNONYM V_S_TRANSLATES FOR ${db.user.user_word_db}.V_S_TRANSLATES;
CREATE OR REPLACE SYNONYM V_AUDIT_LANGUAGES FOR ${db.user.user_word_db}.V_AUDIT_LANGUAGES;
CREATE OR REPLACE SYNONYM V_AUDIT_PART_OF_SPEECHES FOR ${db.user.user_word_db}.V_AUDIT_PART_OF_SPEECHES;
CREATE OR REPLACE SYNONYM V_AUDIT_WORDS FOR ${db.user.user_word_db}.V_AUDIT_WORDS;

--user - ${db.user.user_user_db}
CREATE OR REPLACE SYNONYM T_ROLES FOR ${db.user.user_user_db}.T_ROLES;
CREATE OR REPLACE SYNONYM T_PRIVILEGES FOR ${db.user.user_user_db}.T_PRIVILEGES;
CREATE OR REPLACE SYNONYM T_ROLE_PRIVILEGES FOR ${db.user.user_user_db}.T_ROLE_PRIVILEGES;
CREATE OR REPLACE SYNONYM T_USERS FOR ${db.user.user_user_db}.T_USERS;
CREATE OR REPLACE SYNONYM T_USERS_HISTORY FOR ${db.user.user_user_db}.T_USERS_HISTORY;
CREATE OR REPLACE SYNONYM T_ACCOUNTS FOR ${db.user.user_user_db}.T_ACCOUNTS;
CREATE OR REPLACE SYNONYM T_PASSWORD_HISTORY FOR ${db.user.user_user_db}.T_PASSWORD_HISTORY;
CREATE OR REPLACE SYNONYM T_USER_ROLES FOR ${db.user.user_user_db}.T_USER_ROLES;
CREATE OR REPLACE SYNONYM T_USER_PRIVILEGES FOR ${db.user.user_user_db}.T_USER_PRIVILEGES;
CREATE OR REPLACE SYNONYM T_USER_ATTEMPTS FOR ${db.user.user_user_db}.T_USER_ATTEMPTS;

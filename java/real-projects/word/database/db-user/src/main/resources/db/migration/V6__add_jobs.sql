DECLARE
  L_PROGRAM_NAME VARCHAR2(100 BYTE) := 'PROGRAM_FOR_LOCKED_USERS';
  L_SCHEDULER_NAME VARCHAR2(100 BYTE) := 'SCHEDULER_FOR_LOCKED_USERS';
  L_JOB_NAME VARCHAR2(100 BYTE) := 'JOB_FOR_LOCKED_USERS';
BEGIN
  --create program for the job
  DBMS_SCHEDULER.CREATE_PROGRAM(
      program_name => L_PROGRAM_NAME,
      program_action => 'PKG_USER_MANAGEMENT.LOCKED_USER_EXPIRED_ACCOUNTS',
      program_type => 'STORED_PROCEDURE'
  );

  DBMS_SCHEDULER.ENABLE(L_PROGRAM_NAME);

  DBMS_SCHEDULER.CREATE_SCHEDULE(
    schedule_name => L_SCHEDULER_NAME,
    start_date => SYSTIMESTAMP,
    repeat_interval => 'FREQ=SECONDLY;INTERVAL=25',
    end_date => SYSTIMESTAMP + INTERVAL '30' DAY,
    comments => 'Every 25 seconds'
  );

  DBMS_SCHEDULER.CREATE_JOB(
    job_name => L_JOB_NAME,
    program_name => L_PROGRAM_NAME,
    schedule_name => L_SCHEDULER_NAME,
    auto_drop => FALSE
  );

  DBMS_SCHEDULER.ENABLE(L_JOB_NAME);

END;

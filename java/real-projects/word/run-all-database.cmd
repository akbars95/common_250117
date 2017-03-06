@ECHO OFF
cls

cd database/db-user
call mvn clean install -P flyway_clean_migrate || goto :Error

cd ../db-word
call mvn clean install -P flyway_clean_migrate || goto :Error

cd ../db-common
call mvn clean install -P flyway_clean_migrate || goto :Error

:Error
    echo Failed with error#%errorlevel%.
    cd ../../
    exit /b %errorlevel%
GOTO End

:End
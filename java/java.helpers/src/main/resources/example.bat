@echo off
echo "Running Maven clean test for errors"
SETLOCAL ENABLEDELAYEDEXPANSION
SET return_result=run
echo init = %return_result%
IF ERRORLEVEL 1 echo isok ELSE SET echo bad
IF ERRORLEVEL 1 SET return_result="Build failed" ELSE SET return_result="Build success"
echo return_result is %return_result%
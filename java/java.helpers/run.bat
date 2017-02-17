cls
::initialize error flag to undefined
set "mvnErr="
set return_result=run
echo begin %return_result%

::run your Maven command and define the error flag if there was an error
call mvn clean install || set mvnErr=1

::You can now take action if there was an error
if defined mvnErr (set return_result=error) else (set return_result=ok)
echo endresult %return_result%
git add .
git commit -m '%return_result%'
call runcrud.bat
if "ERRORLEVEL" == "0" goto openWebsite
goto fail

:fail
echo.
echo There were errors
echo Cannot open file: "runcrud.bat"

:openWebsite
start chrome --new-window http://localhost:8080/crud/tasks
:end
echo.
echo Everything is OK
echo Work is finish


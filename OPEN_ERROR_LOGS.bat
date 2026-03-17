@echo off
REM Open the error log file in Notepad
REM Shows only ERROR level logs

echo Opening Error Log...
echo.
echo Path: backend\logs\error.log
echo.
timeout /t 2

start "" "notepad" "%~dp0backend\logs\error.log"

@echo off
REM Open the application log file in Notepad
REM This file updates automatically as the application runs

echo Opening Application Log...
echo.
echo Path: backend\logs\application.log
echo.
timeout /t 2

start "" "notepad" "%~dp0backend\logs\application.log"

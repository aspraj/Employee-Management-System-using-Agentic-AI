@echo off
REM Watch logs in real-time (like tail -f)
REM Press Ctrl+C to stop watching

echo.
echo ========================================
echo REAL-TIME LOG VIEWER
echo ========================================
echo.
echo Watching: backend\logs\application.log
echo Press Ctrl+C to stop
echo.
echo Updates automatically as application runs
echo.
timeout /t 3

powershell -Command "Get-Content -Path '%~dp0backend\logs\application.log' -Tail 50 -Wait"

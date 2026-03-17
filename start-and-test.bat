@echo off
echo ========================================
echo Starting Employee Management System
echo ========================================
echo.

echo Killing any existing Java processes...
taskkill /F /IM java.exe 2>nul
timeout /t 2 /nobreak >nul

echo Starting the application...
cd "employee-management-system\backend"
start "EMS Backend" cmd /k "mvn spring-boot:run"

echo Waiting for application to start...
timeout /t 15 /nobreak >nul

echo.
echo ========================================
echo Application should be starting now!
echo ========================================
echo.
echo You can now:
echo 1. Run diagnose-employees.bat to test all APIs
echo 2. Access H2 Console at: http://localhost:8080/api/h2-console
echo 3. Use these credentials for testing:
echo    - Admin: username=admin, password=admin123
echo    - User: username=user, password=user123
echo.
echo Press any key to run diagnostics...
pause >nul

echo.
echo Running diagnostics...
cd ..
call diagnose-employees.bat
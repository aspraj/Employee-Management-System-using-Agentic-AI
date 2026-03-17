@echo off
REM Employee Management System - Startup Script for Windows

echo.
echo ==========================================
echo Employee Management System - Startup
echo ==========================================
echo.

REM Check if Java is installed
java -version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install Java 17 or higher
    pause
    exit /b 1
)

REM Check if Maven is installed
mvn -version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Maven is not installed or not in PATH
    echo Please install Maven 3.8 or higher
    pause
    exit /b 1
)

REM Check if Node.js is installed
node -v >nul 2>&1
if errorlevel 1 (
    echo ERROR: Node.js is not installed or not in PATH
    echo Please install Node.js 18 or higher
    pause
    exit /b 1
)

echo.
echo Step 1: Building Backend...
echo ==========================================
cd backend
call mvn clean install
if errorlevel 1 (
    echo ERROR: Backend build failed
    pause
    exit /b 1
)

echo.
echo Step 2: Installing Frontend Dependencies...
echo ==========================================
cd ..\frontend
call npm install
if errorlevel 1 (
    echo ERROR: Frontend dependency installation failed
    pause
    exit /b 1
)

echo.
echo ==========================================
echo Setup Complete!
echo ==========================================
echo.
echo To start the application:
echo.
echo 1. Start Backend (in a new terminal):
echo    cd backend
echo    mvn spring-boot:run
echo.
echo 2. Start Frontend (in another new terminal):
echo    cd frontend
echo    npm start
echo.
echo 3. Access the application:
echo    http://localhost:4200
echo.
echo Default ports:
echo - Backend API: http://localhost:8080/api
echo - Frontend: http://localhost:4200
echo - H2 Console: http://localhost:8080/h2-console
echo.
pause

@echo off
echo Testing Employee Management System APIs...
echo.

echo 1. Testing Health Check...
curl -X GET http://localhost:8080/api/auth/health
echo.
echo.

echo 2. Testing Login (Admin)...
curl -X POST http://localhost:8080/api/auth/login ^
  -H "Content-Type: application/json" ^
  -d "{\"username\":\"admin\",\"password\":\"admin123\"}"
echo.
echo.

echo 3. Getting JWT Token for further tests...
for /f "tokens=*" %%i in ('curl -s -X POST http://localhost:8080/api/auth/login -H "Content-Type: application/json" -d "{\"username\":\"admin\",\"password\":\"admin123\"}" ^| findstr /r "\"token\""') do set TOKEN_LINE=%%i
echo Token received: %TOKEN_LINE%
echo.

echo 4. Testing Get All Employees (without auth - should fail)...
curl -X GET http://localhost:8080/api/employees
echo.
echo.

echo 5. Testing Get All Employees (with auth)...
curl -X GET http://localhost:8080/api/employees ^
  -H "Authorization: Bearer YOUR_TOKEN_HERE"
echo.
echo.

echo API Testing Complete!
pause
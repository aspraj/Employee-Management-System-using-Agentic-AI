@echo off
setlocal enabledelayedexpansion
echo ========================================
echo Employee Management System Diagnostics
echo ========================================
echo.

echo Step 1: Testing API Health...
curl -s -w "HTTP Status: %%{http_code}\n" -X GET http://localhost:8080/api/auth/health
echo.

echo Step 2: Testing API Status...
curl -s -w "HTTP Status: %%{http_code}\n" -X GET http://localhost:8080/api/auth/status
echo.

echo Step 3: Login with Admin Credentials...
curl -s -X POST http://localhost:8080/api/auth/login ^
  -H "Content-Type: application/json" ^
  -d "{\"username\":\"admin\",\"password\":\"admin123\"}" > login_response.json

echo Login Response:
type login_response.json
echo.

echo Step 4: Extracting JWT Token...
for /f "tokens=2 delims=:," %%a in ('findstr "token" login_response.json 2^>nul') do (
    set TOKEN=%%a
    set TOKEN=!TOKEN:"=!
    set TOKEN=!TOKEN: =!
)

if "!TOKEN!"=="" (
    echo ERROR: No token found in login response
    echo Please check if login was successful
    pause
    exit /b 1
)

echo Token extracted successfully (length: !TOKEN:~0,20!...)
echo.

echo Step 5: Testing Employee Count...
curl -s -w "HTTP Status: %%{http_code}\n" -X GET http://localhost:8080/api/employees/count ^
  -H "Authorization: Bearer !TOKEN!"
echo.

echo Step 6: Testing Get All Employees...
curl -s -w "HTTP Status: %%{http_code}\n" -X GET http://localhost:8080/api/employees ^
  -H "Authorization: Bearer !TOKEN!" > employees_response.json

echo.
echo Employees Response (first 500 chars):
type employees_response.json | more
echo.

echo Step 7: Testing Specific Employee by ID...
curl -s -w "HTTP Status: %%{http_code}\n" -X GET http://localhost:8080/api/employees/1 ^
  -H "Authorization: Bearer !TOKEN!"
echo.

echo Step 8: Testing Employees by Department...
curl -s -w "HTTP Status: %%{http_code}\n" -X GET http://localhost:8080/api/employees/department/Engineering ^
  -H "Authorization: Bearer !TOKEN!"
echo.

echo Step 9: Testing without Authentication (should fail)...
curl -s -w "HTTP Status: %%{http_code}\n" -X GET http://localhost:8080/api/employees
echo.

echo ========================================
echo Diagnostics Complete!
echo ========================================
echo.
echo If you see HTTP Status 200 for steps 5-7, the API is working correctly.
echo If you see HTTP Status 403, there's an authentication issue.
echo If you see HTTP Status 500, there's a server error.
echo.
pause
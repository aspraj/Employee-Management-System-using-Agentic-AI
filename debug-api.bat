@echo off
echo ========================================
echo Employee Management System API Testing
echo ========================================
echo.

echo Step 1: Testing Health Check...
curl -s -w "Status: %%{http_code}\n" -X GET http://localhost:8080/api/auth/health
echo.

echo Step 2: Testing Login with Admin Credentials...
echo Request: POST /api/auth/login
echo Body: {"username":"admin","password":"admin123"}
curl -s -w "Status: %%{http_code}\n" -X POST http://localhost:8080/api/auth/login ^
  -H "Content-Type: application/json" ^
  -d "{\"username\":\"admin\",\"password\":\"admin123\"}" > login_response.json
echo.
echo Login Response:
type login_response.json
echo.

echo Step 3: Extracting JWT Token...
for /f "tokens=2 delims=:," %%a in ('findstr "token" login_response.json') do (
    set TOKEN=%%a
    set TOKEN=!TOKEN:"=!
    set TOKEN=!TOKEN: =!
)
echo Token extracted (first 50 chars): %TOKEN:~0,50%...
echo.

echo Step 4: Testing Employee API without Authentication (should fail)...
curl -s -w "Status: %%{http_code}\n" -X GET http://localhost:8080/api/employees
echo.

echo Step 5: Testing Employee API with Authentication...
curl -s -w "Status: %%{http_code}\n" -X GET http://localhost:8080/api/employees ^
  -H "Authorization: Bearer %TOKEN%"
echo.

echo Step 6: Testing specific employee by ID...
curl -s -w "Status: %%{http_code}\n" -X GET http://localhost:8080/api/employees/1 ^
  -H "Authorization: Bearer %TOKEN%"
echo.

echo Step 7: Testing employees by department...
curl -s -w "Status: %%{http_code}\n" -X GET http://localhost:8080/api/employees/department/Engineering ^
  -H "Authorization: Bearer %TOKEN%"
echo.

echo ========================================
echo API Testing Complete!
echo ========================================
pause
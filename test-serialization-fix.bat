@echo off
echo Testing Employee API after serialization fix...
echo.

echo Step 1: Login to get JWT token...
curl -s -X POST http://localhost:8080/api/auth/login ^
  -H "Content-Type: application/json" ^
  -d "{\"username\":\"admin\",\"password\":\"admin123\"}" > token.json

for /f "tokens=2 delims=:," %%a in ('findstr "token" token.json 2^>nul') do (
    set TOKEN=%%a
    set TOKEN=!TOKEN:"=!
    set TOKEN=!TOKEN: =!
)

if "!TOKEN!"=="" (
    echo ERROR: Login failed or no token received
    type token.json
    pause
    exit /b 1
)

echo Token received successfully
echo.

echo Step 2: Testing employee count...
curl -s -X GET http://localhost:8080/api/employees/count ^
  -H "Authorization: Bearer !TOKEN!"
echo.
echo.

echo Step 3: Testing get all employees (first 3 only)...
curl -s -X GET http://localhost:8080/api/employees ^
  -H "Authorization: Bearer !TOKEN!" | head -n 20
echo.
echo.

echo Step 4: Testing specific employee by ID...
curl -s -X GET http://localhost:8080/api/employees/1 ^
  -H "Authorization: Bearer !TOKEN!"
echo.
echo.

echo Test complete! If you see JSON data above, the serialization issue is fixed.
pause
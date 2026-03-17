# 📋 HOW TO VIEW LOGS

## ✅ Backend Logs

### Option 1: Real-time Terminal Output (Best)
When the backend is running, you'll see live logs in the terminal:
```
2026-03-17 10:25:23 [main] INFO  com.ems.config.DataInitializer - ✅ Default admin user created
2026-03-17 10:25:50 [http-nio-8080-exec-1] INFO  o.s.web.servlet.DispatcherServlet - Completed initialization
```

### Option 2: Application Log File
**Location:** `backend/logs/application.log`
**Contains:** All INFO, WARN, DEBUG logs
**View with:**
```powershell
Get-Content "c:\Users\apatel\Downloads\New AIs\employee-management-system\backend\logs\application.log" -Tail 50
```
Or open in any text editor

### Option 3: Error Log File
**Location:** `backend/logs/error.log`
**Contains:** Only ERROR level logs
**View with:**
```powershell
Get-Content "c:\Users\apatel\Downloads\New AIs\employee-management-system\backend\logs\error.log" -Tail 20
```

---

## 🌐 Frontend Logs

### Browser Console (Developer Tools)
1. Open http://localhost:4200
2. Press **F12** to open Developer Tools
3. Click the **Console** tab
4. You'll see logs like:
```
🔐 LoginComponent: Attempting login
✅ AuthService: Login successful
📊 EmployeeListComponent: Fetching employees
```

---

## 📊 What Each Log Shows

### Application Log Contains:
- ✅ Database initialization (Liquibase migrations)
- ✅ Spring Boot startup information
- ✅ User creation logs
- ✅ API request/response logs
- ✅ Employee CRUD operations
- ✅ Authentication events
- ⚠️ Warnings about configuration

### Error Log Contains:
- ❌ Only ERROR level messages
- ❌ Exceptions and stack traces
- ❌ Failed operations

### Browser Console Contains:
- 🔐 Authentication logs
- 📊 Data fetching operations
- 🎨 Component lifecycle logs
- ❌ JavaScript errors and warnings

---

## 🎯 Common Log Patterns

### Successful Login
```
2026-03-17 10:25:50 INFO  🔵 Attempting login
2026-03-17 10:25:50 INFO  📨 API Request: POST /api/auth/login
2026-03-17 10:25:50 INFO  ✅ Login successful - Username: admin, Role: ADMIN
2026-03-17 10:25:50 INFO  📤 API Response: Login successful
```

### Employee Creation
```
2026-03-17 10:25:50 INFO  🔵 Creating new employee
2026-03-17 10:25:50 INFO  📨 API Request: POST /api/employees
2026-03-17 10:25:50 INFO  ✅ Employee created successfully
2026-03-17 10:25:50 INFO  📤 API Response: Employee created
```

### Error Case
```
2026-03-17 10:25:50 ERROR ❌ User not found for ID: 999
2026-03-17 10:25:50 ERROR ❌ Error creating employee: User not found
```

---

## 🔍 Quick Commands

### View Last 50 Lines of Application Log
```powershell
Get-Content "backend\logs\application.log" -Tail 50
```

### View Last 20 Lines of Error Log
```powershell
Get-Content "backend\logs\error.log" -Tail 20
```

### Search for "admin" in logs
```powershell
Get-Content "backend\logs\application.log" | Select-String "admin"
```

### Search for errors
```powershell
Get-Content "backend\logs\application.log" | Select-String "ERROR|Exception"
```

### Follow logs in real-time (like tail -f)
```powershell
Get-Content -Path "backend\logs\application.log" -Tail 10 -Wait
```

---

## 📁 Log Files Location

Backend:
- `c:\Users\apatel\Downloads\New AIs\employee-management-system\backend\logs\application.log`
- `c:\Users\apatel\Downloads\New AIs\employee-management-system\backend\logs\error.log`

You can also open them in:
- Notepad
- VS Code
- Any text editor

---

## 💡 Tips

1. **Real-time logs are best** - Watch the terminal where backend is running to see live logs
2. **Use browser console** - Press F12 while using the app to see frontend logs
3. **Check files periodically** - Log files are created and updated automatically
4. **Search for your operation** - Use Ctrl+F in the log file to search for specific operations
5. **Filter by timestamp** - All logs have timestamps for easy tracking

---

## ✅ Current Status

The logging system is fully functional:
- Backend logs to: `logs/application.log` and `logs/error.log`
- Frontend logs to: Browser console
- All operations are logged with emojis for easy reading:
  - 🔵 Starting operation
  - 📨 API request
  - ✅ Success
  - ❌ Error
  - 📤 API response
  - ⚠️ Warning


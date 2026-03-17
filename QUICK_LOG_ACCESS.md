# 🔍 QUICK LOG ACCESS GUIDE

## ⚡ Fastest Way to View Logs

### 1️⃣ **Double-Click to Open Application Log**
```
OPEN_LOGS.bat
```
- Opens the log file in Notepad
- Shows all logs in real-time
- Automatically updating as the app runs
- Press F5 in Notepad to refresh

---

### 2️⃣ **Watch Logs in Real-Time (Terminal)**
```
WATCH_LOGS.bat
```
- Shows logs in terminal window
- Updates live as operations happen
- Shows last 50 lines constantly
- Press Ctrl+C to stop watching

---

### 3️⃣ **Open Error Logs Only**
```
OPEN_ERROR_LOGS.bat
```
- Shows only ERROR level messages
- Good for troubleshooting issues

---

## 📂 Direct File Locations

If you prefer to open the files manually:

**Application Log:**
```
c:\Users\apatel\Downloads\New AIs\employee-management-system\backend\logs\application.log
```

**Error Log:**
```
c:\Users\apatel\Downloads\New AIs\employee-management-system\backend\logs\error.log
```

---

## 🚀 How to Use

### Step 1: Start the Backend
```bash
cd "c:\Users\apatel\Downloads\New AIs\employee-management-system\backend"
$JavaPath = "$env:USERPROFILE\java17\bin"
$MavenPath = "$env:USERPROFILE\maven\bin"
$env:PATH = "$JavaPath;$MavenPath;" + $env:PATH
java -jar target/employee-management-system-1.0.0.jar
```

### Step 2: Open Logs While App Runs
**Option A - View in Notepad:**
- Double-click: `OPEN_LOGS.bat`
- Logs update automatically
- Press F5 to refresh view

**Option B - Watch in Terminal:**
- Double-click: `WATCH_LOGS.bat`
- See live logs scrolling
- Press Ctrl+C to stop

### Step 3: Use the Application
- Open http://localhost:4200
- Login with admin/admin123
- Make operations (create, update, delete employees)
- Watch logs update in real-time!

---

## 📊 What You'll See in Logs

### Login Event
```
2026-03-17 10:25:50 [http-nio-8080-exec-1] INFO  🔵 Attempting login
2026-03-17 10:25:50 [http-nio-8080-exec-1] INFO  📨 API Request: POST /auth/login
2026-03-17 10:25:50 [http-nio-8080-exec-1] INFO  ✅ Login successful - Username: admin
```

### Employee Creation
```
2026-03-17 10:25:51 [http-nio-8080-exec-2] INFO  🔵 Creating new employee
2026-03-17 10:25:51 [http-nio-8080-exec-2] INFO  ✅ Employee created successfully - ID: 1
```

### Error Example
```
2026-03-17 10:25:52 [http-nio-8080-exec-3] ERROR ❌ User not found for ID: 999
```

---

## 💡 Tips

1. **Keep logs open while testing** - Open `OPEN_LOGS.bat` in one window, run app in another
2. **Use Watch mode for live updates** - Run `WATCH_LOGS.bat` to see logs as they happen
3. **Refresh in Notepad** - Press F5 if logs don't update visually
4. **Search logs** - Use Ctrl+F in Notepad to search for keywords
5. **Check errors separately** - `OPEN_ERROR_LOGS.bat` shows only problems

---

## ✅ Log Files Auto-Created

The log files are automatically created when the application starts:
- ✅ `backend/logs/application.log` - All logs
- ✅ `backend/logs/error.log` - Errors only
- ✅ Both update in real-time as the app runs
- ✅ No manual setup needed!

---

## 🔧 Troubleshooting

**Logs not showing?**
- Make sure backend is running
- Wait 2-3 seconds for first logs to appear
- Press F5 in Notepad to refresh

**Want to clear logs?**
- Stop the backend
- Delete the `.log` files
- Restart the backend
- Fresh logs will be created


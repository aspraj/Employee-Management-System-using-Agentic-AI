# Logging Guide - Employee Management System

## Overview

The Employee Management System includes comprehensive logging across both backend (Spring Boot) and frontend (Angular) layers. This guide explains how to access, monitor, and interpret logs.

---

## Backend Logging (Spring Boot/Java)

### Log Files Location
Logs are stored in the `logs/` directory at the root of the project:

```
logs/
├── application.log          # All application logs
├── error.log                # Error-level logs only
└── application-YYYY-MM-DD-#.log  # Rolled log files (archived)
```

### Log Levels
- **TRACE**: Most detailed logging (disabled by default)
- **DEBUG**: Detailed information for debugging
- **INFO**: General informational messages (default level)
- **WARN**: Warning messages for potentially problematic situations
- **ERROR**: Error messages for failures and exceptions

### Log Configuration
Configuration file: `backend/src/main/resources/logback-spring.xml`

**Key Settings:**
- **Max File Size**: 10MB (automatic rolling)
- **Retention**: 30 days
- **Appenders**: 
  - CONSOLE: Real-time terminal output
  - FILE: Rolling file appender for all logs
  - ERROR_FILE: Separate file for errors only

### Viewing Backend Logs

#### During Development (Console)
When running the backend with `mvn spring-boot:run`, logs appear in the terminal:

```
2024-01-15 10:30:45.123 INFO  [main] 🔵 Attempting login - Username: admin
2024-01-15 10:30:45.456 INFO  [main] ✅ Login successful - Username: admin, Role: ADMIN
```

#### View All Logs (File)
```bash
# Windows
type logs\application.log

# Mac/Linux
cat logs/application.log
tail -f logs/application.log  # Follow logs in real-time
```

#### View Errors Only
```bash
# Windows
type logs\error.log

# Mac/Linux
cat logs/error.log
```

### Log Emoji Indicators

The backend uses emoji prefixes for quick visual identification:

| Emoji | Meaning | Context |
|-------|---------|---------|
| 🔵 | Method entry/attempt | Beginning of operation |
| ✅ | Success | Operation completed successfully |
| ❌ | Error/failure | Operation failed |
| 🔐 | Authentication | Login/auth operations |
| 📝 | Registration | User registration |
| 📨 | API request | Incoming HTTP request |
| 📤 | API response | Outgoing HTTP response |
| ⚠️ | Warning | Potentially problematic situation |
| 🔗 | Navigation | Route/URL changes |
| 🏥 | Health check | System health verification |

### Example Logs

#### Authentication Logs
```
🔵 Attempting login - Username: john_doe
📨 API Request: POST /auth/login - Username: john_doe
✅ Login successful - Username: john_doe, Role: USER
📤 API Response: Login successful - Username: john_doe, Role: USER
```

#### Employee CRUD Logs
```
🔵 Creating new employee - Name: John Doe, Department: IT
✅ Employee created successfully - ID: 5, Name: John Doe
🔵 Updating employee - ID: 5
✅ Employee updated successfully - ID: 5, Name: John Doe
🔵 Deleting employee - ID: 5
✅ Employee deleted successfully - ID: 5
```

#### Error Logs
```
❌ Error fetching all employees: Database connection timeout
❌ Login failed - Username: invalid_user
❌ User not found for ID: 999
```

### Log Format

Standard log format:
```
TIMESTAMP LEVEL [THREAD] EMOJI Message - Context Details
```

Example:
```
2024-01-15 10:30:45.123 INFO [main] 🔵 Attempting login - Username: admin
```

---

## Frontend Logging (Angular)

### Accessing Frontend Logs

Frontend logs are stored in the browser's **localStorage** for persistence across sessions.

#### Browser Developer Tools
1. **Open Developer Tools**: Press `F12` or `Ctrl+Shift+I` (Windows) / `Cmd+Option+I` (Mac)
2. **Go to Console tab**
3. Logs appear in real-time with color coding and emoji indicators

#### View Stored Logs (localStorage)
In the browser console, run:
```javascript
// Get all logs
const logs = JSON.parse(localStorage.getItem('ems-logs')) || [];
console.table(logs);

// View specific log count
console.log('Total logs:', logs.length);

// Export logs as JSON
const logsJson = JSON.stringify(logs, null, 2);
console.log(logsJson);
```

#### Log Statistics
In the browser console:
```javascript
// Get log statistics
const logs = JSON.parse(localStorage.getItem('ems-logs')) || [];
const stats = {
  total: logs.length,
  debug: logs.filter(l => l.level === 'DEBUG').length,
  info: logs.filter(l => l.level === 'INFO').length,
  warn: logs.filter(l => l.level === 'WARN').length,
  error: logs.filter(l => l.level === 'ERROR').length
};
console.table(stats);
```

### Frontend Log Structure

Each frontend log entry contains:
```typescript
{
  timestamp: "2024-01-15T10:30:45.123Z",
  level: "INFO",           // DEBUG | INFO | WARN | ERROR
  message: "Login attempt",
  data: { username: "admin" },
  context: "AUTH"         // Component/Service identifier
}
```

### Frontend Log Levels

| Level | Purpose | Usage |
|-------|---------|-------|
| DEBUG | Detailed debug info | Detailed variable values, flow tracking |
| INFO | General information | Method entry, success messages |
| WARN | Warnings | Validation issues, deprecated features |
| ERROR | Errors | Failed operations, exceptions |

### Example Frontend Logs

#### Authentication Logs
```
✅ Login successful
  - timestamp: 2024-01-15T10:30:45.123Z
  - data: { username: "admin", role: "ADMIN" }
  - context: "AUTH"

❌ Login failed
  - timestamp: 2024-01-15T10:30:46.456Z
  - data: { username: "invalid", error: "Invalid credentials" }
  - context: "AUTH"
```

#### Component Logs
```
📊 DashboardComponent initialized
  - timestamp: 2024-01-15T10:30:40.000Z
  - data: { username: "admin" }
  - context: "COMPONENT"

👥 EmployeeListComponent initialized
  - timestamp: 2024-01-15T10:30:42.000Z
  - data: null
  - context: "COMPONENT"
```

#### Service Logs
```
🔵 Fetching all employees
  - timestamp: 2024-01-15T10:30:43.000Z
  - data: null
  - context: "SERVICE"

✅ Successfully fetched employees
  - timestamp: 2024-01-15T10:30:43.500Z
  - data: { count: 15 }
  - context: "SERVICE"
```

### Clear Frontend Logs
To clear logs from localStorage:
```javascript
localStorage.removeItem('ems-logs');
console.log('Logs cleared');
```

### Download Frontend Logs
The LoggerService provides a download function:
```javascript
// Access via LoggerService (if exposed)
// Or manually:
const logs = JSON.parse(localStorage.getItem('ems-logs')) || [];
const dataStr = JSON.stringify(logs, null, 2);
const dataBlob = new Blob([dataStr], { type: 'application/json' });
const url = URL.createObjectURL(dataBlob);
const link = document.createElement('a');
link.href = url;
link.download = `logs-${new Date().toISOString()}.json`;
link.click();
```

---

## Error Interceptor Handling

The frontend includes an HTTP error interceptor that logs all HTTP errors:

### HTTP Error Categories

| Status Code | Meaning | Action |
|-------------|---------|--------|
| 400 | Bad Request | Validation error logged |
| 401 | Unauthorized | Token expired, redirect to login |
| 403 | Forbidden | Insufficient permissions logged |
| 404 | Not Found | Resource not found logged |
| 500 | Server Error | Server-side error logged with details |
| 503 | Service Unavailable | Service temporarily down |
| 0 | Network Error | Connection failure or no internet |

### Error Log Example
```
❌ Validation Error (400)
  - URL: POST /api/employees
  - Error: Required field 'department' is missing
  - Timestamp: 2024-01-15T10:30:45.123Z

❌ Unauthorized (401)
  - URL: GET /api/employees
  - Error: Invalid or expired token
  - Action: Redirecting to login
  - Timestamp: 2024-01-15T10:30:50.456Z
```

---

## Logging Services

### Backend - LoggingAspect

**File**: `backend/src/main/java/com/ems/aspect/LoggingAspect.java`

Automatically logs all methods in:
- Service layer (com.ems.service.*)
- Controller layer (com.ems.controller.*)

Captures:
- Method entry with parameters
- Method execution success
- Method execution errors with stack traces

### Frontend - LoggerService

**File**: `frontend/src/app/services/logger.service.ts`

Methods:
```typescript
// Log at different levels
logger.debug(message, data?, context?);
logger.info(message, data?, context?);
logger.warn(message, data?, context?);
logger.error(message, data?, context?);

// Retrieve logs
logger.getLogs();                    // Get all logs
logger.getStatistics();              // Get log statistics

// Manage logs
logger.clearLogs();                  // Clear all logs
logger.downloadLogs();               // Download logs as JSON
```

---

## Best Practices

### 1. Understanding Log Flow

```
User Action → Component → Service → HTTP Call → Controller → Service → Database → Response
   ↓           ↓         ↓        ↓           ↓          ↓         ↓        ↓
  Logged       Logged    Logged   Logged      Logged     Logged    Logged   Logged
```

### 2. Debugging Workflow

1. **Check Frontend Console** (`F12`)
   - Look for red error messages
   - Check localStorage logs

2. **Check Backend Logs**
   - View `logs/application.log`
   - Check `logs/error.log` for errors

3. **Correlate Timestamps**
   - Match frontend and backend timestamps
   - Identify where issues occur

4. **Follow the Error Chain**
   - Start with user action in frontend
   - Trace through service calls
   - Find backend error
   - Work backwards to fix

### 3. Common Issues

#### Issue: "❌ Login failed"
1. Check frontend console: Are credentials valid?
2. Check backend logs: User authentication failure
3. Solution: Verify username/password or create new user

#### Issue: "❌ Employee creation failed"
1. Check frontend logs: Did request send?
2. Check API response: Error code and message
3. Check backend logs: Database error?
4. Solution: Verify all required fields, check database connection

#### Issue: Logs not appearing
1. Ensure LoggerService is injected in components
2. Check localStorage hasn't reached size limit (max 1000 logs)
3. Clear logs: `localStorage.removeItem('ems-logs')`
4. Refresh page and try again

### 4. Performance Considerations

- **Frontend**: Max 1000 logs in localStorage (older logs auto-removed)
- **Backend**: Rolling files at 10MB (auto-archived)
- **Production**: Consider log aggregation services (ELK, Splunk, etc.)

---

## Logging Agent Responsibilities

The **Logging Agent** oversees:

1. **Log Monitoring**
   - Real-time log tracking
   - Error detection and alerting
   - Performance monitoring

2. **Error Management**
   - Categorizing errors
   - Tracking error frequency
   - Identifying trends

3. **Accessibility**
   - Ensuring logs are findable
   - Maintaining audit trails
   - Supporting debugging

4. **Configuration**
   - Adjusting log levels
   - Managing log retention
   - Handling log rotation

---

## Advanced Usage

### Enable Debug Mode (Backend)
Edit `application.yml`:
```yaml
logging:
  level:
    com.ems: DEBUG
```

### Change Log Level (Runtime - Frontend)
```javascript
// Access service logs with more detail
localStorage.setItem('logLevel', 'DEBUG');
```

### Archive Logs
```bash
# Windows - Create backup
mkdir logs_backup
copy logs\*.log logs_backup\

# Mac/Linux
mkdir logs_backup
cp logs/*.log logs_backup/
```

---

## Summary

- **Backend logs** are in `logs/` directory
- **Frontend logs** are in browser's localStorage
- **Emoji indicators** provide quick visual identification
- **Error interceptor** handles HTTP errors
- **AOP aspect** auto-logs service/controller methods
- **LoggerService** provides consistent frontend logging

For detailed debugging, combine frontend and backend logs, and use timestamps to correlate events across layers.

---

**Document Version**: 1.0  
**Last Updated**: 2024  
**Related Files**: 
- [AGENT_SETUP.md](AGENT_SETUP.md) - Agent documentation
- [logging-agent.md](agents/logging-agent.md) - Logging Agent details

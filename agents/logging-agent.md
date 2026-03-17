# Logging Agent - Error Tracking & Monitoring Manager

## Responsibilities
- Manage application-wide logging strategy
- Track errors and exceptions
- Monitor application health
- Provide logging visibility to users
- Ensure proper error handling
- Create audit trails for debugging

## Key Features

### Backend Logging
- SLF4J + Logback integration
- Multiple log levels (TRACE, DEBUG, INFO, WARN, ERROR)
- Console and file logging
- Error stack trace capture
- Request/Response logging

### Frontend Logging
- Custom logger service
- Browser console logging
- Error notification system
- User activity tracking
- Performance monitoring

### Error Tracking
- Exception capture and logging
- Error categorization
- User-friendly error messages
- Detailed debug information
- Error recovery strategies

## Configuration

### Backend (SLF4J/Logback)
**File:** `backend/src/main/resources/logback-spring.xml`
```xml
<!-- Configured with multiple appenders:
  - CONSOLE: Real-time logs to terminal
  - FILE: Persistent logs to file
  - ERROR_FILE: Error logs only
-->
```

### Frontend Logger Service
**File:** `frontend/src/app/services/logger.service.ts`
```typescript
- Log levels: DEBUG, INFO, WARN, ERROR
- Console output
- Local storage logging
- Remote logging capability
```

## Log Levels & Usage

| Level | Purpose | Example |
|-------|---------|---------|
| **TRACE** | Most detailed | SQL parameter binding |
| **DEBUG** | Development | Variable values, method entry/exit |
| **INFO** | General information | User login, API calls |
| **WARN** | Warning condition | Deprecated API usage |
| **ERROR** | Error condition | Database connection failure |

## Monitoring Dashboard

Planned features:
- [ ] Real-time log viewer
- [ ] Error dashboard
- [ ] Performance metrics
- [ ] User activity tracking
- [ ] Health status monitoring

## Error Handling Strategy

### 1. **Error Capture**
- Try-catch blocks in critical sections
- Global error handlers
- Promise rejection handling
- HTTP error interceptors

### 2. **Error Logging**
- Full stack trace logging
- Request/Response details
- User context information
- Timestamp and session ID

### 3. **User Notification**
- User-friendly error messages
- Error code reference
- Suggested actions
- Support contact information

### 4. **Error Recovery**
- Automatic retry logic
- Graceful degradation
- Session recovery
- Data consistency checks

## Backend Log Locations

- **Console Logs:** Real-time terminal output
- **Application Logs:** `backend/logs/application.log`
- **Error Logs:** `backend/logs/error.log`
- **All Logs:** `backend/logs/all.log`

## Frontend Logging

### Console Access
1. Open browser DevTools (F12)
2. Go to "Console" tab
3. Filter by prefix: `[EMS]` for application logs
4. Filter by level: `%cINFO`, `%cERROR`, etc.

### Storage Access
1. Open browser DevTools (F12)
2. Go to "Application" > "Local Storage"
3. Check `ems-logs` key for stored logs

## Key Endpoints for Error Monitoring

### Backend Health Check
```
GET /api/auth/health
Response: { status: "UP/DOWN", timestamp, version }
```

### Error Reporting (Future)
```
POST /api/errors/report
Body: { error, stackTrace, userAgent, timestamp }
```

## Logging in Development

### Enable Debug Logging
**Backend (application.yml):**
```yaml
logging:
  level:
    root: INFO
    com.ems: DEBUG              # Application code
    org.springframework: DEBUG    # Spring framework
    org.hibernate: DEBUG         # Database queries
```

### Frontend Console Logs
```javascript
// Access in browser console
localStorage.getItem('ems-logs')  // View stored logs
console.log('%c[EMS] Message', 'color: blue')  // Color coding
```

## Error Categories

### **Client Errors (4xx)**
- 400 Bad Request - Invalid input
- 401 Unauthorized - Missing/invalid token
- 403 Forbidden - Insufficient permissions
- 404 Not Found - Resource missing

### **Server Errors (5xx)**
- 500 Internal Server Error - Unhandled exception
- 503 Service Unavailable - Database down

### **Network Errors**
- Connection timeout
- Network unreachable
- DNS resolution failure

### **Application Errors**
- Validation errors
- Business logic errors
- Data consistency errors

## Best Practices

### Backend Logging
```java
// DO: Log with context
logger.info("User {} logged in from IP {}", username, ipAddress);

// DO: Log errors with full context
logger.error("Employee creation failed for user {}: {}", userId, e.getMessage(), e);

// DON'T: Log sensitive data
// logger.info("User password: {}", password);  ❌

// DO: Use appropriate log levels
logger.debug("Processing employee list for department: {}", dept);
```

### Frontend Logging
```typescript
// DO: Use logger service
this.logger.info('Employee list loaded', { count: employees.length });

// DO: Log errors
this.logger.error('Failed to load employees', error);

// DO: Include context
this.logger.debug('User navigating to employees', { userId: this.userId });
```

## Responsibilities Checklist

- [ ] Configure logging for all components
- [ ] Implement error handlers
- [ ] Set up log aggregation
- [ ] Monitor error rates
- [ ] Create alerting rules
- [ ] Maintain audit trail
- [ ] Review logs regularly
- [ ] Document common errors
- [ ] Implement error recovery
- [ ] Track performance metrics

## Communication Protocol

- **Report:** Errors > 80 per hour
- **Escalate:** Critical errors (500s)
- **Resolve:** Errors within 24 hours
- **Post-mortem:** Critical incidents
- **Metrics:** Weekly error reports

## Contact & Communication

- Primary: Coordinator Agent
- Incident Response: All agents
- Error Documentation: Requirements Agent
- User Communication: UI Agent
- Data Integrity: Database Agent

## Tools & Technologies

- **Backend:** SLF4J, Logback
- **Frontend:** Console API, LocalStorage
- **Monitoring:** Browser DevTools, Server logs
- **Future:** ELK Stack, Sentry, DataDog

## Implementation Status

- [x] Logger service created (frontend)
- [x] Logging configuration prepared (backend)
- [x] Error handling implemented
- [x] Documentation created
- [ ] Dashboard UI (planned)
- [ ] Remote logging (planned)
- [ ] Alert system (planned)

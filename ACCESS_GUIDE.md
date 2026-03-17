# 🚀 Employee Management System - Access Guide

## ✅ System Ready to Launch

Your complete Employee Management System with 5-Agent Coordination is fully configured and ready to run!

---

## 🎯 Access Links (After Starting Servers)

### **Frontend Application**
```
http://localhost:4200
```

### **Backend API**
```
http://localhost:8080/api
```

### **Database Console (H2)**
```
http://localhost:8080/h2-console
```
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Username:** `sa`
- **Password:** (leave empty)

---

## 🚀 How to Start the Application

### **Terminal 1: Start Backend**

```bash
cd "c:\Users\apatel\Downloads\New AIs\employee-management-system\backend"
mvn spring-boot:run
```

Wait for this message:
```
[INFO] Tomcat started on port(s): 8080
[INFO] Application started successfully
```

### **Terminal 2: Start Frontend**

```bash
cd "c:\Users\apatel\Downloads\New AIs\employee-management-system\frontend"
npm start
```

Wait for this message:
```
✔ Compiled successfully
Application is running at http://localhost:4200/
```

### **Then Open Browser:**
```
http://localhost:4200
```

---

## 👤 First Time Use

### Register an Account:
1. Click on "Register" button on login page
2. Enter any username and password
3. Click "Register"
4. Then Login with your credentials

### Explore Features:
1. **Dashboard** - Overview page after login
2. **Employees** - View employee list
3. **Logout** - Top right corner

---

## 📊 System Architecture

```
┌─────────────────────────────────────────┐
│        Angular Frontend (4200)          │
│  ✅ Login/Registration Component       │
│  ✅ Dashboard Component                │
│  ✅ Employee List Component            │
│  ✅ JWT Token Management              │
└────────────────┬────────────────────────┘
                 │ HTTPS + JWT Token
                 ▼
┌─────────────────────────────────────────┐
│     Spring Boot Backend (8080)          │
│  ✅ Authentication Service (JWT)       │
│  ✅ Employee Management APIs           │
│  ✅ Role-Based Access Control         │
│  ✅ Liquibase Database Migrations     │
└────────────────┬────────────────────────┘
                 │ SQL
                 ▼
┌─────────────────────────────────────────┐
│        H2 Database (In-Memory)          │
│  ✅ Users Table                        │
│  ✅ Employees Table                    │
│  ✅ Attendance Table                   │
│  ✅ Leave Requests Table               │
└─────────────────────────────────────────┘
```

---

## 🤖 5-Agent System

Your application is managed by 5 specialized agents:

### 1️⃣ **Database Agent** 
- Manages Liquibase migrations
- Maintains schema consistency
- Handles database optimization

### 2️⃣ **UI Agent**
- Develops Angular components
- Manages user interface
- Handles client-side logic

### 3️⃣ **Testing Agent**
- Writes JUnit tests
- Uses Mockito for mocking
- Maintains >80% code coverage

### 4️⃣ **Requirements Agent**
- Analyzes new features
- Documents acceptance criteria
- Ensures alignment with goals

### 5️⃣ **Coordinator Agent**
- Orchestrates communication
- Prevents inconsistencies
- Manages version control
- Ensures quality standards

📄 **See agents/ folder for detailed agent documentation**

---

## 📁 Project Structure

```
employee-management-system/
├── 📂 backend/                 # Spring Boot Application
│   ├── pom.xml                # Maven configuration
│   ├── src/main/java/com/ems/
│   │   ├── controller/        # REST API endpoints
│   │   ├── service/           # Business logic
│   │   ├── entity/            # JPA entities
│   │   ├── repository/        # Database access
│   │   ├── security/          # JWT & Security
│   │   └── config/            # Configuration
│   ├── src/main/resources/
│   │   ├── application.yml    # App config
│   │   └── db/migration/      # Liquibase migrations
│   └── src/test/java/         # JUnit tests
│
├── 📂 frontend/                # Angular Application
│   ├── src/app/
│   │   ├── components/        # UI Components
│   │   ├── services/          # API Services
│   │   ├── guards/            # Route Guards
│   │   └── interceptors/      # HTTP Interceptors
│   ├── package.json           # npm configuration
│   ├── angular.json           # Angular CLI config
│   └── tsconfig.json          # TypeScript config
│
├── 📂 agents/                  # Agent Documentation
│   ├── database-agent.md       # Database management
│   ├── ui-agent.md             # Frontend development
│   ├── testing-agent.md        # Testing strategy
│   ├── requirements-agent.md   # Feature analysis
│   └── coordinator-agent.md    # Orchestration
│
├── 📄 README.md               # Main documentation
├── 📄 AGENT_SETUP.md          # Agent system overview
├── 📄 INSTALLATION.md         # Detailed setup guide
├── 📄 QUICKSTART.md           # Quick start guide
├── 📄 setup.sh/setup.bat      # Automated setup scripts
└── 📄 ACCESS_GUIDE.md         # This file
```

---

## 🔌 API Endpoints

### Authentication
```
POST   /api/auth/register       - Create new account
POST   /api/auth/login          - Login with credentials
GET    /api/auth/health         - Health check
```

### Employees
```
GET    /api/employees           - List all employees
GET    /api/employees/{id}      - Get specific employee
POST   /api/employees           - Create new employee
PUT    /api/employees/{id}      - Update employee
DELETE /api/employees/{id}      - Delete employee
GET    /api/employees/department/{dept} - Filter by department
```

**All requests require JWT token in header:**
```
Authorization: Bearer {your-jwt-token}
```

---

## 🧪 Testing

### Run Backend Tests
```bash
cd backend
mvn test
```

### Run Frontend Tests
```bash
cd frontend
npm test
```

### View Test Coverage
```bash
cd backend
mvn clean test jacoco:report
# Open: target/site/jacoco/index.html
```

---

## 🔑 Key Features

✅ **JWT Authentication** - Secure token-based auth  
✅ **User Registration** - Self-service account creation  
✅ **Employee Management** - Full CRUD operations  
✅ **Department Filter** - Organize by department  
✅ **Role-Based Access** - Admin and User roles  
✅ **Liquibase Migrations** - Versioned database schema  
✅ **H2 Database** - Easy development database  
✅ **JUnit 5 Tests** - Comprehensive test coverage  
✅ **Mockito Mocking** - Advanced test scenarios  
✅ **Responsive UI** - Mobile-friendly design  
✅ **CORS Enabled** - Cross-origin requests allowed  
✅ **Error Handling** - User-friendly error messages  

---

## ⚙️ Configuration

### Backend Settings
**File:** `backend/src/main/resources/application.yml`

```yaml
server:
  port: 8080
  servlet:
    context-path: /api

spring:
  datasource:
    url: jdbc:h2:mem:testdb
  jpa:
    hibernate:
      ddl-auto: validate

jwt:
  secret: your-secret-key-change-in-production
  expiration: 86400000  # 24 hours
```

### Frontend Configuration
**File:** `frontend/src/app/services/auth.service.ts`

```typescript
private apiUrl = 'http://localhost:8080/api';
```

---

## 🚨 Troubleshooting

### Issue: "Port 8080 already in use"
**Solution:**
```bash
# Change in backend/src/main/resources/application.yml
server.port: 8081
```

### Issue: "Port 4200 already in use"
**Solution:**
```bash
cd frontend
ng serve --port 5200
```

### Issue: "Cannot find module '@angular/core'"
**Solution:**
```bash
cd frontend
npm install
```

### Issue: "Maven command not found"
**Solution:**
Add Maven bin folder to PATH or use full path:
```bash
C:\path\to\maven\bin\mvn spring-boot:run
```

### Issue: "CORS error in console"
**Solution:**
- Ensure backend is running on port 8080
- Verify frontend URL in backend CORS config
- Check browser console for detailed error

---

## 💡 Tips & Tricks

### Quick Start Commands
```bash
# One-time setup
cd backend && mvn clean install && cd ../frontend && npm install

# Terminal 1: Backend
cd backend && mvn spring-boot:run

# Terminal 2: Frontend
cd frontend && npm start

# Browser
http://localhost:4200
```

### Database Inspection
- Access H2 Console: http://localhost:8080/h2-console
- Run SQL queries directly
- View all tables and data
- Create test records

### API Testing
```bash
# Test login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"password123"}'

# Get employees (with token)
curl -X GET http://localhost:8080/api/employees \
  -H "Authorization: Bearer {token}"
```

### Enable Debug Logging
**File:** `backend/src/main/resources/application.yml`
```yaml
logging:
  level:
    root: INFO
    com.ems: DEBUG
```

---

## 📞 Support Resources

1. **Documentation:**
   - README.md - Overview and features
   - QUICKSTART.md - Quick start guide
   - INSTALLATION.md - Detailed setup
   - AGENT_SETUP.md - Architecture overview

2. **Agent Documentation:**
   - agents/database-agent.md
   - agents/ui-agent.md
   - agents/testing-agent.md
   - agents/requirements-agent.md
   - agents/coordinator-agent.md

3. **External Resources:**
   - [Spring Boot Docs](https://spring.io/projects/spring-boot)
   - [Angular Docs](https://angular.io/docs)
   - [JWT.io](https://jwt.io)
   - [Liquibase Docs](https://www.liquibase.org)

---

## 🎉 You're All Set!

Your Employee Management System is fully configured with:
- ✅ Spring Boot backend with JWT
- ✅ Angular frontend with responsive UI
- ✅ Liquibase database migrations
- ✅ JUnit 5 & Mockito testing
- ✅ 5-Agent coordination system

### Next Steps:
1. Open two terminals
2. Start backend: `cd backend && mvn spring-boot:run`
3. Start frontend: `cd frontend && npm start`
4. Navigate to: **http://localhost:4200**
5. Register and login
6. Enjoy your application!

---

**Questions?** Check the documentation files or agent guides in the agents/ folder.

**Happy coding! 🚀**

# 🎯 Employee Management System - Visual Quick Reference

## 📍 Where Everything Is Located

```
c:\Users\apatel\Downloads\New AIs\employee-management-system\
│
├── 🔵 START HERE: ACCESS_GUIDE.md (How to access UI)
├── 📘 README.md (Overview)
├── 📗 QUICKSTART.md (Quick start)
├── 📙 INSTALLATION.md (Detailed setup)
├── 📕 DEPLOYMENT_SUMMARY.md (Complete summary)
│
├── 🖥️  BACKEND APPLICATION
│   └── backend/
│       ├── pom.xml (Build configuration)
│       ├── src/main/resources/application.yml (Settings)
│       └── [Backend code]
│
├── 🎨 FRONTEND APPLICATION
│   └── frontend/
│       ├── package.json (Dependencies)
│       ├── angular.json (Angular config)
│       └── [Frontend code]
│
├── 🤖 AGENT DOCUMENTATION
│   └── agents/
│       ├── database-agent.md (Database management)
│       ├── ui-agent.md (Frontend development)
│       ├── testing-agent.md (Testing strategy)
│       ├── requirements-agent.md (Feature analysis)
│       └── coordinator-agent.md (Orchestration)
│
└── 🚀 SETUP SCRIPTS
    ├── setup.bat (Windows)
    └── setup.sh (Mac/Linux)
```

---

## 🚀 Quick Start Commands

### **Option 1: Fastest Way to Run**

```bash
# Terminal 1 - Backend
cd "c:\Users\apatel\Downloads\New AIs\employee-management-system\backend"
mvn spring-boot:run

# Terminal 2 - Frontend  
cd "c:\Users\apatel\Downloads\New AIs\employee-management-system\frontend"
npm start

# Browser - Open
http://localhost:4200
```

### **Option 2: Using Setup Script (Windows)**

```bash
cd "c:\Users\apatel\Downloads\New AIs\employee-management-system"
setup.bat
# Then start servers manually (see Option 1)
```

### **Option 3: Using Setup Script (Mac/Linux)**

```bash
cd /path/to/employee-management-system
chmod +x setup.sh
./setup.sh
# Then start servers manually (see Option 1)
```

---

## 🌐 Access Points

| Service | URL | Purpose |
|---------|-----|---------|
| **Frontend** | http://localhost:4200 | 🎨 User Interface |
| **Backend API** | http://localhost:8080/api | 🔌 REST API |
| **Database Console** | http://localhost:8080/h2-console | 📊 Database |
| **Health Check** | http://localhost:8080/api/auth/health | ✅ API Status |

### **Database Console Credentials**
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Username:** `sa`
- **Password:** (leave empty)

---

## 📊 System Flow

```
USER BROWSER
    ↓
    ├─→ http://localhost:4200
    │   └─→ Login Page
    │       ├─→ Register new account
    │       └─→ Login with credentials
    │
    └─→ Dashboard (after login)
        ├─→ View Employees
        ├─→ Profile
        └─→ Logout
            
    ↕ (HTTP + JWT Token)
    
ANGULAR FRONTEND
    ├─→ AuthService (login/register)
    ├─→ EmployeeService (CRUD)
    ├─→ Components (UI)
    └─→ Auth Interceptor (adds JWT)
    
    ↕ (REST API Calls)
    
SPRING BOOT BACKEND
    ├─→ AuthController
    │   ├─→ POST /auth/login
    │   ├─→ POST /auth/register
    │   └─→ GET /auth/health
    │
    ├─→ EmployeeController
    │   ├─→ GET /employees
    │   ├─→ POST /employees
    │   ├─→ PUT /employees/{id}
    │   └─→ DELETE /employees/{id}
    │
    ├─→ Security Filter
    │   └─→ JWT Token Validation
    │
    └─→ Services & Repositories
        ├─→ AuthService
        └─→ EmployeeService
        
    ↕ (JPA/SQL)
    
H2 DATABASE (In-Memory)
    ├─→ users (User accounts)
    ├─→ employees (Employee records)
    ├─→ attendance (Attendance tracking)
    └─→ leave_requests (Leave management)
```

---

## 🔑 Login Process

```
1. User enters credentials
   ├─ Username: [enter any username]
   └─ Password: [enter any password]

2. Click "Login" or "Register"
   └─→ POST /api/auth/login (or register)

3. Backend validates
   ├─ Check user exists
   ├─ Verify password
   └─ Generate JWT token

4. Frontend stores token
   └─→ localStorage.setItem('token', response.token)

5. Token added to all API requests
   └─→ Authorization: Bearer {token}

6. User can now access protected routes
   └─→ /dashboard/employees
```

---

## 📁 Important Files

### **Backend Configuration**
```
backend/src/main/resources/application.yml
└─ Server port: 8080
└─ Database: H2 (in-memory)
└─ JWT secret: your-secret-key
└─ JWT expiration: 24 hours
```

### **Frontend Configuration**
```
frontend/src/app/services/auth.service.ts
└─ API Base URL: http://localhost:8080/api
└─ Token storage: localStorage
└─ Token key: 'token'
```

### **Database Schema**
```
backend/src/main/resources/db/migration/V1__Initial_Schema.sql
└─ users table
└─ employees table
└─ attendance table
└─ leave_requests table
```

---

## 🧪 Testing

### **Run Backend Tests**
```bash
cd backend
mvn test
# Results: target/surefire-reports/
```

### **Run Frontend Tests**
```bash
cd frontend
npm test
# Press 'a' to run all tests
```

### **Test Coverage Report**
```bash
cd backend
mvn clean test jacoco:report
# Open: target/site/jacoco/index.html
```

---

## 🔧 Troubleshooting

### **Port 8080 in use?**
```yaml
# Edit: backend/src/main/resources/application.yml
server:
  port: 8081  # Change to different port
```

### **Port 4200 in use?**
```bash
ng serve --port 5200  # Use different port
```

### **Can't install dependencies?**
```bash
# Frontend
cd frontend
npm install

# Backend
cd backend
mvn clean install
```

### **Maven not found?**
- Install Maven from: https://maven.apache.org/download.cgi
- Add to PATH or use full path

### **Node modules missing?**
```bash
cd frontend
rm -rf node_modules
npm install
```

---

## 🎯 5-Agent System Overview

```
┌─────────────────────────────────────────────────────────────┐
│                    COORDINATOR AGENT                        │
│          (Manages all communication & ensures quality)      │
└─────────────────────────────────────────────────────────────┘
                              ▲
                   ┌──────────┼──────────┐
                   │          │          │
        ┌──────────▼───┐  ┌──▼──────┐  ┌▼──────────────┐
        │  Database    │  │    UI   │  │   Testing    │
        │   Agent      │  │  Agent  │  │    Agent     │
        └──────────────┘  └─────────┘  └──────────────┘
                   │          │          │
                   └──────────┼──────────┘
                              │
                   ┌──────────▼──────────┐
                   │ Requirements Agent  │
                   │  (Feature Analysis) │
                   └─────────────────────┘
```

**Agent Responsibilities:**
1. 🗄️ **Database Agent** - Liquibase migrations & schema
2. 🎨 **UI Agent** - Angular components & services
3. 🧪 **Testing Agent** - JUnit & Mockito tests
4. 📋 **Requirements Agent** - Feature analysis
5. 🎯 **Coordinator Agent** - Orchestration & communication

See `agents/` folder for detailed documentation.

---

## 💾 Database Inspection

### **Via H2 Console**
1. Open: http://localhost:8080/h2-console
2. Enter JDBC URL: `jdbc:h2:mem:testdb`
3. Click "Connect"
4. View tables and data
5. Run SQL queries

### **Via Terminal**
```bash
# Insert test employee
INSERT INTO employees (user_id, employee_id, department, position, salary) 
VALUES (1, 'EMP001', 'IT', 'Developer', 75000);

# View all employees
SELECT * FROM employees;

# Check users
SELECT * FROM users;
```

---

## 🔌 API Endpoints Quick Reference

### **Authentication**
```
POST   /api/auth/register          Register account
POST   /api/auth/login             Login user
GET    /api/auth/health            Health check
```

### **Employees**
```
GET    /api/employees              List all
GET    /api/employees/{id}         Get one
GET    /api/employees/department/{dept}  Filter
POST   /api/employees?userId={id}  Create
PUT    /api/employees/{id}         Update
DELETE /api/employees/{id}         Delete
```

### **Required Header (except /auth)**
```
Authorization: Bearer YOUR_JWT_TOKEN
```

---

## 📚 Documentation Map

```
START HERE
   ↓
ACCESS_GUIDE.md (How to run & access)
   ↓
QUICKSTART.md (Quick start guide)
   ↓
Either:
├─→ INSTALLATION.md (Detailed setup)
├─→ DEPLOYMENT_SUMMARY.md (Complete overview)
└─→ AGENT_SETUP.md (Architecture)
   ↓
agents/ folder (Specific agent docs)
```

---

## ✅ Pre-Launch Checklist

Before running the application:

- [ ] Java 8+ installed (`java -version`)
- [ ] Maven installed (`mvn -version`)
- [ ] Node.js 11.8+ installed (`node -v`)
- [ ] npm 6+ installed (`npm -v`)
- [ ] Ports 8080 and 4200 are free
- [ ] Access to c:\Users\apatel\Downloads\New AIs\employee-management-system\
- [ ] Read ACCESS_GUIDE.md

---

## 🚀 Launch Sequence

```
Step 1: Start Backend
└─→ cd backend
└─→ mvn spring-boot:run
└─→ Wait for "Tomcat started on port 8080"

Step 2: Start Frontend
└─→ cd frontend
└─→ npm start
└─→ Wait for "Application is running..."

Step 3: Open Browser
└─→ http://localhost:4200

Step 4: Register & Explore
└─→ Create account
└─→ Login
└─→ View employees
```

---

## 🎉 Success Indicators

✅ **Backend Started:**
```
[INFO] Tomcat started on port(s): 8080 (http)
[INFO] Started EmployeeManagementSystemApplication
```

✅ **Frontend Started:**
```
✔ Compiled successfully
● Serving Angular application at http://localhost:4200
```

✅ **Login Works:**
```
- Can register new account
- Can login with credentials
- Can see dashboard after login
```

✅ **Database Works:**
```
- Can access H2 console at http://localhost:8080/h2-console
- Can see tables (users, employees, etc.)
- Can view stored data
```

---

## 📞 Need Help?

1. **Setup Issues:** Read INSTALLATION.md
2. **How to Use:** Read QUICKSTART.md or ACCESS_GUIDE.md
3. **Architecture:** Read AGENT_SETUP.md
4. **Specific Questions:** Check agents/*.md files
5. **API Issues:** Check API endpoints section above

---

## 🎯 What's Next After Launch?

1. ✅ Explore the employee management features
2. ✅ Try creating a new employee
3. ✅ Check H2 database console
4. ✅ Run unit tests
5. ✅ Read agent documentation
6. ✅ Extend with new features

---

## 🌟 Key Highlights

✨ **Modern Stack:** Spring Boot 3.2 + Angular 17  
✨ **Secure:** JWT authentication + BCrypt passwords  
✨ **Scalable:** Liquibase migrations + tested code  
✨ **Documented:** Complete agent coordination system  
✨ **Ready:** All components working out of the box  

---

**Ready to launch? 🚀**

```bash
# Terminal 1
cd backend && mvn spring-boot:run

# Terminal 2
cd frontend && npm start

# Browser
http://localhost:4200
```

**Enjoy your Employee Management System!**

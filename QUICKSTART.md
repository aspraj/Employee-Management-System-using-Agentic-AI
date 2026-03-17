# Quick Start Guide

## Prerequisites Verification

✅ Java 8+ is installed
✅ Node.js 11.8+ and npm are installed

## Application Links

### After starting the servers, access:

**Frontend Application:** http://localhost:4200
**Backend API:** http://localhost:8080/api
**H2 Database Console:** http://localhost:8080/h2-console

## How to Start the Application

### Step 1: Start the Backend

```bash
cd backend
mvn spring-boot:run
```

Expected output:
```
[INFO] BUILD SUCCESS
[INFO] Tomcat started on port(s): 8080
```

### Step 2: Start the Frontend (in a new terminal)

```bash
cd frontend
npm install
npm start
```

Expected output:
```
✔ Compiled successfully in 5.23 s
Application is running at: http://localhost:4200/
```

## Using the Application

### Login/Register

1. Open http://localhost:4200 in your browser
2. Register a new account with:
   - Username: testuser
   - Password: password123

3. Click "Register" to create account
4. Login with your credentials
5. You'll be redirected to the Dashboard

### View Employees

1. After login, click "Employees" in the navbar
2. View the list of employees
3. Employee data is loaded from the backend API

## Key Features

✅ JWT Authentication
✅ User Registration & Login
✅ Employee Management (View, Create, Update, Delete)
✅ Role-Based Access Control
✅ Responsive UI
✅ Secure API with token authentication

## Database

- **Type:** H2 (in-memory)
- **Access:** http://localhost:8080/h2-console
- **Credentials:**
  - URL: jdbc:h2:mem:testdb
  - Username: sa
  - Password: (empty)

## Architecture

```
┌─────────────────────────────────┐
│   Angular Frontend (Port 4200)  │
│   - Login Component             │
│   - Dashboard Component         │
│   - Employee List Component     │
└────────────┬────────────────────┘
             │
             │ HTTP (JWT Token)
             │
┌────────────▼────────────────────┐
│ Spring Boot Backend (Port 8080) │
│   - Auth Controller             │
│   - Employee Controller         │
│   - JWT Security Filter         │
│   - H2 Database                 │
└─────────────────────────────────┘
```

## Test Accounts

### Creating Test Data

After login:
1. The H2 console at http://localhost:8080/h2-console allows you to:
   - Create test employees
   - View database structure
   - Run SQL queries

Sample INSERT query:
```sql
INSERT INTO employees (user_id, employee_id, department, position, salary, phone, address, city, state, zip_code, country, hire_date, created_at, updated_at) 
VALUES (1, 'EMP001', 'IT', 'Software Engineer', 75000, '555-0001', '123 Main St', 'Springfield', 'IL', '62701', 'USA', CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
```

## Troubleshooting

### Port Already in Use

**Backend (8080):**
```bash
# Change in backend/src/main/resources/application.yml
server.port: 8081
```

**Frontend (4200):**
```bash
ng serve --port 5200
```

### Dependencies Not Found

```bash
# Frontend
cd frontend
npm install

# Backend (if having issues)
cd backend
mvn clean install
```

### Can't Connect to Backend

1. Ensure backend is running on port 8080
2. Check firewall settings
3. Verify API URL in `frontend/src/app/services/auth.service.ts`
4. Check browser console for CORS errors

## API Documentation

### Authentication Endpoints

- **Register:**
  ```
  POST /api/auth/register
  Body: { username, password, email, firstName, lastName, role, active }
  ```

- **Login:**
  ```
  POST /api/auth/login
  Body: { username, password }
  Response: { token, username, email, role, userId }
  ```

### Employee Endpoints

- **List All:** `GET /api/employees`
- **Get One:** `GET /api/employees/{id}`
- **Create:** `POST /api/employees?userId={userId}`
- **Update:** `PUT /api/employees/{id}`
- **Delete:** `DELETE /api/employees/{id}`
- **By Department:** `GET /api/employees/department/{department}`

All endpoints require JWT token in Authorization header:
```
Authorization: Bearer {token}
```

## Agent Coordination

This application is managed by 5 specialized agents:

1. **Database Agent:** Manages Liquibase migrations
2. **UI Agent:** Develops Angular components
3. **Testing Agent:** Maintains JUnit and Mockito tests
4. **Requirements Agent:** Analyzes new features
5. **Coordinator Agent:** Ensures communication

See AGENT_SETUP.md for detailed information.

## Next Steps

1. ✅ Start Backend and Frontend
2. ✅ Register an account
3. ✅ Login to dashboard
4. ✅ Explore employee management
5. ✅ View database in H2 Console
6. ✅ Run tests with: `mvn test` (backend) or `npm test` (frontend)

## Useful Commands

```bash
# Backend Commands
cd backend
mvn clean install          # Build project
mvn spring-boot:run        # Run application
mvn test                   # Run tests
mvn clean package          # Build production JAR

# Frontend Commands
cd frontend
npm install                # Install dependencies
npm start                  # Start dev server
npm test                   # Run tests
npm run build              # Build production bundle
```

## Support & Documentation

- **Main Documentation:** README.md
- **Architecture Guide:** AGENT_SETUP.md
- **Installation Guide:** INSTALLATION.md
- **Agent Docs:** See agents/ folder

---

**Application Status:** ✅ Ready to Run

Start both services and access http://localhost:4200 to begin!

# Installation and Setup Guide

## Quick Start

### Option 1: Automated Setup (Recommended)

#### Windows
```bash
setup.bat
```

#### Mac/Linux
```bash
chmod +x setup.sh
./setup.sh
```

### Option 2: Manual Setup

## Prerequisites

Before starting, ensure you have installed:

1. **Java Development Kit (JDK) 17+**
   - Download: https://www.oracle.com/java/technologies/downloads/
   - Verify: `java -version`

2. **Maven 3.8+**
   - Download: https://maven.apache.org/download.cgi
   - Verify: `mvn -version`

3. **Node.js 18+**
   - Download: https://nodejs.org/
   - Verify: `node -v` and `npm -v`

4. **Git** (for version control)
   - Download: https://git-scm.com/

## Step-by-Step Setup

### 1. Backend Setup

```bash
cd backend

# Build the project
mvn clean install

# Run the backend
mvn spring-boot:run
```

Expected output:
```
Tomcat started on port(s): 8080
Employee Management System started successfully
```

Backend is now available at: `http://localhost:8080`

### 2. Frontend Setup

Open a new terminal and run:

```bash
cd frontend

# Install dependencies
npm install

# Start the development server
npm start
```

Expected output:
```
✔ Compiled successfully
Application bundle generated successfully
```

Frontend is now available at: `http://localhost:4200`

## Accessing the Application

1. Open your browser
2. Navigate to: `http://localhost:4200`
3. Register a new account or login
4. Explore the employee management features

## Database Access

H2 Database Console is available at: `http://localhost:8080/h2-console`

- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (leave empty)

## Default API Endpoints

### Authentication
- POST `http://localhost:8080/api/auth/register` - Register
- POST `http://localhost:8080/api/auth/login` - Login
- GET `http://localhost:8080/api/auth/health` - Health Check

### Employees
- GET `http://localhost:8080/api/employees` - List all
- GET `http://localhost:8080/api/employees/{id}` - Get one
- POST `http://localhost:8080/api/employees` - Create
- PUT `http://localhost:8080/api/employees/{id}` - Update
- DELETE `http://localhost:8080/api/employees/{id}` - Delete

## Running Tests

### Backend Tests
```bash
cd backend
mvn test
```

### Frontend Tests
```bash
cd frontend
npm test
```

## Building for Production

### Backend
```bash
cd backend
mvn clean package -DskipTests
# JAR file will be in: target/employee-management-system-1.0.0.jar
```

### Frontend
```bash
cd frontend
ng build --configuration production
# Built files will be in: dist/employee-management-system
```

## Troubleshooting

### Issue: Port 8080 is already in use
**Solution:** 
- Change backend port in `backend/src/main/resources/application.yml`
- Change `server.port: 8081`

### Issue: Port 4200 is already in use
**Solution:**
```bash
ng serve --port 5200
```

### Issue: Node modules not found
**Solution:**
```bash
cd frontend
npm install
```

### Issue: Backend fails to start
**Solution:**
1. Check Java installation: `java -version`
2. Check logs for detailed errors
3. Ensure H2 database dependencies are installed

### Issue: Cannot connect to backend from frontend
**Solution:**
1. Ensure backend is running on port 8080
2. Check CORS settings in `SecurityConfig.java`
3. Verify API base URL in `auth.service.ts`

## Environment Variables

You can set environment variables for configuration:

### Backend (application.yml)
```yaml
server.port: 8080
spring.datasource.url: jdbc:h2:mem:testdb
jwt.secret: your-secret-key
jwt.expiration: 86400000
```

### Frontend (.env)
```
API_BASE_URL=http://localhost:8080/api
```

## Project Structure

```
employee-management-system/
├── backend/
│   ├── src/main/java/com/ems/     # Java source code
│   ├── src/main/resources/         # Configuration & migrations
│   ├── src/test/java/             # Unit tests
│   └── pom.xml                    # Maven configuration
├── frontend/
│   ├── src/app/                   # Angular components & services
│   ├── src/assets/                # Static assets
│   └── angular.json               # Angular configuration
├── agents/                        # Agent coordination documents
├── README.md                      # Project documentation
├── AGENT_SETUP.md                 # Agent orchestration guide
└── setup.sh/setup.bat            # Setup scripts
```

## Next Steps

1. **Create Your First Employee**
   - Login to the application
   - Navigate to Employees section
   - Click "Add Employee" (when available)

2. **Manage Departments**
   - Use the department filter
   - Sort by different columns

3. **Monitor Performance**
   - Check backend logs
   - Review frontend console
   - Monitor test coverage

## Getting Help

1. **Backend Issues**
   - Check `backend/target/logs/` for detailed logs
   - Review Spring Boot documentation

2. **Frontend Issues**
   - Check browser console for errors
   - Review Angular documentation

3. **Database Issues**
   - Access H2 Console at `http://localhost:8080/h2-console`
   - Check migration files in `backend/src/main/resources/db/`

## Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Angular Documentation](https://angular.io/docs)
- [JWT Documentation](https://tools.ietf.org/html/rfc7519)
- [Liquibase Documentation](https://www.liquibase.org/get-started)

## Support

For issues or questions:
1. Check the README.md
2. Review AGENT_SETUP.md for architecture
3. Check logs and error messages
4. Review respective agent documentation in `agents/` folder

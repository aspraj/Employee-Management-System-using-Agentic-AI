Agent Orchestration System for Employee Management System
=========================================================

## Overview
This system uses 5 specialized agents that work together to manage different aspects of the Employee Management System development and deployment.

## Agent Responsibilities

### 1. Database Agent (Liquibase Manager)
- Manages database schema using Liquibase migrations
- Handles database initialization
- Creates and updates database migrations
- Maintains data integrity and schema consistency
- Location: `backend/src/main/resources/db/`

### 2. Angular UI Agent
- Manages the frontend Angular application
- Develops UI components
- Implements user interfaces
- Handles routing and navigation
- Manages state and services
- Location: `frontend/src/app/`

### 3. Testing Agent (JUnit & Mockito)
- Writes and maintains unit tests
- Creates test cases with Mockito
- Ensures code quality and coverage
- Validates backend functionality
- Location: `backend/src/test/java/`

### 4. Requirements Analysis Agent
- Analyzes new requirements
- Documents feature requests
- Validates business logic
- Creates acceptance criteria
- Ensures alignment with project goals

### 5. Communication Coordinator Agent
- Ensures all agents communicate effectively
- Prevents duplications and inconsistencies
- Manages version control and commits
- Coordinates deployment process
- Maintains documentation

## Project Structure

```
employee-management-system/
├── backend/                    # Spring Boot Application
│   ├── pom.xml
│   ├── src/main/java/com/ems/
│   │   ├── controller/        # REST Controllers
│   │   ├── service/           # Business Logic
│   │   ├── entity/            # JPA Entities
│   │   ├── repository/        # Data Access
│   │   ├── security/          # JWT & Security
│   │   └── config/            # Configuration
│   ├── src/main/resources/
│   │   ├── application.yml    # Configuration
│   │   └── db/                # Database migrations (Liquibase)
│   └── src/test/java/         # Unit Tests
├── frontend/                   # Angular Application
│   ├── src/app/
│   │   ├── components/        # UI Components
│   │   ├── services/          # API Services
│   │   ├── guards/            # Route Guards
│   │   └── interceptors/      # HTTP Interceptors
│   ├── package.json
│   └── angular.json
├── agents/                     # Agent Orchestration
│   ├── database-agent.md
│   ├── ui-agent.md
│   ├── testing-agent.md
│   ├── requirements-agent.md
│   └── coordinator-agent.md
└── README.md
```

## API Endpoints

### Authentication
- `POST /api/auth/login` - User login
- `POST /api/auth/register` - User registration
- `GET /api/auth/health` - Health check

### Employees
- `GET /api/employees` - Get all employees
- `GET /api/employees/{id}` - Get employee by ID
- `GET /api/employees/employee-id/{employeeId}` - Get by employee ID
- `GET /api/employees/department/{department}` - Get by department
- `POST /api/employees` - Create employee
- `PUT /api/employees/{id}` - Update employee
- `DELETE /api/employees/{id}` - Delete employee

## Technology Stack

### Backend
- Java 17
- Spring Boot 3.2.0
- Spring Security with JWT
- Spring Data JPA
- Liquibase for database migrations
- H2 Database (Development)
- JUnit 5 & Mockito for testing
- Maven

### Frontend
- Angular 17
- TypeScript
- RxJS
- Angular Router
- Angular Forms
- CSS

## Running the Application

### Prerequisites
- Java 17+
- Node.js 18+
- Maven 3.8+
- npm or yarn

### Backend Setup
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

The backend will start on `http://localhost:8080`

### Frontend Setup
```bash
cd frontend
npm install
npm start
```

The frontend will be available on `http://localhost:4200`

## Login Credentials

Default test users will be created through the registration endpoint.
Use the UI to register a new user account.

## Database

- Type: H2 (in-memory for development)
- Migrations: Liquibase
- Migration files: `backend/src/main/resources/db/migration/`

## Testing

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

## Agent Communication Protocol

All agents should:
1. Update documentation before making changes
2. Test all changes locally
3. Commit with clear messages
4. Follow code style guidelines
5. Validate against requirements
6. Report progress to coordinator

## Deployment

The application is configured for local development. For production:
1. Update JWT secret in application.yml
2. Configure production database
3. Build optimized Angular bundle
4. Deploy backend and frontend separately
5. Set up proper environment variables

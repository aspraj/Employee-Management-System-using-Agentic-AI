# Employee Management System

A comprehensive employee management system built with Spring Boot backend and Angular frontend, featuring JWT authentication and database migrations using Liquibase.

## Features

- **JWT Authentication**: Secure login and registration
- **Employee Management**: CRUD operations for employee records
- **Department Management**: Organize employees by department
- **Attendance Tracking**: Track employee attendance
- **Leave Management**: Manage employee leave requests
- **Role-Based Access Control**: Admin and User roles
- **Responsive UI**: Modern Angular-based dashboard

## Quick Start

### Prerequisites
- Java 17 or higher
- Node.js 18+ and npm
- Maven 3.8+

### Backend Setup

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

Backend runs on: `http://localhost:8080`

### Frontend Setup

```bash
cd frontend
npm install
npm start
```

Frontend runs on: `http://localhost:4200`

### Access the Application

Once both services are running:
1. Open your browser and go to `http://localhost:4200`
2. Register a new account or use demo credentials
3. Login to access the dashboard
4. View and manage employees

## Project Structure

- **backend/**: Spring Boot application with REST APIs
- **frontend/**: Angular single-page application
- **agents/**: Agent orchestration system
- **docs/**: Project documentation

## API Documentation

### Authentication Endpoints
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - User login
- `GET /api/auth/health` - Health check

### Employee Endpoints
- `GET /api/employees` - List all employees
- `GET /api/employees/{id}` - Get employee details
- `POST /api/employees` - Create new employee
- `PUT /api/employees/{id}` - Update employee
- `DELETE /api/employees/{id}` - Delete employee

## Technology Stack

### Backend
- Java 17
- Spring Boot 3.2.0
- Spring Security with JWT
- Spring Data JPA
- Liquibase
- H2 Database
- JUnit 5 & Mockito

### Frontend
- Angular 17
- TypeScript
- RxJS
- Angular Router

## Database

The application uses H2 database in development mode with Liquibase for schema management.

Database migrations are located in: `backend/src/main/resources/db/migration/`

## Testing

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

## Security

- JWT-based authentication with 24-hour token expiration
- Password encryption using BCrypt
- Role-based access control
- CORS configured for development

## Troubleshooting

### Port already in use
- Backend: Change port in `application.yml`
- Frontend: Use `ng serve --port 5200`

### Database connection issues
- H2 console: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa` / Password: (empty)

### CORS errors
- Configure allowed origins in `SecurityConfig.java`
- Frontend proxy is configured in `angular.json`

## Contributing

Follow the agent protocols for development:
1. Update requirements documentation
2. Test changes locally
3. Write/update tests
4. Create clear commit messages
5. Coordinate with team via communication agent

## License

This project is licensed under the MIT License.

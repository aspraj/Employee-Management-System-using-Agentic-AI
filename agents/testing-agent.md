# Testing Agent - JUnit & Mockito Manager

## Responsibilities
- Write and maintain unit tests
- Implement test cases using JUnit 5
- Create mocks using Mockito
- Ensure code coverage targets
- Validate test quality
- Coordinate database setup for testing

## Key Files
- `backend/src/test/java/com/ems/` - Test classes
- `backend/pom.xml` - Test dependencies

## Current Test Classes
1. **AuthServiceTest** - Authentication service tests
2. **EmployeeServiceTest** - Employee service tests

## Test Dependencies
- JUnit 5 (Jupiter)
- Mockito 5.x
- Spring Test
- Spring Security Test

## Testing Guidelines

### Test Structure
- One test class per service class
- Naming: `{ClassName}Test`
- Method naming: `test{ScenarioDescription}`

### Mockito Usage
- Mock external dependencies
- Use `@ExtendWith(MockitoExtension.class)` for JUnit 5
- Use `@Mock` and `@InjectMocks` annotations
- Verify method calls with `verify()`

### Test Coverage Targets
- Service Layer: >80%
- Controller Layer: >70%
- Repository Layer: >60%

## Test Execution
```bash
cd backend
mvn test                    # Run all tests
mvn test -Dtest=AuthServiceTest    # Run specific test
mvn clean test jacoco:report       # Generate coverage report
```

## Test Report Location
- Jacoco Coverage: `backend/target/site/jacoco/index.html`
- Surefire Report: `backend/target/surefire-reports/`

## Responsibilities Checklist
- [ ] Write unit tests for new services
- [ ] Maintain >80% code coverage
- [ ] Update tests when code changes
- [ ] Create integration tests
- [ ] Performance testing
- [ ] Mock external APIs
- [ ] Document test scenarios
- [ ] Coordinate with Database Agent for test data

## Common Issues & Solutions

### Mockito Issues
- Use `MockitoAnnotations.openMocks(this)` if auto-initialization fails
- Ensure `@ExtendWith(MockitoExtension.class)` is used with JUnit 5

### Spring Context
- Use `@SpringBootTest` for integration tests
- Use `@MockBean` for Spring beans in integration tests

## Contact & Communication
- Primary: Coordinator Agent
- Database Setup: Database Agent (for test data)
- Coverage Reports: Coordinator Agent

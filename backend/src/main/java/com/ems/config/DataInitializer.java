package com.ems.config;

import com.ems.entity.User;
import com.ems.entity.Employee;
import com.ems.repository.UserRepository;
import com.ems.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Slf4j
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Random random = new Random();

    private final List<String> firstNames = Arrays.asList(
        "John", "Jane", "Michael", "Sarah", "David", "Emily", "Robert", "Lisa", "James", "Maria",
        "William", "Jennifer", "Richard", "Linda", "Joseph", "Elizabeth", "Thomas", "Barbara", "Christopher", "Susan",
        "Daniel", "Jessica", "Paul", "Karen", "Mark", "Nancy", "Donald", "Betty", "Steven", "Helen",
        "Kenneth", "Sandra", "Joshua", "Donna", "Kevin", "Carol", "Brian", "Ruth", "George", "Sharon",
        "Edward", "Michelle", "Ronald", "Laura", "Timothy", "Sarah", "Jason", "Kimberly", "Jeffrey", "Deborah"
    );

    private final List<String> lastNames = Arrays.asList(
        "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez",
        "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson", "Thomas", "Taylor", "Moore", "Jackson", "Martin",
        "Lee", "Perez", "Thompson", "White", "Harris", "Sanchez", "Clark", "Ramirez", "Lewis", "Robinson",
        "Walker", "Young", "Allen", "King", "Wright", "Scott", "Torres", "Nguyen", "Hill", "Flores",
        "Green", "Adams", "Nelson", "Baker", "Hall", "Rivera", "Campbell", "Mitchell", "Carter", "Roberts"
    );

    private final List<String> departments = Arrays.asList(
        "Engineering", "Marketing", "Sales", "Human Resources", "Finance", "Operations", 
        "Customer Service", "IT Support", "Research & Development", "Quality Assurance"
    );

    private final List<String> positions = Arrays.asList(
        "Software Engineer", "Senior Developer", "Project Manager", "Business Analyst", "Marketing Specialist",
        "Sales Representative", "HR Manager", "Financial Analyst", "Operations Manager", "Customer Support",
        "IT Specialist", "QA Engineer", "Research Scientist", "Data Analyst", "Product Manager",
        "UX Designer", "DevOps Engineer", "Technical Lead", "Account Manager", "Consultant"
    );

    private final List<String> cities = Arrays.asList(
        "New York", "Los Angeles", "Chicago", "Houston", "Phoenix", "Philadelphia", "San Antonio", "San Diego",
        "Dallas", "San Jose", "Austin", "Jacksonville", "Fort Worth", "Columbus", "Charlotte", "San Francisco",
        "Indianapolis", "Seattle", "Denver", "Washington", "Boston", "El Paso", "Nashville", "Detroit", "Oklahoma City"
    );

    private final List<String> states = Arrays.asList(
        "NY", "CA", "IL", "TX", "AZ", "PA", "TX", "CA", "TX", "CA", "TX", "FL", "TX", "OH", "NC", "CA",
        "IN", "WA", "CO", "DC", "MA", "TX", "TN", "MI", "OK"
    );

    @Override
    public void run(String... args) throws Exception {
        try {
            // Create admin user if not exists
            if (!userRepository.existsByUsername("admin")) {
                User admin = User.builder()
                    .username("admin")
                    .email("admin@company.com")
                    .password(passwordEncoder.encode("admin123"))
                    .firstName("System")
                    .lastName("Administrator")
                    .role("ADMIN")
                    .active(true)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
                userRepository.save(admin);
                log.info("✅ Default admin user created - Username: admin, Password: admin123");
            }

            // Create regular user if not exists
            if (!userRepository.existsByUsername("user")) {
                User user = User.builder()
                    .username("user")
                    .email("user@company.com")
                    .password(passwordEncoder.encode("user123"))
                    .firstName("Regular")
                    .lastName("User")
                    .role("USER")
                    .active(true)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
                userRepository.save(user);
                log.info("✅ Default user created - Username: user, Password: user123");
            }

            // Create 50 employee entries if not already created
            long currentEmployeeCount = employeeRepository.count();
            log.info("📊 Current employee count: {}", currentEmployeeCount);
            
            if (currentEmployeeCount == 0) {
                log.info("🔄 Creating 50 employee entries...");
                
                for (int i = 1; i <= 50; i++) {
                    try {
                        // Create user for employee
                        String firstName = firstNames.get(random.nextInt(firstNames.size()));
                        String lastName = lastNames.get(random.nextInt(lastNames.size()));
                        String username = (firstName + "." + lastName + i).toLowerCase();
                        String email = username + "@company.com";

                        if (!userRepository.existsByUsername(username)) {
                            User employeeUser = User.builder()
                                .username(username)
                                .email(email)
                                .password(passwordEncoder.encode("password123"))
                                .firstName(firstName)
                                .lastName(lastName)
                                .role("USER")
                                .active(true)
                                .createdAt(LocalDateTime.now())
                                .updatedAt(LocalDateTime.now())
                                .build();
                            employeeUser = userRepository.save(employeeUser);
                            log.debug("✅ Created user: {} (ID: {})", username, employeeUser.getId());

                            // Create employee record
                            String department = departments.get(random.nextInt(departments.size()));
                            String position = positions.get(random.nextInt(positions.size()));
                            String city = cities.get(random.nextInt(cities.size()));
                            String state = states.get(random.nextInt(states.size()));

                            Employee employee = Employee.builder()
                                .user(employeeUser)
                                .employeeId("EMP" + String.format("%03d", i))
                                .department(department)
                                .position(position)
                                .salary(generateRandomSalary(position))
                                .hireDate(generateRandomHireDate())
                                .phone(generateRandomPhone())
                                .address(generateRandomAddress())
                                .city(city)
                                .state(state)
                                .zipCode(generateRandomZipCode())
                                .country("USA")
                                .dateOfBirth(generateRandomDateOfBirth())
                                .createdAt(LocalDateTime.now())
                                .updatedAt(LocalDateTime.now())
                                .build();
                            
                            Employee savedEmployee = employeeRepository.save(employee);
                            log.debug("✅ Created employee: {} {} (ID: {}, EmpID: {})", 
                                firstName, lastName, savedEmployee.getId(), savedEmployee.getEmployeeId());
                        }
                    } catch (Exception e) {
                        log.error("❌ Error creating employee {}: {}", i, e.getMessage(), e);
                    }
                }
                
                long finalCount = employeeRepository.count();
                log.info("✅ Successfully created {} employee entries!", finalCount);
                log.info("📊 Total employees in database: {}", finalCount);
            } else {
                log.info("📊 Database already contains {} employees - skipping creation", currentEmployeeCount);
            }

        } catch (Exception e) {
            log.error("❌ Error during data initialization: {}", e.getMessage(), e);
        }
    }

    private Double generateRandomSalary(String position) {
        // Base salary ranges by position type
        if (position.contains("Manager") || position.contains("Lead")) {
            return 80000 + (random.nextDouble() * 50000); // 80k-130k
        } else if (position.contains("Senior") || position.contains("Specialist")) {
            return 60000 + (random.nextDouble() * 40000); // 60k-100k
        } else if (position.contains("Engineer") || position.contains("Analyst")) {
            return 50000 + (random.nextDouble() * 35000); // 50k-85k
        } else {
            return 35000 + (random.nextDouble() * 30000); // 35k-65k
        }
    }

    private LocalDate generateRandomHireDate() {
        // Generate hire date between 1 and 10 years ago
        int daysAgo = random.nextInt(3650) + 30; // 30 days to 10 years ago
        return LocalDate.now().minusDays(daysAgo);
    }

    private String generateRandomPhone() {
        return String.format("(%03d) %03d-%04d", 
            200 + random.nextInt(800), 
            200 + random.nextInt(800), 
            1000 + random.nextInt(9000));
    }

    private String generateRandomAddress() {
        int streetNumber = 100 + random.nextInt(9900);
        String[] streetNames = {"Main St", "Oak Ave", "Pine Rd", "Elm St", "Maple Ave", "Cedar Ln", "Park Blvd", "First St", "Second Ave", "Third St"};
        return streetNumber + " " + streetNames[random.nextInt(streetNames.length)];
    }

    private String generateRandomZipCode() {
        return String.format("%05d", 10000 + random.nextInt(90000));
    }

    private LocalDate generateRandomDateOfBirth() {
        // Generate birth date for ages between 22 and 65
        int ageInDays = (22 * 365) + random.nextInt(43 * 365); // 22-65 years old
        return LocalDate.now().minusDays(ageInDays);
    }
}
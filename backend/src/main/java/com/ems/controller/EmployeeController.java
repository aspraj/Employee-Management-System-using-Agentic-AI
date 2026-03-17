package com.ems.controller;

import com.ems.dto.EmployeeDTO;
import com.ems.entity.Employee;
import com.ems.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * EmployeeController - Handles all web requests for employee operations (like a receptionist)
 */
@Slf4j
@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    /** Service that handles employee business logic */
    @Autowired
    private EmployeeService employeeService;

    /** Get all employees - returns complete employee directory */
    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        log.info("📨 API Request: GET /employees");
        try {
            List<Employee> employees = employeeService.getAllEmployees();
            List<EmployeeDTO> employeeDTOs = employees.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
            log.info("📤 API Response: Successfully retrieved {} employees", employeeDTOs.size());
            return ResponseEntity.ok(employeeDTOs);
        } catch (Exception e) {
            log.error("❌ API Error in getAllEmployees: {}", e.getMessage(), e);
            throw e;
        }
    }

    /** Get specific employee by database ID */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        log.info("📨 API Request: GET /employees/{}", id);
        try {
            Optional<Employee> employee = employeeService.getEmployeeById(id);
            if (employee.isPresent()) {
                EmployeeDTO employeeDTO = convertToDTO(employee.get());
                log.info("📤 API Response: Employee found - ID={}", id);
                return ResponseEntity.ok(employeeDTO);
            } else {
                log.warn("⚠️ API Response: Employee not found - ID={}", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("❌ API Error in getEmployeeById({}): {}", id, e.getMessage(), e);
            throw e;
        }
    }

    /** Get employee by work badge ID (like EMP001) */
    @GetMapping("/employee-id/{employeeId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Employee> getEmployeeByEmployeeId(@PathVariable String employeeId) {
        log.info("📨 API Request: GET /employees/employee-id/{}", employeeId);
        try {
            Optional<Employee> employee = employeeService.getEmployeeByEmployeeId(employeeId);
            if (employee.isPresent()) {
                log.info("📤 API Response: Employee found - EmployeeID={}", employeeId);
                return ResponseEntity.ok(employee.get());
            } else {
                log.warn("⚠️ API Response: Employee not found - EmployeeID={}", employeeId);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("❌ API Error in getEmployeeByEmployeeId({}): {}", employeeId, e.getMessage(), e);
            throw e;
        }
    }

    /** Get all employees in specific department */
    @GetMapping("/department/{department}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByDepartment(@PathVariable String department) {
        log.info("📨 API Request: GET /employees/department/{}", department);
        try {
            List<Employee> employees = employeeService.getEmployeesByDepartment(department);
            List<EmployeeDTO> employeeDTOs = employees.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
            log.info("📤 API Response: Found {} employees in department: {}", employeeDTOs.size(), department);
            return ResponseEntity.ok(employeeDTOs);
        } catch (Exception e) {
            log.error("❌ API Error in getEmployeesByDepartment({}): {}", department, e.getMessage(), e);
            throw e;
        }
    }

    /** Create new employee - ADMIN only */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Employee> createEmployee(
            @RequestBody Employee employee,
            @RequestParam Long userId) {
        log.info("📨 API Request: POST /employees - Creating new employee, UserId: {}", userId);
        try {
            Employee createdEmployee = employeeService.createEmployee(employee, userId);
            log.info("📤 API Response: Employee created successfully - ID: {}, Name: {}", 
                createdEmployee.getId(), createdEmployee.getUser().getFirstName());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
        } catch (Exception e) {
            log.error("❌ API Error in createEmployee: {}", e.getMessage(), e);
            throw e;
        }
    }

    /** Update existing employee info - ADMIN only */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employeeDetails) {
        log.info("📨 API Request: PUT /employees/{} - Updating employee", id);
        try {
            Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
            log.info("📤 API Response: Employee updated successfully - ID: {}, Name: {}", 
                id, updatedEmployee.getUser().getFirstName());
            return ResponseEntity.ok(updatedEmployee);
        } catch (Exception e) {
            log.error("❌ API Error in updateEmployee({}): {}", id, e.getMessage(), e);
            throw e;
        }
    }

    /** Delete employee - ADMIN only (permanent removal) */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        log.info("📨 API Request: DELETE /employees/{}", id);
        try {
            employeeService.deleteEmployee(id);
            log.info("📤 API Response: Employee deleted successfully - ID: {}", id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("❌ API Error in deleteEmployee({}): {}", id, e.getMessage(), e);
            throw e;
        }
    }

    /** Get total employee count - for debugging/monitoring */
    @GetMapping("/count")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Map<String, Object>> getEmployeeCount() {
        log.info("📊 API Request: GET /employees/count");
        try {
            List<Employee> employees = employeeService.getAllEmployees();
            long count = employees.size();
            Map<String, Object> response = new HashMap<>();
            response.put("count", count);
            response.put("timestamp", LocalDateTime.now());
            log.info("📤 API Response: Employee count = {}", count);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("❌ API Error in getEmployeeCount: {}", e.getMessage(), e);
            throw e;
        }
    }

    /** Convert Employee entity to DTO for clean API responses */
    private EmployeeDTO convertToDTO(Employee employee) {
        return EmployeeDTO.builder()
            .id(employee.getId())
            .employeeId(employee.getEmployeeId())
            .firstName(employee.getUser().getFirstName())
            .lastName(employee.getUser().getLastName())
            .email(employee.getUser().getEmail())
            .username(employee.getUser().getUsername())
            .department(employee.getDepartment())
            .position(employee.getPosition())
            .salary(employee.getSalary())
            .hireDate(employee.getHireDate())
            .phone(employee.getPhone())
            .address(employee.getAddress())
            .city(employee.getCity())
            .state(employee.getState())
            .zipCode(employee.getZipCode())
            .country(employee.getCountry())
            .dateOfBirth(employee.getDateOfBirth())
            .createdAt(employee.getCreatedAt())
            .updatedAt(employee.getUpdatedAt())
            .build();
    }
}

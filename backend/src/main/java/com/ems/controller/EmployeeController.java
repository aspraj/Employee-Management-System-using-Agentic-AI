package com.ems.controller;

import com.ems.entity.Employee;
import com.ems.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        log.info("📨 API Request: GET /employees");
        try {
            List<Employee> employees = employeeService.getAllEmployees();
            log.info("📤 API Response: Successfully retrieved {} employees", employees.size());
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            log.error("❌ API Error in getAllEmployees: {}", e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        log.info("📨 API Request: GET /employees/{}", id);
        try {
            Optional<Employee> employee = employeeService.getEmployeeById(id);
            if (employee.isPresent()) {
                log.info("📤 API Response: Employee found - ID={}", id);
                return ResponseEntity.ok(employee.get());
            } else {
                log.warn("⚠️ API Response: Employee not found - ID={}", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("❌ API Error in getEmployeeById({}): {}", id, e.getMessage(), e);
            throw e;
        }
    }

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

    @GetMapping("/department/{department}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable String department) {
        log.info("📨 API Request: GET /employees/department/{}", department);
        try {
            List<Employee> employees = employeeService.getEmployeesByDepartment(department);
            log.info("📤 API Response: Found {} employees in department: {}", employees.size(), department);
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            log.error("❌ API Error in getEmployeesByDepartment({}): {}", department, e.getMessage(), e);
            throw e;
        }
    }

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
}

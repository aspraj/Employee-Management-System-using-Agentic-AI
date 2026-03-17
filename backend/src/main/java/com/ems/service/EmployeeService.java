package com.ems.service;

import com.ems.entity.Employee;
import com.ems.entity.User;
import com.ems.repository.EmployeeRepository;
import com.ems.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Employee> getAllEmployees() {
        log.info("🔵 Fetching all employees");
        try {
            List<Employee> employees = employeeRepository.findAll();
            log.info("✅ Successfully fetched {} employees", employees.size());
            return employees;
        } catch (Exception e) {
            log.error("❌ Error fetching all employees: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to fetch employees", e);
        }
    }

    public Optional<Employee> getEmployeeById(Long id) {
        log.info("🔵 Fetching employee by ID: {}", id);
        try {
            Optional<Employee> employee = employeeRepository.findById(id);
            if (employee.isPresent()) {
                log.info("✅ Employee found: ID={}, Name={}", id, employee.get().getUser().getFirstName());
            } else {
                log.warn("⚠️ Employee not found: ID={}", id);
            }
            return employee;
        } catch (Exception e) {
            log.error("❌ Error fetching employee by ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to fetch employee", e);
        }
    }

    public Optional<Employee> getEmployeeByEmployeeId(String employeeId) {
        log.info("🔵 Fetching employee by Employee ID: {}", employeeId);
        try {
            Optional<Employee> employee = employeeRepository.findByEmployeeId(employeeId);
            if (employee.isPresent()) {
                log.info("✅ Employee found: EmployeeID={}, Name={}", employeeId, employee.get().getUser().getFirstName());
            } else {
                log.warn("⚠️ Employee not found: EmployeeID={}", employeeId);
            }
            return employee;
        } catch (Exception e) {
            log.error("❌ Error fetching employee by EmployeeID {}: {}", employeeId, e.getMessage(), e);
            throw new RuntimeException("Failed to fetch employee", e);
        }
    }

    public List<Employee> getEmployeesByDepartment(String department) {
        log.info("🔵 Fetching employees by department: {}", department);
        try {
            List<Employee> employees = employeeRepository.findByDepartment(department);
            log.info("✅ Found {} employees in department: {}", employees.size(), department);
            return employees;
        } catch (Exception e) {
            log.error("❌ Error fetching employees by department {}: {}", department, e.getMessage(), e);
            throw new RuntimeException("Failed to fetch employees by department", e);
        }
    }

    public List<Employee> getEmployeesByPosition(String position) {
        log.info("🔵 Fetching employees by position: {}", position);
        try {
            List<Employee> employees = employeeRepository.findByPosition(position);
            log.info("✅ Found {} employees with position: {}", employees.size(), position);
            return employees;
        } catch (Exception e) {
            log.error("❌ Error fetching employees by position {}: {}", position, e.getMessage(), e);
            throw new RuntimeException("Failed to fetch employees by position", e);
        }
    }

    public Employee createEmployee(Employee employee, Long userId) {
        log.info("🔵 Creating new employee. Employee ID: {}, UserId: {}", employee.getEmployeeId(), userId);
        try {
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                employee.setUser(user.get());
                employee.setCreatedAt(LocalDateTime.now());
                employee.setUpdatedAt(LocalDateTime.now());
                Employee savedEmployee = employeeRepository.save(employee);
                log.info("✅ Employee created successfully. ID: {}, Name: {}, Department: {}", 
                    savedEmployee.getId(), savedEmployee.getUser().getFirstName(), savedEmployee.getDepartment());
                return savedEmployee;
            } else {
                log.error("❌ User not found for ID: {}", userId);
                throw new RuntimeException("User not found");
            }
        } catch (Exception e) {
            log.error("❌ Error creating employee: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create employee", e);
        }
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        log.info("🔵 Updating employee. ID: {}", id);
        try {
            Optional<Employee> employee = employeeRepository.findById(id);
            if (employee.isPresent()) {
                Employee existingEmployee = employee.get();
                if (employeeDetails.getDepartment() != null) {
                    log.debug("Updating department from {} to {}", existingEmployee.getDepartment(), employeeDetails.getDepartment());
                    existingEmployee.setDepartment(employeeDetails.getDepartment());
                }
                if (employeeDetails.getPosition() != null) {
                    log.debug("Updating position from {} to {}", existingEmployee.getPosition(), employeeDetails.getPosition());
                    existingEmployee.setPosition(employeeDetails.getPosition());
                }
                if (employeeDetails.getSalary() != null) {
                    log.debug("Updating salary");
                    existingEmployee.setSalary(employeeDetails.getSalary());
                }
                if (employeeDetails.getPhone() != null) {
                    log.debug("Updating phone");
                    existingEmployee.setPhone(employeeDetails.getPhone());
                }
                if (employeeDetails.getAddress() != null) {
                    log.debug("Updating address");
                    existingEmployee.setAddress(employeeDetails.getAddress());
                }
                existingEmployee.setUpdatedAt(LocalDateTime.now());
                Employee updatedEmployee = employeeRepository.save(existingEmployee);
                log.info("✅ Employee updated successfully. ID: {}, Name: {}", id, updatedEmployee.getUser().getFirstName());
                return updatedEmployee;
            } else {
                log.error("❌ Employee not found for ID: {}", id);
                throw new RuntimeException("Employee not found");
            }
        } catch (Exception e) {
            log.error("❌ Error updating employee ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to update employee", e);
        }
    }

    public void deleteEmployee(Long id) {
        log.info("🔵 Deleting employee. ID: {}", id);
        try {
            Optional<Employee> employee = employeeRepository.findById(id);
            if (employee.isPresent()) {
                Employee emp = employee.get();
                employeeRepository.deleteById(id);
                log.info("✅ Employee deleted successfully. ID: {}, Name: {}", id, emp.getUser().getFirstName());
            } else {
                log.warn("⚠️ Employee not found for deletion. ID: {}", id);
            }
        } catch (Exception e) {
            log.error("❌ Error deleting employee ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to delete employee", e);
        }
    }
}

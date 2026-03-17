package com.ems.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * EmployeeDTO - Clean data container for sending employee info to frontend (avoids security issues)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {
    
    /** Database ID - unique number for each employee */
    private Long id;
    
    /** Work badge ID like EMP001, EMP002 */
    private String employeeId;
    
    /** Employee's first name */
    private String firstName;
    
    /** Employee's last name */
    private String lastName;
    
    /** Work email address */
    private String email;
    
    /** Login username */
    private String username;
    
    /** Department name (Engineering, Marketing, etc.) */
    private String department;
    
    /** Job title (Manager, Developer, etc.) */
    private String position;
    
    /** Annual salary in dollars */
    private Double salary;
    
    /** Date employee started working */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate hireDate;
    
    /** Contact phone number */
    private String phone;
    
    /** Home street address */
    private String address;
    
    /** City where employee lives */
    private String city;
    
    /** State abbreviation (CA, NY, etc.) */
    private String state;
    
    /** ZIP/postal code */
    private String zipCode;
    
    /** Country (usually USA) */
    private String country;
    
    /** Employee's birthday */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    
    /** When record was created */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    
    /** When record was last updated */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}
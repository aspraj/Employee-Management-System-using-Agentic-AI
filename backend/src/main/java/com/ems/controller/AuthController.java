package com.ems.controller;

import com.ems.dto.LoginRequest;
import com.ems.dto.LoginResponse;
import com.ems.entity.User;
import com.ems.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        log.info("📨 API Request: POST /auth/login - Username: {}", loginRequest.getUsername());
        try {
            LoginResponse response = authService.login(loginRequest);
            log.info("📤 API Response: Login successful - Username: {}, Role: {}", 
                loginRequest.getUsername(), response.getRole());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("❌ API Error in login for username {}: {}", loginRequest.getUsername(), e.getMessage(), e);
            throw e;
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        log.info("📨 API Request: POST /auth/register - Username: {}, Email: {}", user.getUsername(), user.getEmail());
        try {
            User registeredUser = authService.register(user);
            log.info("📤 API Response: Registration successful - UserID: {}, Username: {}", 
                registeredUser.getId(), registeredUser.getUsername());
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
        } catch (Exception e) {
            log.error("❌ API Error in register for username {}: {}", user.getUsername(), e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        log.debug("🏥 Health check endpoint called");
        return ResponseEntity.ok("API is running");
    }
}

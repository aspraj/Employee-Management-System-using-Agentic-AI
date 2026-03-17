package com.ems.service;

import com.ems.dto.LoginRequest;
import com.ems.dto.LoginResponse;
import com.ems.entity.User;
import com.ems.repository.UserRepository;
import com.ems.security.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest loginRequest) {
        log.info("🔵 Login attempt for user: {}", loginRequest.getUsername());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            log.debug("✅ Authentication successful for user: {}", loginRequest.getUsername());

            Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());
            if (user.isPresent()) {
                String token = jwtTokenProvider.generateToken(loginRequest.getUsername());
                User userData = user.get();
                log.info("✅ Login successful for user: {} with role: {}", userData.getUsername(), userData.getRole());
                return LoginResponse.builder()
                        .token(token)
                        .username(userData.getUsername())
                        .email(userData.getEmail())
                        .role(userData.getRole())
                        .userId(userData.getId())
                        .build();
            }
            log.warn("⚠️  User not found after successful authentication: {}", loginRequest.getUsername());
            throw new RuntimeException("User not found");
        } catch (Exception e) {
            log.error("❌ Login failed for user: {}, Error: {}", loginRequest.getUsername(), e.getMessage(), e);
            throw e;
        }
    }

    public User register(User user) {
        log.info("📝 Registration attempt for user: {} with email: {}", user.getUsername(), user.getEmail());
        try {
            if (userRepository.existsByUsername(user.getUsername())) {
                log.warn("⚠️  Registration failed: Username already exists - {}", user.getUsername());
                throw new RuntimeException("Username already exists");
            }
            if (userRepository.existsByEmail(user.getEmail())) {
                log.warn("⚠️  Registration failed: Email already exists - {}", user.getEmail());
                throw new RuntimeException("Email already exists");
            }

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setActive(true);
            user.setRole("USER");
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());

            User savedUser = userRepository.save(user);
            log.info("✅ User registered successfully: {} (ID: {})", savedUser.getUsername(), savedUser.getId());
            return savedUser;
        } catch (Exception e) {
            log.error("❌ Registration failed for user: {}, Error: {}", user.getUsername(), e.getMessage(), e);
            throw e;
        }
    }
}

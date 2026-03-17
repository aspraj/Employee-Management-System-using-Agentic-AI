-- Insert Admin User
-- Password is 'admin123' encoded with BCrypt
INSERT INTO users (username, email, password, first_name, last_name, role, active, created_at, updated_at) 
VALUES ('admin', 'admin@company.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'System', 'Administrator', 'ADMIN', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
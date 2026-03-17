import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { LoggerService } from '../../services/logger.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  loading: boolean = false;
  error: string = '';

  constructor(
    private authService: AuthService,
    private router: Router,
    private logger: LoggerService
  ) {
    this.logger.info('🔐 LoginComponent initialized', null, 'COMPONENT');
  }

  login() {
    this.logger.info('🔵 Login attempt', { username: this.username }, 'COMPONENT');
    
    if (!this.username || !this.password) {
      this.error = 'Please fill in all fields';
      this.logger.warn('⚠️ Login validation failed', { reason: 'Missing credentials' }, 'COMPONENT');
      return;
    }

    this.loading = true;
    this.error = '';

    this.authService.login(this.username, this.password).subscribe({
      next: (response) => {
        this.loading = false;
        this.logger.info('✅ Login successful', { username: this.username }, 'COMPONENT');
        this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        this.loading = false;
        this.error = 'Invalid credentials';
        this.logger.error('❌ Login failed', { username: this.username, error: err.message }, 'COMPONENT');
      }
    });
  }

  register() {
    this.logger.info('🔵 Register attempt', { username: this.username }, 'COMPONENT');
    
    if (!this.username || !this.password) {
      this.error = 'Please fill in all fields';
      this.logger.warn('⚠️ Registration validation failed', { reason: 'Missing credentials' }, 'COMPONENT');
      return;
    }

    this.loading = true;
    this.error = '';

    this.authService.register(this.username, this.password).subscribe({
      next: (response) => {
        this.loading = false;
        this.error = '';
        this.username = '';
        this.password = '';
        this.logger.info('✅ Registration successful', { username: this.username }, 'COMPONENT');
      },
      error: (err) => {
        this.loading = false;
        this.error = 'Registration failed';
        this.logger.error('❌ Registration failed', { username: this.username, error: err.message }, 'COMPONENT');
      }
    });
  }
}

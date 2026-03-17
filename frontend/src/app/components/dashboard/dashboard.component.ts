import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterOutlet } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { LoggerService } from '../../services/logger.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterOutlet],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  username: string = '';

  constructor(
    private authService: AuthService,
    private router: Router,
    private logger: LoggerService
  ) {
    this.username = this.authService.getCurrentUser()?.username || 'User';
    this.logger.info('📊 DashboardComponent initialized', { username: this.username }, 'COMPONENT');
  }

  logout() {
    this.logger.info('🚪 User initiated logout', { username: this.username }, 'COMPONENT');
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  navigateTo(path: string) {
    this.logger.info('🔗 Navigation requested', { path }, 'COMPONENT');
    this.router.navigate(['/dashboard', path]);
  }
}

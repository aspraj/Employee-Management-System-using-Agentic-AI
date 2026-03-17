import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { LoggerService } from './logger.service';
import { throwError } from 'rxjs';

interface LoginResponse {
  token: string;
  username: string;
  email: string;
  role: string;
  userId: number;
}

interface User {
  username: string;
  email: string;
  firstName: string;
  lastName: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth';
  private currentUser: any = null;

  constructor(private http: HttpClient, private logger: LoggerService) {
    this.loadUserFromStorage();
    this.logger.info('AuthService initialized', null, 'SERVICE');
  }

  login(username: string, password: string): Observable<LoginResponse> {
    this.logger.info('🔐 Attempting login', { username }, 'AUTH');
    return this.http.post<LoginResponse>(`${this.apiUrl}/login`, { username, password })
      .pipe(
        tap((response: any) => {
          localStorage.setItem('token', response.token);
          this.currentUser = response;
          localStorage.setItem('currentUser', JSON.stringify(response));
          this.logger.info('✅ Login successful', { username, role: response.role }, 'AUTH');
        }),
        catchError((error: any) => {
          this.logger.error('❌ Login failed', { username, error: error.error }, 'AUTH');
          return throwError(() => error);
        })
      );
  }

  register(username: string, password: string): Observable<User> {
    this.logger.info('📝 Attempting registration', { username }, 'AUTH');
    return this.http.post<User>(`${this.apiUrl}/register`, {
      username,
      password,
      email: username + '@company.com',
      firstName: username,
      lastName: username,
      role: 'USER',
      active: true
    }).pipe(
      tap(() => {
        this.logger.info('✅ Registration successful', { username }, 'AUTH');
      }),
      catchError((error: any) => {
        this.logger.error('❌ Registration failed', { username, error: error.error }, 'AUTH');
        return throwError(() => error);
      })
    );
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('currentUser');
    this.currentUser = null;
    this.logger.info('🚪 User logged out', null, 'AUTH');
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  getCurrentUser(): any {
    return this.currentUser;
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  private loadUserFromStorage() {
    const user = localStorage.getItem('currentUser');
    if (user) {
      this.currentUser = JSON.parse(user);
    }
  }
}

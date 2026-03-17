import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { LoggerService } from './logger.service';

export interface Employee {
  id: number;
  employeeId: string;
  department: string;
  position: string;
  salary: number;
  hireDate: string;
  phone: string;
  address: string;
  city: string;
  state: string;
  zipCode: string;
  country: string;
  dateOfBirth: string;
}

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private apiUrl = 'http://localhost:8080/api/employees';

  constructor(
    private http: HttpClient,
    private logger: LoggerService
  ) {
    this.logger.info('📦 EmployeeService initialized', null, 'SERVICE');
  }

  getEmployees(): Observable<Employee[]> {
    this.logger.info('🔵 Fetching all employees', null, 'SERVICE');
    return this.http.get<Employee[]>(this.apiUrl).pipe(
      tap(
        (employees: Employee[]) => {
          this.logger.info('✅ Successfully fetched employees', { count: employees.length }, 'SERVICE');
        },
        (error: any) => {
          this.logger.error('❌ Failed to fetch employees', { error: error.message }, 'SERVICE');
        }
      )
    );
  }

  getEmployeeById(id: number): Observable<Employee> {
    this.logger.info('🔵 Fetching employee by ID', { id }, 'SERVICE');
    return this.http.get<Employee>(`${this.apiUrl}/${id}`).pipe(
      tap(
        (employee: Employee) => {
          this.logger.info('✅ Employee fetched successfully', { id, name: `${employee.employeeId}` }, 'SERVICE');
        },
        (error: any) => {
          this.logger.error('❌ Failed to fetch employee', { id, error: error.message }, 'SERVICE');
        }
      )
    );
  }

  createEmployee(employee: Employee, userId: number): Observable<Employee> {
    this.logger.info('🔵 Creating new employee', { userId, department: employee.department }, 'SERVICE');
    return this.http.post<Employee>(`${this.apiUrl}?userId=${userId}`, employee).pipe(
      tap(
        (createdEmployee: Employee) => {
          this.logger.info('✅ Employee created successfully', { id: createdEmployee.id, employeeId: createdEmployee.employeeId }, 'SERVICE');
        },
        (error: any) => {
          this.logger.error('❌ Failed to create employee', { error: error.message }, 'SERVICE');
        }
      )
    );
  }

  updateEmployee(id: number, employee: Employee): Observable<Employee> {
    this.logger.info('🔵 Updating employee', { id, department: employee.department }, 'SERVICE');
    return this.http.put<Employee>(`${this.apiUrl}/${id}`, employee).pipe(
      tap(
        (updatedEmployee: Employee) => {
          this.logger.info('✅ Employee updated successfully', { id }, 'SERVICE');
        },
        (error: any) => {
          this.logger.error('❌ Failed to update employee', { id, error: error.message }, 'SERVICE');
        }
      )
    );
  }

  deleteEmployee(id: number): Observable<void> {
    this.logger.info('🔵 Deleting employee', { id }, 'SERVICE');
    return this.http.delete<void>(`${this.apiUrl}/${id}`).pipe(
      tap(
        () => {
          this.logger.info('✅ Employee deleted successfully', { id }, 'SERVICE');
        },
        (error: any) => {
          this.logger.error('❌ Failed to delete employee', { id, error: error.message }, 'SERVICE');
        }
      )
    );
  }

  getEmployeesByDepartment(department: string): Observable<Employee[]> {
    this.logger.info('🔵 Fetching employees by department', { department }, 'SERVICE');
    return this.http.get<Employee[]>(`${this.apiUrl}/department/${department}`).pipe(
      tap(
        (employees: Employee[]) => {
          this.logger.info('✅ Employees fetched by department', { department, count: employees.length }, 'SERVICE');
        },
        (error: any) => {
          this.logger.error('❌ Failed to fetch employees by department', { department, error: error.message }, 'SERVICE');
        }
      )
    );
  }
}

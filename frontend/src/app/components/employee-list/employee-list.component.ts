import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EmployeeService, Employee } from '../../services/employee.service';
import { LoggerService } from '../../services/logger.service';

@Component({
  selector: 'app-employee-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './employee-list.component.html',
  styleUrl: './employee-list.component.css'
})
export class EmployeeListComponent implements OnInit {
  employees: Employee[] = [];
  loading: boolean = false;
  error: string = '';

  constructor(
    private employeeService: EmployeeService,
    private logger: LoggerService
  ) {
    this.logger.info('👥 EmployeeListComponent initialized', null, 'COMPONENT');
  }

  ngOnInit() {
    this.logger.info('🔄 EmployeeListComponent initialized', null, 'COMPONENT');
    this.loadEmployees();
  }

  loadEmployees() {
    this.logger.info('🔵 Loading employees', null, 'COMPONENT');
    this.loading = true;
    this.error = '';

    this.employeeService.getEmployees().subscribe({
      next: (data) => {
        this.employees = data;
        this.loading = false;
        this.logger.info('✅ Employees loaded successfully', { count: data.length }, 'COMPONENT');
      },
      error: (err) => {
        this.error = 'Failed to load employees';
        this.loading = false;
        this.logger.error('❌ Failed to load employees', { error: err.message }, 'COMPONENT');
      }
    });
  }
}

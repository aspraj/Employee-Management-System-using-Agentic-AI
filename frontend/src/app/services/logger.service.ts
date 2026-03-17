import { Injectable } from '@angular/core';

export enum LogLevel {
  DEBUG = 'DEBUG',
  INFO = 'INFO',
  WARN = 'WARN',
  ERROR = 'ERROR'
}

export interface LogEntry {
  timestamp: Date;
  level: LogLevel;
  message: string;
  data?: any;
  context?: string;
}

@Injectable({
  providedIn: 'root'
})
export class LoggerService {
  private logs: LogEntry[] = [];
  private readonly MAX_LOGS = 1000;
  private readonly LOG_STORAGE_KEY = 'ems-logs';
  private readonly isDevelopment = !this.isProd();

  constructor() {
    this.loadLogsFromStorage();
  }

  debug(message: string, data?: any, context?: string) {
    this.log(LogLevel.DEBUG, message, data, context);
  }

  info(message: string, data?: any, context?: string) {
    this.log(LogLevel.INFO, message, data, context);
  }

  warn(message: string, data?: any, context?: string) {
    this.log(LogLevel.WARN, message, data, context);
  }

  error(message: string, data?: any, context?: string) {
    this.log(LogLevel.ERROR, message, data, context);
  }

  private log(level: LogLevel, message: string, data?: any, context?: string) {
    const entry: LogEntry = {
      timestamp: new Date(),
      level,
      message,
      data,
      context
    };

    this.logs.push(entry);

    // Keep logs limited to MAX_LOGS
    if (this.logs.length > this.MAX_LOGS) {
      this.logs.shift();
    }

    // Persist to storage
    this.saveLogsToStorage();

    // Console output
    this.logToConsole(entry);
  }

  private logToConsole(entry: LogEntry) {
    const timestamp = entry.timestamp.toLocaleTimeString();
    const prefix = `%c[EMS ${entry.level}] ${timestamp}`;
    const context = entry.context ? ` [${entry.context}]` : '';
    const message = `${prefix}${context}: ${entry.message}`;

    const style = this.getColorForLevel(entry.level);

    if (entry.data) {
      if (this.isDevelopment) {
        console.log(message, style, entry.data);
      } else {
        console.log(message, style);
      }
    } else {
      console.log(message, style);
    }
  }

  private getColorForLevel(level: LogLevel): string {
    switch (level) {
      case LogLevel.DEBUG:
        return 'color: gray; font-weight: bold;';
      case LogLevel.INFO:
        return 'color: blue; font-weight: bold;';
      case LogLevel.WARN:
        return 'color: orange; font-weight: bold;';
      case LogLevel.ERROR:
        return 'color: red; font-weight: bold;';
      default:
        return 'color: black;';
    }
  }

  getLogs(level?: LogLevel): LogEntry[] {
    if (!level) {
      return this.logs;
    }
    return this.logs.filter(log => log.level === level);
  }

  getRecentLogs(count: number = 50): LogEntry[] {
    return this.logs.slice(-count);
  }

  clearLogs() {
    this.logs = [];
    this.saveLogsToStorage();
  }

  exportLogs(): string {
    return JSON.stringify(this.logs, null, 2);
  }

  downloadLogs(filename: string = 'ems-logs.json') {
    const data = this.exportLogs();
    const blob = new Blob([data], { type: 'application/json' });
    const url = window.URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = url;
    link.download = filename;
    link.click();
    window.URL.revokeObjectURL(url);
  }

  private saveLogsToStorage() {
    try {
      localStorage.setItem(this.LOG_STORAGE_KEY, JSON.stringify(this.logs));
    } catch (e) {
      console.warn('Failed to save logs to storage', e);
    }
  }

  private loadLogsFromStorage() {
    try {
      const stored = localStorage.getItem(this.LOG_STORAGE_KEY);
      if (stored) {
        this.logs = JSON.parse(stored);
      }
    } catch (e) {
      console.warn('Failed to load logs from storage', e);
    }
  }

  private isProd(): boolean {
    return false; // Set to true in production build
  }

  getStatistics() {
    return {
      totalLogs: this.logs.length,
      debugCount: this.logs.filter(l => l.level === LogLevel.DEBUG).length,
      infoCount: this.logs.filter(l => l.level === LogLevel.INFO).length,
      warnCount: this.logs.filter(l => l.level === LogLevel.WARN).length,
      errorCount: this.logs.filter(l => l.level === LogLevel.ERROR).length,
      oldestLog: this.logs[0]?.timestamp,
      newestLog: this.logs[this.logs.length - 1]?.timestamp
    };
  }
}

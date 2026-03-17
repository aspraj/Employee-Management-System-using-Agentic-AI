import { HttpInterceptorFn, HttpErrorResponse } from '@angular/common/http';
import { inject } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { LoggerService } from '../services/logger.service';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  const logger = inject(LoggerService) as LoggerService;

  logger.debug('🌐 HTTP Request', {
    method: req.method,
    url: req.url,
    headers: req.headers
  }, 'HTTP');

  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      const errorMessage = error.error?.message || error.message || 'Unknown error occurred';
      
      logger.error('❌ HTTP Error', {
        status: error.status,
        statusText: error.statusText,
        message: errorMessage,
        url: error.url,
        body: error.error
      }, 'HTTP');

      // Handle specific error codes
      switch (error.status) {
        case 400:
          logger.warn('⚠️  Bad Request - Invalid input data', error.error, 'VALIDATION');
          break;
        case 401:
          logger.warn('⚠️  Unauthorized - Invalid credentials or expired token', null, 'AUTH');
          // Redirect to login
          if (typeof window !== 'undefined') {
            window.location.href = '/login';
          }
          break;
        case 403:
          logger.warn('⚠️  Forbidden - Insufficient permissions', null, 'AUTH');
          break;
        case 404:
          logger.warn('⚠️  Not Found - Resource does not exist', { url: error.url }, 'RESOURCE');
          break;
        case 500:
          logger.error('❌ Internal Server Error - Please contact support', error.error, 'SERVER');
          break;
        case 503:
          logger.error('❌ Service Unavailable - Server is down', null, 'SERVER');
          break;
        default:
          if (error.status === 0) {
            logger.error('❌ Network Error - Connection failed', null, 'NETWORK');
          }
      }

      return throwError(() => error);
    })
  );
};

import { Injectable } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const AuthGuard: CanActivateFn = (route, state) => {
  const router: Router = (route as any).router || new Router();
  const token = localStorage.getItem('token');
  if (token) {
    return true;
  }
  router.navigate(['/login']);
  return false;
};

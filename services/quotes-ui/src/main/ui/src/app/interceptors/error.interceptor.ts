import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError } from 'rxjs';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  const router = inject(Router);
  return next(req).pipe(catchError((err) => {
    if (err instanceof HttpErrorResponse) {
      if (err.status === 0) {
        router.navigate(["error"], {
          state: {
            statusCode: err.status,
            errMessage: 'Offline'
          }
        });
      }
    }
    throw err;
  }));
};

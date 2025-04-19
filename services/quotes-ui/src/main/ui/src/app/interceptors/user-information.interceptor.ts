import { HttpHeaders, HttpInterceptorFn, HttpResponse } from '@angular/common/http';
import { tap } from 'rxjs';
import AppConstants from '../utils/AppConstants';

export const userInformationInterceptor: HttpInterceptorFn = (req, next) => {
  let newReq = req,
    userId = localStorage.getItem(AppConstants.Headers.X_USER_ID);
  if (userId) {
    newReq = req.clone({
      headers: new HttpHeaders({
        [AppConstants.Headers.X_USER_ID]: userId,
      })
    });
  }
  return next(newReq).pipe(tap(res => {
    if (res instanceof HttpResponse) {
      const userId = res.headers.get(AppConstants.Headers.X_USER_ID);
      if (userId) {
        localStorage.setItem(AppConstants.Headers.X_USER_ID, userId);
      }
    }
  }));
};

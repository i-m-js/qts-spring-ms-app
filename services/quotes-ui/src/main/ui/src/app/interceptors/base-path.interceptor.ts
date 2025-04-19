import { HttpInterceptorFn } from '@angular/common/http';

export const basePathInterceptor: HttpInterceptorFn = (req, next) => {
  return next(req);
};

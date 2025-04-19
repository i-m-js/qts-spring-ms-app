import { HashLocationStrategy, Location, LocationStrategy } from '@angular/common';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideAnimations } from '@angular/platform-browser/animations';
import { provideRouter } from '@angular/router';
import aura from '@primeng/themes/aura';
import { MessageService } from 'primeng/api';
import { providePrimeNG } from 'primeng/config';
import { routes } from './app.routes';
import { basePathInterceptor } from './interceptors/base-path.interceptor';
import { errorInterceptor } from './interceptors/error.interceptor';
import { userInformationInterceptor } from './interceptors/user-information.interceptor';

export const appConfig: ApplicationConfig = {
  providers: [
    MessageService,
    Location,
    { provide: LocationStrategy, useClass: HashLocationStrategy },
    // provideAppInitializer(() => {
    //   const authService = inject(AuthService);
    //   return authService.tryExistingSession();
    // }),
    provideAnimations(),
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideHttpClient(withInterceptors([basePathInterceptor, errorInterceptor, userInformationInterceptor])),
    providePrimeNG({
      theme: {
        preset: aura
      }
    })
  ]
};

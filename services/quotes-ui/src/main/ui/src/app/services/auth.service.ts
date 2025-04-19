import { HttpClient, HttpResponse } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { MessageService } from 'primeng/api';
import { BehaviorSubject, catchError, filter, tap } from 'rxjs';
import { Login } from '../models/login';
import { Router } from '@angular/router';
import AppConstants from '../utils/AppConstants';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  #userId: any;
  isLoggedIn: BehaviorSubject<boolean>;
  isLoggingIn: BehaviorSubject<boolean>;

  router = inject(Router)
  constructor(private http: HttpClient, private messageService: MessageService) {
    this.isLoggedIn = new BehaviorSubject<boolean>(false);
    this.isLoggingIn = new BehaviorSubject<boolean>(false);
  }

  setUserId(id: any) {
    this.#userId = id;
  }
  getUserId() {
    return this.#userId;
  }

  tryExistingSession(forceLogin = false) {
    //MOCK..!
    const userId = localStorage.getItem(AppConstants.Headers.X_USER_ID);
    this.isLoggingIn.next(true);
    return new Promise<void>((resolve) => {
      setTimeout(() => {
        this.setUserId(userId);
        this.isLoggedIn.next(!!userId);
        this.isLoggingIn.next(false);
        if(!userId){
          this.messageService.add({severity:'error', detail:'Oops..Auto login failed..!'})
        }
        resolve();
      }, 3000);
    })
  }

  login(data: Login) {
    this.isLoggingIn.next(true);
    return this.http.post<Object>("/api/auth/login", data, {
      observe: 'events'
    }).pipe(tap(res => {
      this.isLoggingIn.next(false);

      if (res instanceof HttpResponse) {
        let userId = res.headers.get('X-USER-ID');
        this.setUserId(userId);
        this.isLoggedIn.next(true);
        //res.body
      }
    }), catchError((err) => {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Login failed', });
      this.isLoggedIn.next(false);
      throw err;
    }), filter(i => i instanceof HttpResponse));
  }

  logout() {
    this.#clearAuthStates();
    this.isLoggedIn.next(false);
    this.router.navigate(['auth', 'login'])
  }

  #clearAuthStates() {
    this.setUserId(null);
  }
}

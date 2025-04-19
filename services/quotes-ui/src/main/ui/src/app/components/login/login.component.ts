import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { FormsModule, NgModel } from '@angular/forms';
import { CardModule } from 'primeng/card';
import { PasswordModule } from 'primeng/password';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  imports: [FormsModule, PasswordModule, CardModule, InputTextModule, ButtonModule],
  providers: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  username = ""
  password = ""
  // changeDedectionRef = inject(ChangeDetectorRef);

  constructor(private authService: AuthService, private router: Router) {

  }
  login() {
    const username = this.username, password = this.password;
    this.password = '';
    this.authService.login({ username, password }).subscribe({
      next: (res) => {
        console.log(res)
        this.router.navigate([""]);
      },
      error: (err) => console.log(err)
    });
  }

}

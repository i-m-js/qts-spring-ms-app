import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, RouterOutlet } from '@angular/router';
import { ToastModule } from 'primeng/toast';
import { AuthService } from './services/auth.service';
@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ToastModule],
  providers: [],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'ui';
  id = ''
  authService = inject(AuthService)
  route = inject(ActivatedRoute)
  
  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      // Access the 'id' query parameter and update the component state
      this.id = params['id'];
      console.log('Query Parameter changed to:', this.id);
    });

    this.authService.tryExistingSession();
  }
}

import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ButtonModule } from 'primeng/button'
import { MessageModule } from 'primeng/message';
@Component({
  selector: 'app-server-not-reachable',
  imports: [ButtonModule, MessageModule],
  templateUrl: './server-not-reachable.component.html',
  styleUrl: './server-not-reachable.component.css'
})
export class ServerNotReachableComponent {
  constructor(private router: Router) {
    console.log(router.getCurrentNavigation()?.extras.state)
  }

  retry() {
    this.router.navigate([""]).then(() => window.location.reload());
  }

}

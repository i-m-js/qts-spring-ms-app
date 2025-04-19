import { Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ServerNotReachableComponent } from './components/server-not-reachable/server-not-reachable.component';
import { LoginComponent } from './components/login/login.component';

export const routes: Routes = [
    { path: 'error', component: ServerNotReachableComponent },
    {
        path: 'auth', children: [
            { path: 'login', component: LoginComponent }
        ]
    },
    { path: '**', component: DashboardComponent },
];

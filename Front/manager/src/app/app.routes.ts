import { Routes } from '@angular/router';
import { MainLayout } from './layouts/main-layout/main-layout';
import { EmptyLayout } from './layouts/empty-layout/empty-layout';
import { Dashboard } from './pages/dashboard/dashboard';
import { Profile } from './pages/profile/profile';
import { Login } from './pages/login/login';
import { Register } from './pages/register/register';
import { Home } from './pages/home/home';

export const routes: Routes = [
  {
    path: '',
    component: MainLayout,
    children: [
      { path: "home", component: Home},
      { path: "dashboard", component: Dashboard},
      { path: "profile", component: Profile},
    ]
  },
  {
    path: '',
    component: EmptyLayout,
    children: [
      { path: "login", component: Login},
      { path: "register", component: Register},
    ]
  }
];

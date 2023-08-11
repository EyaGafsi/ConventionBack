import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { RHComponent } from './rh/rh.component';
import { EmployeeComponent } from './employee/employee.component';
import { LoginComponent } from './login/login.component';
import { AccessDeniedComponent } from './access-denied/access-denied.component';

const routes: Routes = [
  {path:'home',component:HomeComponent},
  {path:'rh',component:RHComponent},
  {path:'employee',component:EmployeeComponent},
  {path:'login',component:LoginComponent},
  {path:'accessDenied',component:AccessDeniedComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

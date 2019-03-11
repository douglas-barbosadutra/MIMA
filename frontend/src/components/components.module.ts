import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { HomeAdminComponent } from './home-admin/home-admin.component';
import { HomeUserComponent } from './home-user/home-user.component';
import { HomeEmployeeComponent } from './home-employee/home-employee.component';

@NgModule({
  declarations: [LoginComponent, HomeAdminComponent, HomeUserComponent, HomeEmployeeComponent],
  imports: [
    CommonModule
  ]
})
export class ComponentsModule { }

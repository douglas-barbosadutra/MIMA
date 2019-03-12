import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { HomeAdminComponent } from './home-admin/home-admin.component';
import { HomeUserComponent } from './home-user/home-user.component';
import { HomeEmployeeComponent } from './home-employee/home-employee.component';
import { MenuUserComponent } from './menu-user/menu-user.component';
import { MachineInsertComponent } from './machine-insert/machine-insert.component';
import { MachineShowComponent } from './machine-show/machine-show.component';
import { SchedulingInsertComponent } from './scheduling-insert/scheduling-insert.component';
import { SchedulingShowComponent } from './scheduling-show/scheduling-show.component';
import { TaskInsertComponent } from './task-insert/task-insert.component';
import { TaskShowComponent } from './task-show/task-show.component';
import { InstructionInsertComponent } from './instruction-insert/instruction-insert.component';
import { InstructionShowComponent } from './instruction-show/instruction-show.component';
import { UserUpdateComponent } from './user-update/user-update.component';
import { WbsInsertComponent } from './wbs-insert/wbs-insert.component';
import { WbsShowComponent } from './wbs-show/wbs-show.component';
import { ItemInsertComponent } from './item-insert/item-insert.component';
import { ItemShowComponent } from './item-show/item-show.component';
import { SchedulingUpdateComponent } from './scheduling-update/scheduling-update.component';
import { TimeShowComponent } from './time-show/time-show.component';
import { TaskScheduledComponent } from './task-scheduled/task-scheduled.component';
import { TaskEmployeeShowComponent } from './task-employee-show/task-employee-show.component';
import { EmployeeUpdateComponent } from './employee-update/employee-update.component';

@NgModule({
  declarations: [LoginComponent, HomeAdminComponent, HomeUserComponent, HomeEmployeeComponent, MenuUserComponent, MachineInsertComponent, MachineShowComponent, SchedulingInsertComponent, SchedulingShowComponent, TaskInsertComponent, TaskShowComponent, InstructionInsertComponent, InstructionShowComponent, UserUpdateComponent, WbsInsertComponent, WbsShowComponent, ItemInsertComponent, ItemShowComponent, SchedulingUpdateComponent, TimeShowComponent, TaskScheduledComponent, TaskEmployeeShowComponent, EmployeeUpdateComponent],
  imports: [
    CommonModule
  ]
})
export class ComponentsModule { }

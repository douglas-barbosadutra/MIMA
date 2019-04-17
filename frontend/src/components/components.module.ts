import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { HomeAdminComponent } from './home-admin/home-admin.component';
import { HomeUserComponent } from './home-user/home-user.component';
import { HomeEmployeeComponent } from './home-employee/home-employee.component';
import { MenuUserComponent } from './menu-user/menu-user.component';
import { MachineShowComponent } from './machine-show/machine-show.component';
import { SchedulingShowComponent } from './scheduling-show/scheduling-show.component';
import { TaskShowComponent } from './task-show/task-show.component';
import { InstructionShowComponent } from './instruction-show/instruction-show.component';
import { UserUpdateComponent } from './user-update/user-update.component';
import { WbsShowComponent } from './wbs-show/wbs-show.component';
import { ItemInsertComponent } from './item-insert/item-insert.component';
import { ItemShowComponent } from './item-show/item-show.component';
import { TimeShowComponent } from './time-show/time-show.component';
import { TaskScheduledComponent } from './task-scheduled/task-scheduled.component';
import { TaskEmployeeShowComponent } from './task-employee-show/task-employee-show.component';
import { EmployeeShowComponent } from './employee-show/employee-show.component';
import { AssignTaskComponent } from './assign-task/assign-task.component';
import { InputOutputComponent } from './input-output/input-output.component';
import { TaskScheduledShowComponent } from './task-scheduled-show/task-scheduled-show.component';
import { TaskScheduledDeleteComponent } from './task-scheduled-delete/task-scheduled-delete.component';
import { SendMessageComponent } from './send-message/send-message.component';
import { InputShowComponent } from './input-show/input-show.component';
import { EmployeeUpdateComponent } from './employee-update/employee-update.component';

@NgModule({
// tslint:disable-next-line: max-line-length
  declarations: [LoginComponent, HomeAdminComponent, HomeUserComponent, HomeEmployeeComponent, MenuUserComponent, MachineShowComponent, SchedulingShowComponent, TaskShowComponent, InstructionShowComponent, UserUpdateComponent, WbsShowComponent, ItemInsertComponent, ItemShowComponent, TimeShowComponent, TaskScheduledComponent, TaskEmployeeShowComponent, EmployeeShowComponent, AssignTaskComponent, InputOutputComponent, TaskScheduledShowComponent, TaskScheduledDeleteComponent, SendMessageComponent, InputShowComponent, EmployeeUpdateComponent],
  imports: [
    CommonModule
  ]
})
export class ComponentsModule { }

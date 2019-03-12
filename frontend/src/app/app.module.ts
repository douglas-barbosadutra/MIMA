import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { LoginComponent } from "../components/login/login.component";
import { FormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginService } from 'src/services/login.service';
import {MachineService} from 'src/services/machine.service';
import { TaskService } from 'src/services/task.service';
import { InstructionService } from 'src/services/instruction.service';
import { SchedulingService } from 'src/services/scheduling.service';
import { ManufactoringService } from 'src/services/manufactoring.service';
import {WbsService} from 'src/services/wbs.service';
import {ItemService} from 'src/services/item.service';
import {TaskScheduledService} from 'src/services/task-scheduled.service';
import {EmployeeService} from 'src/services/employee.service';

import {HomeAdminComponent} from 'src/components/home-admin/home-admin.component';
import {HomeUserComponent} from 'src/components/home-user/home-user.component';
import {HomeEmployeeComponent} from 'src/components/home-employee/home-employee.component';
import {MenuUserComponent} from 'src/components/menu-user/menu-user.component';
import {MenuEmployeeComponent} from 'src/components/menu-employee/menu-employee.component';
import {InstructionInsertComponent} from 'src/components/instruction-insert/instruction-insert.component';
import {InstructionShowComponent} from 'src/components/instruction-show/instruction-show.component';
import {MachineInsertComponent} from 'src/components/machine-insert/machine-insert.component';
import {MachineShowComponent} from 'src/components/machine-show/machine-show.component';
import {SchedulingInsertComponent} from 'src/components/scheduling-insert/scheduling-insert.component';
import {SchedulingShowComponent} from 'src/components/scheduling-show/scheduling-show.component';
import {SchedulingUpdateComponent} from 'src/components/scheduling-update/scheduling-update.component';
import {TaskInsertComponent} from 'src/components/task-insert/task-insert.component';
import {TaskShowComponent} from 'src/components/task-show/task-show.component';
import {UserUpdateComponent} from 'src/components/user-update/user-update.component';
import {WbsInsertComponent} from 'src/components/wbs-insert/wbs-insert.component';
import {WbsShowComponent} from 'src/components/wbs-show/wbs-show.component';
import {UserInsertComponent} from 'src/components/user-insert/user-insert.component';
import {UserShowComponent} from 'src/components/user-show/user-show.component';
import {MenuAdminComponent} from 'src/components/menu-admin/menu-admin.component';
import { UserService } from 'src/services/user.service';
import { ItemInsertComponent } from 'src/components/item-insert/item-insert.component';
import { ItemShowComponent } from 'src/components/item-show/item-show.component';
import { TimeShowComponent } from 'src/components/time-show/time-show.component';
import{TaskScheduledComponent} from 'src/components/task-scheduled/task-scheduled.component';
import { TaskEmployeeShowComponent } from 'src/components/task-employee-show/task-employee-show.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeAdminComponent,
    HomeUserComponent,
    HomeEmployeeComponent,
    MenuUserComponent,
    InstructionInsertComponent,
    InstructionShowComponent,
    MachineInsertComponent,
    MachineShowComponent,
    SchedulingInsertComponent,
    SchedulingShowComponent,
    SchedulingUpdateComponent,
    TaskInsertComponent,
    TaskShowComponent,
    UserUpdateComponent,
    WbsInsertComponent,
    WbsShowComponent,
    UserInsertComponent,
    UserShowComponent,
    MenuAdminComponent,
    ItemInsertComponent,
    ItemShowComponent,
    MenuEmployeeComponent,
    TimeShowComponent,
    TaskScheduledComponent,
    TaskEmployeeShowComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [LoginService,UserService,MachineService,TaskService,InstructionService,SchedulingService,ManufactoringService, WbsService, ItemService, TaskScheduledService, EmployeeService],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { LoginComponent } from "../components/login/login.component";
import { FormsModule,ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import { MatStepperModule, MatInputModule, MatButtonModule, MatAutocompleteModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DataTablesModule } from 'angular-datatables';
import {MatTreeModule} from '@angular/material/tree';

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
import { DataServiceService } from 'src/services/data-service.service';
import { SendMessageService } from 'src/services/send-message.service';
import {InputService} from 'src/services/input.service';

import {HomeAdminComponent} from 'src/components/home-admin/home-admin.component';
import {HomeUserComponent} from 'src/components/home-user/home-user.component';
import {HomeEmployeeComponent} from 'src/components/home-employee/home-employee.component';
import {MenuUserComponent} from 'src/components/menu-user/menu-user.component';
import {MenuEmployeeComponent} from 'src/components/menu-employee/menu-employee.component';
import {InstructionShowComponent} from 'src/components/instruction-show/instruction-show.component';
import {MachineShowComponent} from 'src/components/machine-show/machine-show.component';
import {SchedulingShowComponent} from 'src/components/scheduling-show/scheduling-show.component';
import {TaskShowComponent} from 'src/components/task-show/task-show.component';
import {UserUpdateComponent} from 'src/components/user-update/user-update.component';
import {WbsShowComponent} from 'src/components/wbs-show/wbs-show.component';
import {UserInsertComponent} from 'src/components/user-insert/user-insert.component';
import {UserShowComponent} from 'src/components/user-show/user-show.component';
import {MenuAdminComponent} from 'src/components/menu-admin/menu-admin.component';
import { UserService } from 'src/services/user.service';
import { ItemShowComponent } from 'src/components/item-show/item-show.component';
import { TimeShowComponent } from 'src/components/time-show/time-show.component';
import{TaskScheduledComponent} from 'src/components/task-scheduled/task-scheduled.component';
import { TaskEmployeeShowComponent } from 'src/components/task-employee-show/task-employee-show.component';
import { EmployeeShowComponent } from 'src/components/employee-show/employee-show.component';
import {EmployeeUpdateComponent} from 'src/components/employee-update/employee-update.component';
import { AssignTaskComponent } from 'src/components/assign-task/assign-task.component';
import { InputOutputComponent } from 'src/components/input-output/input-output.component';
import { TaskScheduledShowComponent } from 'src/components/task-scheduled-show/task-scheduled-show.component';
import { TaskScheduledDeleteComponent } from 'src/components/task-scheduled-delete/task-scheduled-delete.component';
import { HttpConfigInterceptor } from './interceptor/httpconfig.interceptor';
import { SendMessageComponent } from 'src/components/send-message/send-message.component';
import {InputShowComponent} from 'src/components/input-show/input-show.component';
import { MapperComponent } from 'src/components/mapper/mapper.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeAdminComponent,
    HomeUserComponent,
    HomeEmployeeComponent,
    MenuUserComponent,
    InstructionShowComponent,
    MachineShowComponent,
    SchedulingShowComponent,
    TaskShowComponent,
    UserUpdateComponent,
    WbsShowComponent,
    UserInsertComponent,
    UserShowComponent,
    MenuAdminComponent,
    ItemShowComponent,
    MenuEmployeeComponent,
    TimeShowComponent,
    TaskScheduledComponent,
    TaskScheduledDeleteComponent,
    TaskEmployeeShowComponent,
    EmployeeShowComponent,
    AssignTaskComponent,
    InputOutputComponent,
    TaskScheduledShowComponent,
    SendMessageComponent,
    InputShowComponent,
    EmployeeUpdateComponent,
    MapperComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    NgbModule.forRoot(),
    MatStepperModule,
    MatInputModule,
    MatButtonModule,
    MatAutocompleteModule,
    DataTablesModule,
    MatTreeModule
  ],
  providers: [LoginService,UserService,MachineService,TaskService,InstructionService,SchedulingService,ManufactoringService, WbsService, ItemService, TaskScheduledService, EmployeeService, DataServiceService, SendMessageService, InputService,
    { provide: HTTP_INTERCEPTORS, useClass: HttpConfigInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }

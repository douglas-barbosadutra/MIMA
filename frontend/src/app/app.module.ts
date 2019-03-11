import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { LoginComponent } from "../components/login/login.component";
import { FormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginService } from 'src/services/login.service';
import {HomeAdminComponent} from 'src/components/home-admin/home-admin.component';
import {HomeUserComponent} from 'src/components/home-user/home-user.component';
import {HomeEmployeeComponent} from 'src/components/home-employee/home-employee.component';
import {MenuUserComponent} from 'src/components/menu-user/menu-user.component';
import {InstructionInsertComponent} from 'src/components/instruction-insert/instruction-insert.component';
import {InstructionShowComponent} from 'src/components/instruction-show/instruction-show.component';
import {MachineInsertComponent} from 'src/components/machine-insert/machine-insert.component';
import {MachineShowComponent} from 'src/components/machine-show/machine-show.component';
import {SchedulingInsertComponent} from 'src/components/scheduling-insert/scheduling-insert.component';
import {SchedulingShowComponent} from 'src/components/scheduling-show/scheduling-show.component';
import {TaskInsertComponent} from 'src/components/task-insert/task-insert.component';
import {TaskShowComponent} from 'src/components/task-show/task-show.component';
import {UserUpdateComponent} from 'src/components/user-update/user-update.component';
import {WbsInsertComponent} from 'src/components/wbs-insert/wbs-insert.component';
import {WbsShowComponent} from 'src/components/wbs-show/wbs-show.component';

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
    TaskInsertComponent,
    TaskShowComponent,
    UserUpdateComponent,
    WbsInsertComponent,
    WbsShowComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [LoginService],
  bootstrap: [AppComponent]
})
export class AppModule { }

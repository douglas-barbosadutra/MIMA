import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from '../components/login/login.component';
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
import {MenuAdminComponent} from 'src/components/menu-admin/menu-admin.component';
import {UserInsertComponent} from 'src/components/user-insert/user-insert.component';
import {UserShowComponent} from 'src/components/user-show/user-show.component';
import { ItemInsertComponent } from 'src/components/item-insert/item-insert.component';
import { ItemShowComponent } from 'src/components/item-show/item-show.component';
import { SchedulingUpdateComponent } from 'src/components/scheduling-update/scheduling-update.component';
import { TimeShowComponent } from 'src/components/time-show/time-show.component';
import { TaskScheduledComponent } from 'src/components/task-scheduled/task-scheduled.component';
import { TaskEmployeeShowComponent } from 'src/components/task-employee-show/task-employee-show.component';
import { EmployeeUpdateComponent } from 'src/components/employee-update/employee-update.component';
import { EmployeeInsertComponent } from 'src/components/employee-insert/employee-insert.component';
import { EmployeeShowComponent } from 'src/components/employee-show/employee-show.component';
import { AssignTaskComponent } from 'src/components/assign-task/assign-task.component';
import { InputOutputComponent } from 'src/components/input-output/input-output.component';
import { TaskScheduledShowComponent } from 'src/components/task-scheduled-show/task-scheduled-show.component';
import { TaskScheduledDeleteComponent } from 'src/components/task-scheduled-delete/task-scheduled-delete.component';
import { SendMessageComponent } from 'src/components/send-message.component';

const routes: Routes = [
{path: '', redirectTo: '/login', pathMatch: 'full'},
{path: 'login', component: LoginComponent},
{path: 'homeAdmin', component: HomeAdminComponent},
{path: 'homeUser', component: HomeUserComponent},
{path: 'homeEmployee', component: HomeEmployeeComponent},
{path: 'menuUser', component: MenuUserComponent},
{path: 'instructionInsert', component: InstructionInsertComponent},
{path: 'instructionShow', component: InstructionShowComponent},
{path: 'machineInsert', component: MachineInsertComponent},
{path: 'machineShow', component: MachineShowComponent},
{path: 'schedulingInsert', component: SchedulingInsertComponent},
{path: 'schedulingShow', component: SchedulingShowComponent},
{path: 'schedulingUpdate', component: SchedulingUpdateComponent},
{path: 'taskInsert', component: TaskInsertComponent},
{path: 'taskShow', component: TaskShowComponent},
{path: 'userUpdate', component: UserUpdateComponent},
{path: 'wbsInsert', component: WbsInsertComponent},
{path: 'wbsShow', component: WbsShowComponent},
{path: 'menuAdmin', component: MenuAdminComponent},
{path: 'userInsert', component: UserInsertComponent},
{path: 'userShow', component: UserShowComponent},
{path: 'itemInsert', component: ItemInsertComponent},
{path: 'itemShow', component: ItemShowComponent},
{path: 'timeShow', component: TimeShowComponent},
{path: 'TaskScheduled', component: TaskScheduledComponent},
{path: 'taskEmployeeShow', component: TaskEmployeeShowComponent},
{path: 'employeeUpdate', component: EmployeeUpdateComponent},
{path: 'employeeInsert', component: EmployeeInsertComponent},
{path: 'employeeShow', component: EmployeeShowComponent},
{path: 'assignTask', component: AssignTaskComponent},
{path: 'inputOutput', component: InputOutputComponent},
{path: 'taskScheduledShow', component: TaskScheduledShowComponent},
{path: 'TaskScheduledDelete', component: TaskScheduledDeleteComponent},
{path: 'SendMessage', component: SendMessageComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true, onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }

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
{path: 'taskInsert', component: TaskInsertComponent},
{path: 'taskShow', component: TaskShowComponent},
{path: 'userUpdate', component: UserUpdateComponent},
{path: 'wbsInsert', component: WbsInsertComponent},
{path: 'wbsShow', component: WbsShowComponent},
{path: 'menuAdmin', component: MenuAdminComponent},
{path: 'userInsert', component: UserInsertComponent},
{path: 'userShow', component: UserShowComponent},
{path: 'itemInsert', component: ItemInsertComponent},
{path: 'itemShow', component: ItemShowComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true, onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }

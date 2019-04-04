import { Component, OnInit } from '@angular/core';
import { EmployeeDTO } from 'src/dto/EmployeeDTO';
import { UserDTO } from 'src/dto/UserDTO';
import { TaskDTO } from 'src/dto/TaskDTO';
import { TaskService } from 'src/services/task.service';
import { Router } from '@angular/router';
import { EmployeeService } from 'src/services/employee.service';
import { ParamDTO } from 'src/dto/ParamDTO';

@Component({
  selector: 'app-assign-task',
  templateUrl: './assign-task.component.html',
  styleUrls: ['./assign-task.component.css']
})
export class AssignTaskComponent implements OnInit {
  private employeeDTO: EmployeeDTO;
  private userDTO: UserDTO;
  private taskList: Array<TaskDTO>;
  private paramDTO: ParamDTO;

  constructor(private taskService: TaskService, private employeeService: EmployeeService, private router: Router) { }

  ngOnInit() {
    this.checkMachine();
  }

  checkMachine(){
    if(sessionStorage.getItem("idMachine") == null){
      //alert("Devi prima selezionare un macchinario");
      this.router.navigateByUrl("machineShow");
    }
    else
      this.showTask();
  }

  showTask(){
    this.taskService.showTask(parseInt(sessionStorage.getItem("idMachine")), sessionStorage.getItem("userLogged")).subscribe((data: any) =>{

      if(data != null){
        this.taskList = data;
      }
    })
  }

  assignTask(idTask: number){

    this.userDTO = new UserDTO(parseInt(sessionStorage.getItem("idUserEmployee")),"","","","","","",0,"");
    this.employeeDTO = new EmployeeDTO(parseInt(sessionStorage.getItem("idEmployee")),this.userDTO,idTask,0);
    this.paramDTO = new ParamDTO(sessionStorage.getItem("userLogged"),this.employeeDTO);

    this.employeeService.assignTask(this.paramDTO).subscribe((data: any) =>{
      if(data != null)
        alert("Assegnazione task effettuata");
      else
        alert("Assegnazione task fallita");
    })
  }

}

import { Component, OnInit } from '@angular/core';
import { EmployeeDTO } from 'src/dto/EmployeeDTO';
import { UserDTO } from 'src/dto/UserDTO';
import { TaskDTO } from 'src/dto/TaskDTO';
import { TaskService } from 'src/services/task.service';
import { Router } from '@angular/router';
import { EmployeeService } from 'src/services/employee.service';

@Component({
  selector: 'app-assign-task',
  templateUrl: './assign-task.component.html',
  styleUrls: ['./assign-task.component.css']
})
export class AssignTaskComponent implements OnInit {
  private oldEmployee: EmployeeDTO;
  private newEmployee: EmployeeDTO;
  public taskList: Array<TaskDTO>;

  constructor(private taskService: TaskService, private employeeService: EmployeeService, private router: Router) { }

  ngOnInit() {
    this.checkMachine();
  }

  checkMachine(){
    if(sessionStorage.getItem("idMachine") == null){
      this.router.navigateByUrl("machineShow");
    }
    else{
      this.showTask();
      this.findEmployee();
    }
      
  }

  findEmployee(){
    this.employeeService.findEmployee(parseInt(sessionStorage.getItem("idEmployee"))).subscribe((response: EmployeeDTO) =>{
      if(response != null)
        this.oldEmployee = response;
    })
  }

  showTask(){
    this.taskService.showTask(parseInt(sessionStorage.getItem("idMachine"))).subscribe((data: Array<TaskDTO>) =>{

      if(data != null){
        this.taskList = data;
      }
    })
  }

  assignTask(idTask: number){
    this.newEmployee = new EmployeeDTO(this.oldEmployee.id,this.oldEmployee.idUser,this.oldEmployee.idBusinessOwner,this.oldEmployee.name,idTask);

    this.employeeService.assignTask(this.newEmployee).subscribe((data: EmployeeDTO) =>{
      if(data != null)
        this.router.navigateByUrl("employeeShow");
    })
  }

}

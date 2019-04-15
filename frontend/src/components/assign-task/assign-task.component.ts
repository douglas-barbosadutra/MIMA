import { Component, OnInit,ViewChild, ElementRef, OnDestroy } from '@angular/core';
import { EmployeeDTO } from 'src/dto/EmployeeDTO';
import { TaskDTO } from 'src/dto/TaskDTO';
import { TaskService } from 'src/services/task.service';
import { Router } from '@angular/router';
import { EmployeeService } from 'src/services/employee.service';
import { Subject } from 'rxjs';
declare var $;

@Component({
  selector: 'app-assign-task',
  templateUrl: './assign-task.component.html',
  styleUrls: ['./assign-task.component.css']
})
export class AssignTaskComponent implements OnInit,OnDestroy {
  private oldEmployee: EmployeeDTO;
  private newEmployee: EmployeeDTO;
  taskList: TaskDTO[] = [];
  dtOptions: DataTables.Settings = {};
  // We use this trigger because fetching the list of persons can be quite long,
  // thus we ensure the data is fetched before rendering
  dtTrigger: Subject<TaskDTO> = new Subject();

  constructor(private taskService: TaskService, private employeeService: EmployeeService, private router: Router) { }

  ngOnInit() {

    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 10
    };
    
    this.checkMachine();
  }

  ngOnDestroy(): void {
    // Do not forget to unsubscribe the event
    this.dtTrigger.unsubscribe();
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
        this.dtTrigger.next();
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
import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { MachineDTO } from 'src/dto/MachineDTO';
import { TaskService } from 'src/services/task.service';
import { TaskDTO } from 'src/dto/TaskDTO';

@Component({
  selector: 'app-task-show',
  templateUrl: './task-show.component.html',
  styleUrls: ['./task-show.component.css']
})
export class TaskShowComponent implements OnInit {

  private machineDTO: MachineDTO;
  private taskDTO: TaskDTO;
  private taskList: Array<TaskDTO>;

  constructor(private router: Router, private taskService: TaskService) { }

  ngOnInit() {
    this.checkMachineSelected();
    this.taskShow();
  }

  checkMachineSelected(){
    if(sessionStorage.getItem("idMachine") == null){
      this.router.navigateByUrl("machineShow");
      alert("Devi prima selezionare un macchinario");
    }
  }

  taskShow(){

    this.machineDTO = new MachineDTO(parseInt(sessionStorage.getItem("idMachine")),"","",0);

    this.taskService.showTask(this.machineDTO).subscribe((data: any) =>{

      if(data != null){
        this.taskList = data;
        console.log(this.taskList);
      }
    })
  }

  chooseTask(idTask: number){
    
    sessionStorage.setItem("idTask",JSON.stringify(idTask));
    alert("Task selezionato");
    this.router.navigateByUrl("homeUser");
  }

  deleteTask(idTask: number){

    this.taskDTO = new TaskDTO(idTask,"",0);
    this.taskService.deleteTask(this.taskDTO).subscribe((data: any) =>{

      if(data)
        alert("Cancellazione effettuata");   
      else
        alert("Cancellazione fallita");

      this.router.navigateByUrl("homeUser");
    })
  }

}

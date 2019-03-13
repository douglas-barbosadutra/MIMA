import { Component, OnInit } from '@angular/core';
import { TaskScheduledService } from 'src/services/task-scheduled.service';
import { TaskService } from 'src/services/task.service';
import { Router } from "@angular/router";
import { TaskScheduledDTO } from 'src/dto/TaskScheduledDTO';
import { TaskDTO } from 'src/dto/TaskDTO';
import { OperationSchedulingDTO } from 'src/dto/OperationschedulingDTO';

@Component({
  selector: 'app-task-scheduled',
  templateUrl: './task-scheduled.component.html',
  styleUrls: ['./task-scheduled.component.css']
})
export class TaskScheduledComponent implements OnInit {
  public taskScheduledList : Array<TaskScheduledDTO>;
  public taskList: Array<TaskDTO>;
  public table: Array<OperationSchedulingDTO>;
  osDTO: OperationSchedulingDTO;

  constructor(private router: Router, private taskScheduledService: TaskScheduledService, private taskService: TaskService) { }

  ngOnInit() {
    this.getTaskScheduledList();
    this.getTaskList();
  }

  getTaskScheduledList(){
    this.taskScheduledService.showTaskScheduled(parseInt(sessionStorage.getItem("idScheduling"))).subscribe((data: any) =>{
      if(data != null){
        this.taskScheduledList = data;
      }
    });
  }

  getTaskList(){
    this.taskService.showTask(parseInt(sessionStorage.getItem("idMachine"))).subscribe((data: any) =>{
      if(data != null){
        this.taskList = data;
      }
    });
    this.createTable();
  }

  createTable(){
    for(let father of this.taskScheduledList){
      for(let elem of father.taskScheduledChildren){
        this.osDTO.idFather = father.id;
        this.osDTO.idChild = elem.id;
        this.table.push(this.osDTO);
      }
    }
  }


  
}

import { Component, OnInit } from '@angular/core';
import { TaskScheduledService } from 'src/services/task-scheduled.service';
import { TaskService } from 'src/services/task.service';
import { Router } from "@angular/router";
import { TaskScheduledDTO } from 'src/dto/TaskScheduledDTO';
import { TaskDTO } from 'src/dto/TaskDTO';
import { OperationSchedulingDTO } from 'src/dto/OperationSchedulingDTO';
import { DataServiceService } from 'src/services/data-service.service';

@Component({
  selector: 'app-task-scheduled',
  templateUrl: './task-scheduled.component.html',
  styleUrls: ['./task-scheduled.component.css']
})
export class TaskScheduledComponent implements OnInit {
  public taskScheduledList: Array<TaskScheduledDTO>;
  public taskList: Array<TaskDTO>;
  public table: Array<OperationSchedulingDTO>;
  public taskScheduledListToUpdateFather: Array<TaskScheduledDTO>;
  public taskScheduledListToUpdateChild: Array<TaskScheduledDTO>;
  public idTaskDeleted: number;
  osDTO: OperationSchedulingDTO;
  task: TaskScheduledDTO;
  public idFather: number;
  public idChild: number;

  constructor(private router: Router, private taskScheduledService: TaskScheduledService, private taskService: TaskService, private dataService: DataServiceService) {
    this.router.onSameUrlNavigation = 'reload';
   }

  ngOnInit() {
    this.getTaskScheduledList();
    this.getTaskList();
    this.createTable();
  }



  getTaskScheduledList() {
    this.taskScheduledService.showTaskScheduled(parseInt(sessionStorage.getItem("idScheduling"))).subscribe((data) => {
      if (data != null) {
        this.taskScheduledList = new Array();
        this.taskScheduledList = data;
        sessionStorage.setItem("taskScheduledListLength", JSON.stringify(this.taskScheduledList.length));
      }
    });
  }

  getTaskList() {
    this.taskService.showTask(parseInt(sessionStorage.getItem("idMachine"))).subscribe((data: any) => {
      if (data != null) {
        this.taskList = data;
      }
    });
  }

  createTable() {
    this.table = new Array<OperationSchedulingDTO>();
    this.taskScheduledService.showOperationScheduling(parseInt(sessionStorage.getItem("idScheduling"))).subscribe((data) => {
      if (data != null) {
        this.table = data;
      }
    });
  }

  chooseChild(idChild: number) {
    this.idChild = idChild;
  }

  chooseFather(idFather: number) {
    this.idFather = idFather;
  }

  insertTask(idTask: number, taskName: string) {
    if (parseInt(sessionStorage.getItem("taskScheduledListLength")) == 0) {
      this.task = new TaskScheduledDTO(0, idTask, false, taskName, parseInt(sessionStorage.getItem("idScheduling")), null);
      this.createTaskScheduled();
      window.location.reload();
    }
    else {
      this.idChild = idTask;
      this.osDTO = new OperationSchedulingDTO(this.idFather, this.idChild, idTask, parseInt(sessionStorage.getItem("idScheduling")));
    }
  }

  createOperationScheduling() {
    this.taskScheduledService.insertOperationScheduling(this.osDTO).subscribe((data: any) => { });
    this.getTaskScheduledList();
    this.getTaskList();
    this.createTable();
  }

  createTaskScheduled() {
    this.taskScheduledService.insertTaskScheduled(this.task).subscribe((data: any) => { });
    this.getTaskScheduledList();
    this.getTaskList();
    this.createTable();
  }

  delete(idTaskScheduled: number) {

    this.taskScheduledListToUpdateFather = new Array<TaskScheduledDTO>();
    this.taskScheduledListToUpdateChild = new Array<TaskScheduledDTO>();

    for (let row of this.table) {
      if (row.idFather == idTaskScheduled) {
        for (let t of this.taskScheduledList) {
          if (row.idChild == t.id)
            this.taskScheduledListToUpdateFather.push(t);
        }
      }
      else if (row.idChild == idTaskScheduled) {
        for (let t of this.taskScheduledList) {
          if (row.idFather == t.id)
            this.taskScheduledListToUpdateChild.push(t);
        }
      }
    }
    this.dataService.sendTaskScheduledChildren(this.taskScheduledListToUpdateChild);
    this.dataService.sendTaskScheduledFathers(this.taskScheduledListToUpdateFather);
    this.taskScheduledService.deleteTaskScheduled(idTaskScheduled).subscribe((data: any) => { });
    this.router.navigateByUrl("/TaskScheduledDelete");
  }


  insertTaskScheduled() {
    if ((this.idFather != undefined) && (this.idFather != null) && (this.idChild != undefined) && (this.idChild != null)) {
      if ((this.osDTO != undefined) && (this.osDTO != null)) {
        this.osDTO.idFather = this.idFather;
        this.osDTO.idChild = this.idChild;
        this.osDTO.idScheduling = parseInt(sessionStorage.getItem("idScheduling"));
        this.osDTO.idTask = this.idChild;
        this.taskScheduledService.insertOperationScheduling(this.osDTO).subscribe((data: any) => { });
      }
      else {
        this.osDTO = new OperationSchedulingDTO(this.idFather, this.idChild, 0, parseInt(sessionStorage.getItem("idScheduling")));
        this.taskScheduledService.insertOperationScheduling(this.osDTO).subscribe((data: any) => { });
      }
    }
    window.location.reload();
  }
}

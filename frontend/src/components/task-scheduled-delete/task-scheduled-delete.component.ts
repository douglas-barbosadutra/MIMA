import { Component, OnInit } from '@angular/core';
import { DataServiceService } from 'src/services/data-service.service';
import { TaskScheduledDTO } from 'src/dto/TaskScheduledDTO';
import { Subscription } from 'rxjs';
import { TaskScheduledService } from 'src/services/task-scheduled.service';
import { Router } from '@angular/router';
import { TaskScheduledRelationDTO } from 'src/dto/TaskScheduledRelationDTO';

@Component({
  selector: 'app-task-scheduled-delete',
  templateUrl: './task-scheduled-delete.component.html',
  styleUrls: ['./task-scheduled-delete.component.css']
})
export class TaskScheduledDeleteComponent implements OnInit {

  taskScheduledListToUpdateFather: TaskScheduledDTO[]=[];
  taskScheduledListToUpdateChild: TaskScheduledDTO[]=[];
  public taskScheduledList: Array<TaskScheduledDTO>;
  idFather: number;
  idChild: number;
  sub: Subscription;

  constructor(private router: Router, private dataService: DataServiceService, private taskScheduledService: TaskScheduledService) { }

  ngOnInit() {

    this.sub = this.dataService.currentDataFather.subscribe(dataSource => {
      this.taskScheduledListToUpdateChild = dataSource;
    });

    this.sub = this.dataService.currentDataChildren.subscribe(dataSource => {
      this.taskScheduledListToUpdateFather = dataSource;
    });

    this.taskScheduledService.showTaskScheduled(parseInt(sessionStorage.getItem("idScheduling"))).subscribe((data: TaskScheduledDTO[]) => {
      if (data != null) {
        this.taskScheduledList = new Array();
        this.taskScheduledList = data; 
      }
    });
  }

  addRelationChild(id: number) {
    
    let taskScheduledRelationDTO: TaskScheduledRelationDTO = new TaskScheduledRelationDTO(null,this.idFather,id);
    this.taskScheduledService.insertTaskScheduledRelation(taskScheduledRelationDTO).subscribe((data: TaskScheduledRelationDTO) => { 
      location.reload(true);
    });
  }

  addRelationFather(id: number) {
    let taskScheduledRelationDTO: TaskScheduledRelationDTO = new TaskScheduledRelationDTO(null,id,this.idChild);
    this.taskScheduledService.insertTaskScheduledRelation(taskScheduledRelationDTO).subscribe((data: TaskScheduledRelationDTO) => { 
      location.reload(true);
    });
  }

  finish(){
    this.router.navigateByUrl("/TaskScheduled");
  }
}

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

  taskScheduledListToUpdate: Array<TaskScheduledDTO>;
  public taskScheduledList: Array<TaskScheduledDTO>;
  idFather: number;
  idChild: number;
  sub: Subscription;

  constructor(private router: Router, private dataService: DataServiceService, private taskScheduledService: TaskScheduledService) { }

  ngOnInit() {

    this.sub = this.dataService.currentDataListToUpdate.subscribe(dataSource => {
      this.taskScheduledListToUpdate = dataSource;
    }); 
    this.sub = this.dataService.currentDataList.subscribe(dataSource => {
      this.taskScheduledList = dataSource;
    }); 
    /*
    this.taskScheduledService.showTaskScheduled(parseInt(sessionStorage.getItem("idScheduling"))).subscribe((data: Array<TaskScheduledDTO>) => {
      if (data != null) {
        this.taskScheduledList = new Array();
        this.taskScheduledList = data; 
      }
    });*/
    console.log(this.taskScheduledListToUpdate);
    console.log(this.taskScheduledList);
  }

  addRelationChild(id: number) {
    
    let taskScheduledRelationDTO: TaskScheduledRelationDTO = new TaskScheduledRelationDTO(null,this.idFather,id);
    this.taskScheduledService.insertTaskScheduledRelation(taskScheduledRelationDTO).subscribe((data: TaskScheduledRelationDTO) => { 
      
    });
    //location.reload(true);
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

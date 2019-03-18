import { Component, OnInit } from '@angular/core';
import { DataServiceService } from 'src/services/data-service.service';
import { TaskScheduledDTO } from 'src/dto/TaskScheduledDTO';
import { Subscription } from 'rxjs';
import { TaskScheduledService } from 'src/services/task-scheduled.service';
import { OperationSchedulingDTO } from 'src/dto/OperationSchedulingDTO';
import { Router } from '@angular/router';

@Component({
  selector: 'app-task-scheduled-delete',
  templateUrl: './task-scheduled-delete.component.html',
  styleUrls: ['./task-scheduled-delete.component.css']
})
export class TaskScheduledDeleteComponent implements OnInit {

  public taskScheduledListToUpdateFather: Array<TaskScheduledDTO>;
  public taskScheduledListToUpdateChild: Array<TaskScheduledDTO>;
  public taskScheduledList: Array<TaskScheduledDTO>;
  osDTO: OperationSchedulingDTO = new OperationSchedulingDTO(0, 0, 0, 0);
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
    this.taskScheduledService.showTaskScheduled(parseInt(sessionStorage.getItem("idScheduling"))).subscribe((data) => {
      if (data != null) {
        this.taskScheduledList = new Array();
        this.taskScheduledList = data;
        sessionStorage.setItem("taskScheduledListLength", JSON.stringify(this.taskScheduledList.length));
      }
    });
  }

  addRelationChild(id: number) {
    this.osDTO.idFather = this.idFather;
    this.osDTO.idChild = id;
    this.osDTO.idScheduling = parseInt(sessionStorage.getItem("idScheduling"));
    this.taskScheduledService.insertOperationScheduling(this.osDTO).subscribe((data: any) => { });
  }

  addRelationFather(id: number) {
    this.osDTO.idFather = id;
    this.osDTO.idChild = this.idChild;
    this.osDTO.idScheduling = parseInt(sessionStorage.getItem("idScheduling"));
    this.taskScheduledService.insertOperationScheduling(this.osDTO).subscribe((data: any) => { });
  }

  finish(){
    this.router.navigateByUrl("/TaskScheduled");
  }
}

import { Component, OnInit } from '@angular/core';
import { TaskScheduledDTO } from 'src/dto/TaskScheduledDTO';
import { TaskScheduledService } from 'src/services/task-scheduled.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-task-scheduled-show',
  templateUrl: './task-scheduled-show.component.html',
  styleUrls: ['./task-scheduled-show.component.css']
})
export class TaskScheduledShowComponent implements OnInit {
  public taskScheduledList: Array<TaskScheduledDTO>;

  constructor(private taskScheduledService: TaskScheduledService, private router: Router) { }

  ngOnInit() {
    this.checkScheduling();
  }

  checkScheduling(){
    if(sessionStorage.getItem("idScheduling") == null){
      this.router.navigateByUrl("schedulingShow");
    }
    else
      this.getTaskScheduledList();
  }

  getTaskScheduledList(){
    this.taskScheduledService.showTaskScheduled(parseInt(sessionStorage.getItem("idScheduling"))).subscribe((data) =>{
      if(data != null){
        this.taskScheduledList = new Array();
        this.taskScheduledList = data;  
      }
    });
  }

  choose(idOperationScheduling: number, idTaskScheduled: number){
    sessionStorage.setItem("idOperationScheduling",JSON.stringify(idOperationScheduling));
    sessionStorage.setItem("idTaskScheduled",JSON.stringify(idTaskScheduled));
    this.router.navigateByUrl("inputOutput");
  }

}

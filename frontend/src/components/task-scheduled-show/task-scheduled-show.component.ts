import { Component, OnInit } from '@angular/core';
import { TaskScheduledDTO } from 'src/dto/TaskScheduledDTO';
import { TaskScheduledService } from 'src/services/task-scheduled.service';
import { Router } from '@angular/router';
import { TaskScheduledShowDTO } from 'src/dto/TaskScheduledShowDTO';
import { ItemService } from 'src/services/item.service';
import { ItemDTO } from 'src/dto/ItemDTO';
import { TaskService } from 'src/services/task.service';
import { TaskDTO } from 'src/dto/TaskDTO';

@Component({
  selector: 'app-task-scheduled-show',
  templateUrl: './task-scheduled-show.component.html',
  styleUrls: ['./task-scheduled-show.component.css']
})
export class TaskScheduledShowComponent implements OnInit {
  private taskScheduledList: Array<TaskScheduledDTO>;
  public taskScheduledShowList: Array<TaskScheduledShowDTO>;
  private nameOutput: string;
  private descriptionTask: string;

  constructor(private taskService: TaskService, private itemService: ItemService, private taskScheduledService: TaskScheduledService, private router: Router) { }

  ngOnInit() {
    this.checkScheduling();
    this.taskScheduledShowList = new Array<TaskScheduledShowDTO>();
  }

  checkScheduling(){
    if(sessionStorage.getItem("idScheduling") == null){
      this.router.navigateByUrl("schedulingShow");
    }
    else
      this.getTaskScheduledList();
  }

  getTaskScheduledList(){
    this.taskScheduledService.showTaskScheduled(parseInt(sessionStorage.getItem("idScheduling"))).subscribe((response: Array<TaskScheduledDTO>) =>{
      if(response != null){
        this.taskScheduledList = response;
        this.showTaskScheduled();  
      }
    });
  }

  showTaskScheduled(){

    for(let task of this.taskScheduledList){

      this.taskService.findOne(task.taskId).subscribe((response: TaskDTO) =>{
        if(response != null){
          this.descriptionTask = response.description;

          if(task.idOutput != null){
            this.itemService.findOne(task.idOutput).subscribe((response: ItemDTO) =>{
              if(response != null)
                this.nameOutput = response.name;
            })
            this.taskScheduledShowList.push(new TaskScheduledShowDTO(task.id,task.name,this.nameOutput,this.descriptionTask));
          }
          else
          this.taskScheduledShowList.push(new TaskScheduledShowDTO(task.id,task.name,"Nessun output assegnato",this.descriptionTask));
        }
          
      })
      
    }
  }

  choose(idTaskScheduled: number){
    sessionStorage.setItem("idTaskScheduled",JSON.stringify(idTaskScheduled));
    this.router.navigateByUrl("inputOutput");
  }

}

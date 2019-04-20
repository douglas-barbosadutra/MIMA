import { Component, OnInit,ViewChild, ElementRef, OnDestroy } from '@angular/core';
import { TaskScheduledDTO } from 'src/dto/TaskScheduledDTO';
import { TaskScheduledService } from 'src/services/task-scheduled.service';
import { Router } from '@angular/router';
import { TaskScheduledShowDTO } from 'src/dto/TaskScheduledShowDTO';
import { ItemService } from 'src/services/item.service';
import { ItemDTO } from 'src/dto/ItemDTO';
import { TaskService } from 'src/services/task.service';
import { TaskDTO } from 'src/dto/TaskDTO';
import { Subject } from 'rxjs';
declare var $;

@Component({
  selector: 'app-task-scheduled-show',
  templateUrl: './task-scheduled-show.component.html',
  styleUrls: ['./task-scheduled-show.component.css']
})
export class TaskScheduledShowComponent implements OnInit,OnDestroy {
  private taskScheduledList: Array<TaskScheduledDTO>;
  public nameScheduling: string;
  taskScheduledShowList: TaskScheduledShowDTO[] = [];
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<TaskScheduledShowDTO> = new Subject();

  constructor(private taskService: TaskService, private itemService: ItemService, private taskScheduledService: TaskScheduledService, private router: Router) { }

  ngOnInit() {

    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 10
    };

    this.checkScheduling();
    this.taskScheduledShowList = new Array<TaskScheduledShowDTO>();
  }

  ngOnDestroy(): void {
    // Do not forget to unsubscribe the event
    this.dtTrigger.unsubscribe();
  }

  checkScheduling(){
    if(sessionStorage.getItem("idScheduling") == null){
      this.router.navigateByUrl("schedulingShow");
    }
    else{
      this.nameScheduling = sessionStorage.getItem("nameScheduling");
      this.getTaskScheduledList();
    }
      
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

          if(task.idOutput != null){
            this.itemService.findOne(task.idOutput).subscribe((response2: ItemDTO) =>{
              if(response != null)
                this.taskScheduledShowList.push(new TaskScheduledShowDTO(task.id,task.name,response2.name,response.description));
            })
          }
          else
          this.taskScheduledShowList.push(new TaskScheduledShowDTO(task.id,task.name,"Nessun output assegnato",response.description));
        }
          
      })
      
    }
    this.dtTrigger.next();
  }

  choose(idTaskScheduled: number, nameTaskScheduled: string){
    sessionStorage.setItem("idTaskScheduled",JSON.stringify(idTaskScheduled));
    sessionStorage.setItem("nameTaskScheduled",nameTaskScheduled);
    this.router.navigateByUrl("inputOutput");
  }

  showInput(idTaskScheduled: number){
    sessionStorage.setItem("idTaskScheduled",JSON.stringify(idTaskScheduled));
    this.router.navigateByUrl("inputShow");
  }

}

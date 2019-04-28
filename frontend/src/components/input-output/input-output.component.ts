import { Component, OnInit,ViewChild, ElementRef, OnDestroy } from '@angular/core';
import { ItemDTO } from 'src/dto/ItemDTO';
import { ItemService } from 'src/services/item.service';
import { InputDTO } from 'src/dto/InputDTO';
import { TaskScheduledService } from 'src/services/task-scheduled.service';
import { TaskScheduledDTO } from 'src/dto/TaskScheduledDTO';
import { InputService } from 'src/services/input.service';
import { Subject } from 'rxjs';
declare var $;

@Component({
  selector: 'app-input-output',
  templateUrl: './input-output.component.html',
  styleUrls: ['./input-output.component.css']
})
export class InputOutputComponent implements OnInit,OnDestroy {

  itemList: ItemDTO[] = [];
  public nameTaskScheduled: string;
  private inputDTO: InputDTO;
  private oldTaskScheduled: TaskScheduledDTO;
  private newTaskScheduled: TaskScheduledDTO;
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<ItemDTO> = new Subject();

  constructor(private itemService: ItemService, private taskScheduledService: TaskScheduledService, private inputService: InputService) { }

  ngOnInit() {
    this.nameTaskScheduled = sessionStorage.getItem("nameTaskScheduled");

    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 10
    };

    this.itemService.showItem().subscribe((data: Array<ItemDTO>) =>{
      if(data != null){
        this.itemList = data;
        this.dtTrigger.next();
      }  
    })

    this.taskScheduledService.findOne(parseInt(sessionStorage.getItem("idTaskScheduled"))).subscribe((response: TaskScheduledDTO) =>{
      if(response != null)
        this.oldTaskScheduled = response;
    })
  }

  ngOnDestroy(): void {
    // Do not forget to unsubscribe the event
    this.dtTrigger.unsubscribe();
  }

  insertInput(idItem: number){
    this.inputDTO = new InputDTO(null,idItem,parseInt(sessionStorage.getItem("idTaskScheduled")));
    this.inputService.insertInput(this.inputDTO).subscribe((response: InputDTO) =>{
      if(response != null)
        alert("Inserimento effettuato");
    })
  }

  insertOutput(idItem: number){
    
    this.newTaskScheduled = new TaskScheduledDTO(this.oldTaskScheduled.id,this.oldTaskScheduled.name,idItem,this.oldTaskScheduled.schedulingId,this.oldTaskScheduled.taskId,this.oldTaskScheduled.taskScheduledRelationList);

    this.taskScheduledService.insertOutput(this.newTaskScheduled).subscribe((data: TaskScheduledDTO) =>{
      if(data != null)
        alert("Inserimento effettuato");
    })
  }

}

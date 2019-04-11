import { Component, OnInit } from '@angular/core';
import { ItemDTO } from 'src/dto/ItemDTO';
import { ItemService } from 'src/services/item.service';
import { InputDTO } from 'src/dto/InputDTO';
import { TaskScheduledService } from 'src/services/task-scheduled.service';
import { TaskScheduledDTO } from 'src/dto/TaskScheduledDTO';
import { InputService } from 'src/services/input.service';

@Component({
  selector: 'app-input-output',
  templateUrl: './input-output.component.html',
  styleUrls: ['./input-output.component.css']
})
export class InputOutputComponent implements OnInit {

  public itemList: Array<ItemDTO>;
  private inputDTO: InputDTO;
  private oldTaskScheduled: TaskScheduledDTO;
  private newTaskScheduled: TaskScheduledDTO;

  constructor(private itemService: ItemService, private taskScheduledService: TaskScheduledService, private inputService: InputService) { }

  ngOnInit() {
    this.itemService.showItem().subscribe((data: Array<ItemDTO>) =>{
      if(data != null)
        this.itemList = data;
    })

    this.taskScheduledService.findOne(parseInt(sessionStorage.getItem("idTaskScheduled"))).subscribe((response: TaskScheduledDTO) =>{
      if(response != null)
        this.oldTaskScheduled = response;
    })
  }

  insertInput(idItem: number){
    this.inputDTO = new InputDTO(null,idItem,parseInt(sessionStorage.getItem("idTaskScheduled")));
    this.inputService.insertInput(this.inputDTO).subscribe((response: InputDTO) =>{
      if(response != null)
        alert("Inserimento effettuato");
    })
  }

  insertOutput(idItem: number){
    
    this.newTaskScheduled = new TaskScheduledDTO(this.oldTaskScheduled.id,this.oldTaskScheduled.name,idItem,this.oldTaskScheduled.schedulingId,this.oldTaskScheduled.taskId);

    this.taskScheduledService.insertOutput(this.newTaskScheduled).subscribe((data: TaskScheduledDTO) =>{
      if(data != null)
        alert("Inserimento effettuato");
    })
  }

}

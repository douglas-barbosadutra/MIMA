import { Component, OnInit } from '@angular/core';
import { ItemDTO } from 'src/dto/ItemDTO';
import { ItemService } from 'src/services/item.service';
import { InputDTO } from 'src/dto/InputDTO';
import { TaskScheduledService } from 'src/services/task-scheduled.service';
import { TaskScheduledDTO } from 'src/dto/TaskScheduledDTO';

@Component({
  selector: 'app-input-output',
  templateUrl: './input-output.component.html',
  styleUrls: ['./input-output.component.css']
})
export class InputOutputComponent implements OnInit {

  public itemList: Array<ItemDTO>;
  private inputDTO: InputDTO;
  private taskScheduledDTO: TaskScheduledDTO;

  constructor(private itemService: ItemService, private taskScheduledService: TaskScheduledService) { }

  ngOnInit() {
    this.itemService.showItem().subscribe((data: any) =>{
      if(data != null)
        this.itemList = data;
    })
  }

  insertInput(idItem: number){
    this.inputDTO = new InputDTO(idItem,parseInt(sessionStorage.getItem("idTaskScheduled")));
    this.itemService.insertInput(this.inputDTO).subscribe((data: any) =>{
      if(data)
        alert("Inserimento effettuato");
      else
        alert("Inserimento fallito");
    })
  }

  insertOutput(idItem: number){
    this.taskScheduledService.insertOutput(idItem,parseInt(sessionStorage.getItem("idOperationScheduling")), sessionStorage.getItem("userLogged")).subscribe((data: any) =>{
      if(data)
        alert("Inserimento effettuato");
      else
        alert("Inserimento fallito");
    })
  }

}

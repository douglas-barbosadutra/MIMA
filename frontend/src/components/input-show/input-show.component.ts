import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { InputDTO } from 'src/dto/InputDTO';
import { InputService } from 'src/services/input.service';
import { InputShowDTO } from 'src/dto/InputShowDTO';
import { ItemService } from 'src/services/item.service';
import { ItemDTO } from 'src/dto/ItemDTO';

@Component({
  selector: 'app-input-show',
  templateUrl: './input-show.component.html',
  styleUrls: ['./input-show.component.css']
})
export class InputShowComponent implements OnInit {
  
  private inputList: Array<InputDTO>;
  private inputShowList: Array<InputShowDTO>;

  constructor(private router: Router, private inputService: InputService, private itemService: ItemService) { }

  ngOnInit() {

    this.inputShowList = new Array<InputShowDTO>();

    if(sessionStorage.getItem("idTaskScheduled") == null)
      this.router.navigateByUrl("taskScheduledShow");
    
    else{
      this.inputService.showInput(parseInt(sessionStorage.getItem("idTaskScheduled"))).subscribe((response: Array<InputDTO>) =>{
        if(response != null){
          this.inputList = response;
          this.showInput();
        }
      })
    }
  }

  showInput(){
    for(let input of this.inputList){
      this.itemService.findOne(input.idItem).subscribe((response: ItemDTO) =>{
        if(response != null)
          this.inputShowList.push(new InputShowDTO(input.id,response.name));
      })
    }
    console.log(this.inputShowList);
  }


}

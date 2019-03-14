import { Component, OnInit } from '@angular/core';
import { ItemDTO } from 'src/dto/ItemDTO';
import { ItemService } from 'src/services/item.service';

@Component({
  selector: 'app-input-output',
  templateUrl: './input-output.component.html',
  styleUrls: ['./input-output.component.css']
})
export class InputOutputComponent implements OnInit {

  public itemList: Array<ItemDTO>
  public inputOutput: number;

  constructor(private itemService: ItemService) { }

  ngOnInit() {
    this.itemService.showItem().subscribe((data: any) =>{
      if(data != null)
        this.itemList = data;
    })
  }

  selectItem(idItem: number){
    console.log(this.inputOutput);
  }

}

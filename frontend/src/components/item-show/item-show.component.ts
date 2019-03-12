import { Component, OnInit } from '@angular/core';
import { ItemDTO } from 'src/dto/ItemDTO';
import { ItemService } from 'src/services/item.service';
import { Router } from "@angular/router";
import { WBSDTO } from 'src/dto/WBSDTO';


@Component({
  selector: 'app-item-show',
  templateUrl: './item-show.component.html',
  styleUrls: ['./item-show.component.css']
})
export class ItemShowComponent implements OnInit {
  public list: Array<ItemDTO>;
  private itemDto: ItemDTO;
  private wbsDto: WBSDTO;

  constructor(private router: Router, private itemService: ItemService) { }

  ngOnInit() {
    this.wbsDto = new WBSDTO(parseInt(sessionStorage.getItem("idWbs")), "", 0);
    this.itemService.showItemTree(this.wbsDto).subscribe((data: any) =>{

      if(data != null){
        this.list = data;
      }
    })
  }

  chooseFather(idItem: number){
    
    sessionStorage.setItem("idItemFather",JSON.stringify(idItem));
    alert("Item padre selezionato");
  }

  deleteItem(idItem: number){
    this.itemDto = new ItemDTO(idItem, "", 0, 0, null, 0);
    this.itemService.deleteItem(this.itemDto).subscribe((data: any) =>{

      if(data)
        alert("Cancellazione effettuata");   
      else
        alert("Cancellazione fallita");

      this.router.navigateByUrl("homeUser");
    });
  }
}

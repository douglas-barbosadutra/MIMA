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
  public father: ItemDTO;
  public list: Array<ItemDTO>;
  private itemDto: ItemDTO;
  private wbsDto: WBSDTO;


  constructor(private router: Router, private itemService: ItemService) { }

  ngOnInit() {
    this.wbsDto = new WBSDTO(parseInt(sessionStorage.getItem("idWbs")), "", 0);
    this.itemService.showItemTree(sessionStorage.getItem("userLogged"), parseInt(sessionStorage.getItem("idWbs")).subscribe((data: any) => {
      if (data != null) {
        this.father = data;
        this.list = this.father.itemChildrenDTO;
      }
    });
  }

  insertChild(idItem: number) {
    sessionStorage.setItem("idFather", JSON.stringify(idItem));
    this.router.navigateByUrl("itemInsert");
  }

  deleteItem(idItem: number) {
    this.itemDto = new ItemDTO(idItem, "", 0, 0, null);
    this.itemService.deleteItem(sessionStorage.getItem("userLogged"), idItem).subscribe((data: any) => {
    });
    window.location.reload();
  }

  inserisciRadice() {
    sessionStorage.setItem("idFather", JSON.stringify(0));
    this.router.navigateByUrl("itemInsert");
  }
}

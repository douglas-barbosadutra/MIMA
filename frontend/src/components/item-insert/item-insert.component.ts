import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { NgForm } from "@angular/forms";
import { ItemDTO } from 'src/dto/ItemDTO';
import { ItemService } from 'src/services/item.service';
import { ParamDTO } from 'src/dto/ParamDTO';

@Component({
  selector: 'app-item-insert',
  templateUrl: './item-insert.component.html',
  styleUrls: ['./item-insert.component.css']
})
export class ItemInsertComponent implements OnInit {

  public itemDTO: ItemDTO;
  private paramDTO: ParamDTO;

  constructor(private router: Router, private itemService: ItemService) { }

  ngOnInit() {
    this.itemDTO = new ItemDTO(null, "",parseInt(sessionStorage.getItem("idWbs")), parseInt(sessionStorage.getItem("idFather")));
    
  }

  insertItem(f: NgForm) {

    this.itemService.insertItem(this.paramDTO).subscribe((data: any) => {
      this.router.navigateByUrl("/itemShow");
    });
  }
}

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

  private itemDto: ItemDTO;
  private paramDTO: ParamDTO;

  constructor(private router: Router, private itemService: ItemService) { }

  ngOnInit() {
  }

  insertItem(f: NgForm) {
    this.itemDto = new ItemDTO(0, f.value.nome, parseInt(sessionStorage.getItem("idFather")), parseInt(sessionStorage.getItem("idWbs")), null);
    this.paramDTO = new ParamDTO(sessionStorage.getItem("userLogged"), this.itemDto);
    this.itemService.insertItem(this.paramDTO).subscribe((data: any) => {
      this.router.navigateByUrl("/itemShow");
    });
  }
}

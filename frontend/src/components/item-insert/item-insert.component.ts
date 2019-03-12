import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { NgForm } from "@angular/forms";
import { ItemDTO } from 'src/dto/ItemDTO';
import { ItemService } from 'src/services/item.service';

@Component({
  selector: 'app-item-insert',
  templateUrl: './item-insert.component.html',
  styleUrls: ['./item-insert.component.css']
})
export class ItemInsertComponent implements OnInit {

  private itemDto: ItemDTO;

  constructor(private router: Router, private itemService: ItemService) { }

  ngOnInit() {
  }

  insertItem(f: NgForm){
    this.itemDto = new ItemDTO(0, f.value.nome, parseInt(sessionStorage.getItem("idFather")), parseInt(sessionStorage.getItem("idWbs")), null, 0);
    this.itemService.insertItem(this.itemDto).subscribe((data: any) => {
      
      if(data != null)
        alert("Inserimento effettuato");
      else
        alert("Inserimento fallito");

        this.router.navigateByUrl("/homeUser");
    });
  }
}

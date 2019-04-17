import { Component, OnInit } from '@angular/core';

declare var $: any;

@Component({
  selector: 'app-mapper',
  templateUrl: './mapper.component.html',
  styleUrls: ['./mapper.component.css']
})
export class MapperComponent implements OnInit {

  public areas = [];
  private selectedAreas = [];

  constructor() {

    let width = 818;
    let height = 500;
    let resolution = 20;

    let indice = 0;
    for (let i=0; i < width; i+=resolution) {
      for (let j=0; j < height; j+=resolution) {
        this.areas[indice] = {x: i, y: j, x1: (i+resolution), y1: (j+resolution)};
        indice++;
      }
    }

  }

  ngOnInit() {

  }

  public getAreaClick(data: any) {

    let index = this.selectedAreas.indexOf(data);
    if(index != -1){
      console.log("rimosso");
      this.selectedAreas.splice(index, 1);
    }else{
      console.log("aggiunto");
      this.selectedAreas.push(data);  
    }

    console.log(data);
    console.log(this.selectedAreas);
  }

}

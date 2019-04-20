import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-mapper',
  templateUrl: './mapper.component.html',
  styleUrls: ['./mapper.component.css']
})
export class MapperComponent implements OnInit {

  public areas = [];
  public imageInput: File;
  public imageURL: any;
  public resolutionInput = 20;

  private selectedAreas = [];

  constructor() {

  }

  ngOnInit() {

  }

  private generateAreas(width: number, height: number, resolution: number){

    let indice = 0;
    for (let i=0; i < width; i+=resolution) {
      for (let j=0; j < height; j+=resolution) {
        this.areas[indice] = {x: i, y: j, x1: (i+resolution), y1: (j+resolution)};
        indice++;
      }
    }

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

  public generate(){

    let width = 818;
    let height = 500;

    /*if(this.imageInput != undefined){
      console.log(this.imageInput);
      this.imagePath = this.imageInput;
    }*/

    this.generateAreas(width, height, this.resolutionInput);
  }

  public onFileSelected(event: any){
    //console.log(event.target.files[0]);
    this.imageInput = event.target.files[0];

    var reader = new FileReader();
    reader.readAsDataURL(this.imageInput); 
    reader.onload = (_event) => { 
      this.imageURL = reader.result;
    }
  }

}

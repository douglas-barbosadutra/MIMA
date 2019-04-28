import { Component, OnInit } from '@angular/core';
import { AreasInterface } from 'src/interfaces/AreasInterface';
import { Areas } from 'src/classes/Areas';
import { BlackBox } from 'src/classes/BlackBox';

@Component({
  selector: 'app-mapper',
  templateUrl: './mapper.component.html',
  styleUrls: ['./mapper.component.css']
})
export class MapperComponent implements OnInit {

  public areas: Areas[] = [];
  public imageInput: File;
  public imageURL: any;
  public resolutionInput = 20;

  private selectedAreas: BlackBox[] = [];

  constructor() {

  }

  ngOnInit() {

  }

  private generateAreas(width: number, height: number, resolution: number){

    let indice = 0;
    for (let i=0; i < width; i+=resolution) {
      for (let j=0; j < height; j+=resolution) {
        this.areas[indice] = new Areas(i, j, (i+resolution), (j+resolution));
        indice++;
      }
    }

  }

  public getAreaClick(data: AreasInterface) {


    let row = data.y/this.resolutionInput;
    let column = data.x/this.resolutionInput;
    console.log(row + " - " + column);
    let blackbox = new BlackBox(row,column);

    let index = this.selectedAreas.findIndex((bb)=>bb.column === blackbox.column && bb.row === blackbox.row);
    if(index != -1){
      console.log("rimosso");
      this.selectedAreas.splice(index, 1);
    }else{
      console.log("aggiunto");
      this.selectedAreas.push(blackbox);  
    }

    console.log(blackbox);
    console.log(this.selectedAreas);
  }

  public generate(){

    let width = 800;
    let height = 500;

    this.generateAreas(width, height, this.resolutionInput);
  }

  public onFileSelected(event: any){
    //console.log(event.target.files[0]);
    this.imageInput = event.target.files[0];

    var reader = new FileReader();
    reader.readAsDataURL(this.imageInput); 
    reader.onload = (_event) => { 
      let myBase64 = reader.result;
      this.imageURL = myBase64;
      //console.log(myBase64); //immagine
    }
  }

  public sendToServer(){
    console.log("ok");
  }

}

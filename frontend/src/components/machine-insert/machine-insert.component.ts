import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { NgForm } from "@angular/forms";
import { MachineDTO } from 'src/dto/MachineDTO';

@Component({
  selector: 'app-machine-insert',
  templateUrl: './machine-insert.component.html',
  styleUrls: ['./machine-insert.component.css']
})
export class MachineInsertComponent implements OnInit {
  private machineDTO: MachineDTO;
  

  constructor(private router: Router) { }

  ngOnInit() {
  }

  insertMachine(f: NgForm){
    this.machineDTO = new MachineDTO(0,f.value.nome,f.value.modello, parseInt(sessionStorage.getItem("idUser")));
    console.log(this.machineDTO);
  }

}

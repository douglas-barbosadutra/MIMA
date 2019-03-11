import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { NgForm } from "@angular/forms";
import { MachineDTO } from 'src/dto/MachineDTO';
import {MachineService} from "src/services/machine.service";


@Component({
  selector: 'app-machine-insert',
  templateUrl: './machine-insert.component.html',
  styleUrls: ['./machine-insert.component.css']
})
export class MachineInsertComponent implements OnInit {
  private machineDTO: MachineDTO;
  

  constructor(private router: Router, private machineService: MachineService) { }

  ngOnInit() {
  }

  insertMachine(f: NgForm){
    this.machineDTO = new MachineDTO(0,f.value.nome,f.value.modello, parseInt(sessionStorage.getItem("idUser")));
    this.machineService.insertMachine(this.machineDTO).subscribe((data: any) => {
      
      if(data != null)
        alert("Inserimento effettuato");
      else
        alert("Richiesta fallita");
    })
  }

}

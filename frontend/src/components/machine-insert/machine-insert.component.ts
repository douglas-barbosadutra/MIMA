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
  public machineDTO: MachineDTO;
  

  constructor(private router: Router, private machineService: MachineService) { }

  ngOnInit() {
    this.machineDTO = new MachineDTO(0,"","",parseInt(sessionStorage.getItem("idUser")));
  }

  insertMachine(f: NgForm){
    console.log(this.machineDTO);
    this.machineService.insertMachine(this.machineDTO).subscribe((data: any) => {
      
      if(data != null){
        alert("Inserimento effettuato");
        //console.log(data);
      }
        
      else
        alert("Inserimento fallito");

        this.router.navigateByUrl("/homeUser");
    })
  }

}

import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import {MachineDTO} from 'src/dto/MachineDTO';
import {MachineService} from 'src/services/machine.service';
import { UserDTO } from 'src/dto/UserDTO';

@Component({
  selector: 'app-machine-show',
  templateUrl: './machine-show.component.html',
  styleUrls: ['./machine-show.component.css']
})
export class MachineShowComponent implements OnInit {

  private machineList : Array<MachineDTO>;
  private userDTO: UserDTO;
  private machineDTO: MachineDTO;

  constructor(private machineService: MachineService, private router:  Router) { }

  ngOnInit() {

    this.userDTO = new UserDTO(parseInt(sessionStorage.getItem("idUser")),"","","","","",0);
    this.machineService.showMachine(this.userDTO).subscribe((data: any) =>{

      if(data != null){
        this.machineList = data;
      }
    })

  }

  chooseMachine(idMachine: number){
    
    sessionStorage.setItem("idMachine",JSON.stringify(idMachine));
    alert("Macchinario selezionato");
  }

  deleteMachine(idMachine: number){

    this.machineDTO = new MachineDTO(idMachine,"","",0);
    this.machineService.deleteMachine(this.machineDTO).subscribe((data: any) =>{

      if(data)
        alert("Cancellazione effettuata");   
      else
        alert("Cancellazione fallita");

      this.router.navigateByUrl("homeUser");
    })
  }

}

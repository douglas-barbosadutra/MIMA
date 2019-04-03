import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import {MachineDTO} from 'src/dto/MachineDTO';
import {MachineService} from 'src/services/machine.service';
import { UserDTO } from 'src/dto/UserDTO';
import { ParamDTO } from 'src/dto/ParamDTO';

@Component({
  selector: 'app-machine-show',
  templateUrl: './machine-show.component.html',
  styleUrls: ['./machine-show.component.css']
})
export class MachineShowComponent implements OnInit {

  private machineList : Array<MachineDTO>;
  private machineDTO: MachineDTO;
  private paramDTO: ParamDTO;

  constructor(private machineService: MachineService, private router:  Router) { }

  ngOnInit() {
    console.log(sessionStorage.getItem("userLogged"));
    this.machineService.showMachine(localStorage.getItem("userLogged")).subscribe((data: any) =>{

      if(data != null){
        this.machineList = data;
      }
    })

  }

  chooseMachine(idMachine: number){
    
    sessionStorage.setItem("idMachine",JSON.stringify(idMachine));
    alert("Macchinario selezionato");
    this.router.navigateByUrl("homeUser");
  }

  deleteMachine(idMachine: number){
    this.paramDTO = new ParamDTO(sessionStorage.getItem("userLogged"),idMachine);
    this.machineService.deleteMachine(this.paramDTO).subscribe((data: any) =>{

      if(data)
        alert("Cancellazione effettuata");   
      else
        alert("Cancellazione fallita");

      this.router.navigateByUrl("homeUser");
    })
  }

}

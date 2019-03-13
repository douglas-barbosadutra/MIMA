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
  private machineDTO: MachineDTO;

  constructor(private machineService: MachineService, private router:  Router) { }

  ngOnInit() {

    this.machineService.showMachine().subscribe((data: any) =>{

      if(data != null){
        this.machineList = data;
      }
    })

  }
s
  chooseMachine(idMachine: number){
    
    sessionStorage.setItem("idMachine",JSON.stringify(idMachine));
    alert("Macchinario selezionato");
    this.router.navigateByUrl("homeUser");
  }

  deleteMachine(idMachine: number){

    this.machineService.deleteMachine(idMachine).subscribe((data: any) =>{

      if(data)
        alert("Cancellazione effettuata");   
      else
        alert("Cancellazione fallita");

      this.router.navigateByUrl("homeUser");
    })
  }

}

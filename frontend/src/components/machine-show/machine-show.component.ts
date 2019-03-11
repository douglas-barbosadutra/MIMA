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

  constructor(private machineService: MachineService) { }

  ngOnInit() {

    this.userDTO = new UserDTO(parseInt(sessionStorage.getItem("idUser")),"","","","","",0);
    this.machineService.showMachine(this.userDTO).subscribe((data: any) =>{

      if(data != null){
        this.machineList = data;
        console.log(this.machineList);
      }
    })

  }

}

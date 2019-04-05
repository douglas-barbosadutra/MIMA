import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { NgForm } from "@angular/forms";
import { MachineDTO } from 'src/dto/MachineDTO';
import {MachineService} from "src/services/machine.service";
import { ParamDTO } from 'src/dto/ParamDTO';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { UserDTO } from 'src/dto/UserDTO';


@Component({
  selector: 'app-machine-insert',
  templateUrl: './machine-insert.component.html',
  styleUrls: ['./machine-insert.component.css']
})
export class MachineInsertComponent implements OnInit {
  public machineDTO: MachineDTO;
  public userDTO: UserDTO;

  constructor(private router: Router, private machineService: MachineService) { }

  ngOnInit() {
    this.userDTO = JSON.parse(localStorage.getItem("currentUserData")) as UserDTO;
    this.machineDTO = new MachineDTO(null,"","",this.userDTO.id);
  }

  insertMachine(f: NgForm){

    console.log(this.machineDTO);

    this.machineService.insertMachine(this.machineDTO).subscribe((response: HttpResponse<any>) => {

        console.log(response);
        alert("Inserimento effettuato");
        this.router.navigateByUrl("/homeUser");

    })
  }

}

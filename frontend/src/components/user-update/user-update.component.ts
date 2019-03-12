import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/services/user.service';
import { NgForm } from '@angular/forms';
import {UserDTO } from 'src/dto/UserDTO';
import { Router } from '@angular/router';
import { FindValueSubscriber } from 'rxjs/internal/operators/find';


@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.css']
})
export class UserUpdateComponent implements OnInit {
  public userDTO: UserDTO;

  constructor(private router: Router, private userSerivce: UserService) { }

  ngOnInit() {
    this.findUser();
  }

  updateUser(f: NgForm){
    console.log(this.userDTO);

    /*this.userSerivce.updateUser(this.userDTO).subscribe((data: any) => {

      if(data != null)
        alert("Aggiornamento effettuato");
      else
        alert("Aggiornamento fallito");

        this.router.navigateByUrl("homeAdmin");
    })*/
  }

  findUser(){
    this.userSerivce.findUser(parseInt(sessionStorage.getItem("idUser"))).subscribe((data: any) =>{
      if(data != null)
        this.userDTO = data;
    })
  }

}

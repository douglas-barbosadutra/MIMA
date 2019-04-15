import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/services/user.service';
import {UserDTO } from 'src/dto/UserDTO';
import { Router } from '@angular/router';


@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.css']
})
export class UserUpdateComponent implements OnInit {
  
  public userDTO: UserDTO;

  constructor(private router: Router, private userSerivce: UserService) { }

  ngOnInit() {
    this.userDTO = JSON.parse(localStorage.getItem("currentUserData"));
    console.log(this.userDTO);
  }

  updateUser(){
    console.log(this.userDTO);
    this.userSerivce.updateUser(this.userDTO).subscribe((data: UserDTO) => {

      if(data != null)
        alert("Aggiornamento effettuato");
      
      this.router.navigateByUrl("homeUser");
    })
  }

  findUser(){
    this.userSerivce.findUser(sessionStorage.getItem("userLogged")).subscribe((data: any) =>{
      if(data != null)
        this.userDTO = data;
    })
  }

}

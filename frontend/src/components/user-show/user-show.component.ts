import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import {UserService} from 'src/services/user.service';
import { UserDTO } from 'src/dto/UserDTO';
import { Router } from "@angular/router";
import { ParamDTO } from 'src/dto/ParamDTO';



@Component({
  selector: 'app-user-show',
  templateUrl: './user-show.component.html',
  styleUrls: ['./user-show.component.css']
})
export class UserShowComponent implements OnInit {

  private userList : Array<UserDTO>;
  private userDTO: UserDTO;
  private paramDTO: ParamDTO;

  constructor(private userService: UserService, private router:  Router) { }

  ngOnInit() {

   this.userService.showUser(sessionStorage.getItem("userLogged")).subscribe((data: any) =>{

      if(data != null){
        this.userList = data;
      }
    })
  }
    deleteUser(idUser: number){

      this.paramDTO = new ParamDTO(sessionStorage.getItem("userLogged"),<Object>idUser);

      this.userService.deleteUser(this.paramDTO).subscribe((data: any) =>{

        if(data)
          alert("Cancellazione effettuata");
        else
          alert("Cancellazione fallita");

        this.router.navigateByUrl("homeAdmin");
      })
    }

}

import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from "@angular/router";
import { UserDTO } from 'src/dto/UserDTO';
import {UserService} from "src/services/user.service";
import { ParamDTO } from 'src/dto/ParamDTO';


@Component({
  selector: 'app-user-insert',
  templateUrl: './user-insert.component.html',
  styleUrls: ['./user-insert.component.css']
})
export class UserInsertComponent implements OnInit {
  public userDTO: UserDTO;
  private paramDTO: ParamDTO;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
    //this.userDTO = new UserDTO(null,null,null,null,null,null,null);
  }

    insertUser(f: NgForm){

      this.paramDTO = new ParamDTO(sessionStorage.getItem("userLogged"),this.userDTO);
      
    /*this.userService.insertUser(this.paramDTO).subscribe((data: any) => {

      if(data != null)
        alert("Inserimento effettuato");
      else
        alert("Inserimento fallito");

        this.router.navigateByUrl("/homeAdmin");
    })*/
  }

}

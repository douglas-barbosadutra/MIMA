import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from "@angular/router";
import { UserDTO } from 'src/dto/UserDTO';
import {UserService} from "src/services/user.service";


@Component({
  selector: 'app-user-insert',
  templateUrl: './user-insert.component.html',
  styleUrls: ['./user-insert.component.css']
})
export class UserInsertComponent implements OnInit {
  public userDTO: UserDTO;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
    this.userDTO = new UserDTO(0,"","","","","","",0);
  }

    insertUser(f: NgForm){
      console.log(this.userDTO);
    this.userService.insertUser(this.userDTO).subscribe((data: any) => {

      if(data != null)
        alert("Inserimento effettuato");
      else
        alert("Inserimento fallito");

        this.router.navigateByUrl("/homeAdmin");
    })
  }

}

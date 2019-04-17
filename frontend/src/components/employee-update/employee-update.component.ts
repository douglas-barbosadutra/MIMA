import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/services/user.service';
import { Router } from '@angular/router';
import { UserDTO } from 'src/dto/UserDTO';

@Component({
  selector: 'app-employee-update',
  templateUrl: './employee-update.component.html',
  styleUrls: ['./employee-update.component.css']
})
export class EmployeeUpdateComponent implements OnInit {

  public userDTO: UserDTO;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
    this.userDTO = JSON.parse(localStorage.getItem("currentUserData"));
  }

  updateUser(){
    console.log(this.userDTO);
    this.userService.updateUser(this.userDTO).subscribe((data: UserDTO) => {

      if(data != null)
        alert("Aggiornamento effettuato");
      
      this.router.navigateByUrl("homeEmployee");
    })
  }

  findUser(){
    this.userService.findUser(sessionStorage.getItem("userLogged")).subscribe((data: any) =>{
      if(data != null)
        this.userDTO = data;
    })
  }

}

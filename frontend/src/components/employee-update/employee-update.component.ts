import { Component, OnInit } from '@angular/core';
import { UserDTO } from 'src/dto/UserDTO';
import { Router } from '@angular/router';
import { UserService } from 'src/services/user.service';

@Component({
  selector: 'app-employee-update',
  templateUrl: './employee-update.component.html',
  styleUrls: ['./employee-update.component.css']
})
export class EmployeeUpdateComponent implements OnInit {

  public userDTO: UserDTO;

  constructor(private router: Router, private userSerivce: UserService) { }

  ngOnInit() {
    //this.findUser();
    this.userDTO = new UserDTO(parseInt(sessionStorage.getItem("idUser")),"","","","","","",0);
  }

  updateUser(){

    this.userSerivce.updateUser(this.userDTO).subscribe((data: any) => {

      if(data != null)
        alert("Aggiornamento effettuato");
      else
        alert("Aggiornamento fallito");

        this.router.navigateByUrl("homeEmployee");
    })
  }

  findUser(){
    this.userSerivce.findUser(parseInt(sessionStorage.getItem("idUser"))).subscribe((data: any) =>{
      if(data != null)
        this.userDTO = data;
    })
  }

}

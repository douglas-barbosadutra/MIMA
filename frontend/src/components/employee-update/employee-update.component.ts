import { Component, OnInit } from '@angular/core';
import { UserDTO } from 'src/dto/UserDTO';
import { Router } from '@angular/router';
import { UserService } from 'src/services/user.service';
import { ParamDTO } from 'src/dto/ParamDTO';

@Component({
  selector: 'app-employee-update',
  templateUrl: './employee-update.component.html',
  styleUrls: ['./employee-update.component.css']
})
export class EmployeeUpdateComponent implements OnInit {

  public userDTO: UserDTO;
  private paramDTO: ParamDTO;

  constructor(private router: Router, private userSerivce: UserService) { }

  ngOnInit() {
    //this.userDTO = new UserDTO(null,null,null,null,null,null,null);
  }

  updateUser(){

    this.paramDTO = new ParamDTO(sessionStorage.getItem("userLogged"),this.userDTO);

    this.userSerivce.updateUser(this.paramDTO).subscribe((data: any) => {

      if(data != null)
        alert("Aggiornamento effettuato");
      else
        alert("Aggiornamento fallito");

        this.router.navigateByUrl("homeEmployee");
    })
  }

}

import { Component, OnInit } from '@angular/core';
import { UserDTO } from 'src/dto/UserDTO';
import { EmployeeService } from 'src/services/employee.service';
import { Router } from '@angular/router';
import { EmployeeDTO } from 'src/dto/EmployeeDTO';
import { ParamDTO } from 'src/dto/ParamDTO';
import { UserService } from 'src/services/user.service';

@Component({
  selector: 'app-employee-insert',
  templateUrl: './employee-insert.component.html',
  styleUrls: ['./employee-insert.component.css']
})
export class EmployeeInsertComponent implements OnInit {

  private userLoggedDTO: UserDTO;
  private employeeDTO: EmployeeDTO;
  public userDTO: UserDTO;
  private authorities: Array<string>;

  constructor(private userService: UserService, private employeeService: EmployeeService, private router: Router) { }

  ngOnInit() {
    this.userLoggedDTO = JSON.parse(localStorage.getItem("currentUserData"));
    this.employeeDTO = new EmployeeDTO(null,null,this.userLoggedDTO.id,null,null);
    this.authorities = new Array<string>();
    this.authorities.push("ROLE_USER")
    this.authorities.push("ROLE_EMPLOYEE");
    this.userDTO = new UserDTO(null,null,null,null,null,null,this.authorities);
  }

  insertEmployee(){

    this.userService.insertEmployeeUser(this.userDTO).subscribe((response: UserDTO) =>{
      if(response != null){
        this.employeeDTO.setIdUser(response.id);
        this.employeeDTO.setName(response.firstName);
        this.employeeService.insertEmployee(this.employeeDTO).subscribe((response: EmployeeDTO) =>{
          if(response != null)
            this.router.navigateByUrl("employeeShow");
        })
      } 
    })
  }
}

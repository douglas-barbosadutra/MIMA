import { Component, OnInit } from '@angular/core';
import { UserDTO } from 'src/dto/UserDTO';
import { EmployeeService } from 'src/services/employee.service';
import { Router } from '@angular/router';
import { EmployeeDTO } from 'src/dto/EmployeeDTO';

@Component({
  selector: 'app-employee-insert',
  templateUrl: './employee-insert.component.html',
  styleUrls: ['./employee-insert.component.css']
})
export class EmployeeInsertComponent implements OnInit {

  public userDTO: UserDTO;
  private employeeDTO: EmployeeDTO;

  constructor(private employeeService: EmployeeService, private router: Router) { }

  ngOnInit() {
    this.userDTO = new UserDTO(0,"","","","","","",0);
  }

  insertEmployee(){
      this.employeeDTO = new EmployeeDTO(0,this.userDTO,0,parseInt(sessionStorage.getItem("idUser")));
      this.employeeService.insertEmployee(this.employeeDTO).subscribe((data: any) =>{
        if(data =! null)
          alert("Inserimento effettuato");
        else
          alert("Inserimento fallito")
        this.router.navigateByUrl("homeUser");
      })
  }

}

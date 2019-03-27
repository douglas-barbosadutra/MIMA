import { Component, OnInit } from '@angular/core';
import { EmployeeDTO } from 'src/dto/EmployeeDTO';
import { EmployeeService } from 'src/services/employee.service';
import { Router } from '@angular/router';
import { ParamDTO } from 'src/dto/ParamDTO';

@Component({
  selector: 'app-employee-show',
  templateUrl: './employee-show.component.html',
  styleUrls: ['./employee-show.component.css']
})
export class EmployeeShowComponent implements OnInit {
  private employeeList: Array<EmployeeDTO>;
  private paramDTO: ParamDTO;

  constructor(private employeeService: EmployeeService, private router: Router) { }

  ngOnInit() {
    this.employeeService.showEmployee(sessionStorage.getItem("userLogged")).subscribe((data: any) =>{
      if(data != null){
        this.employeeList = data;
      }
        
    })
  }

  employeeDelete(idUser: number){

    this.paramDTO = new ParamDTO(sessionStorage.getItem("userLogged"),idUser);

    this.employeeService.deleteEmployee(this.paramDTO).subscribe((data: any) =>{
      if(data)
        alert("Cancellazione effettuata");
      else
        alert("Cancellazione fallita");
    })
    this.router.navigateByUrl("homeUser");
  }

  assignTask(idEmployee: number, idUser: number){

    sessionStorage.setItem("idEmployee",JSON.stringify(idEmployee));
    sessionStorage.setItem("idUserEmployee",JSON.stringify(idUser));
    this.router.navigateByUrl("assignTask");
    
  }

}

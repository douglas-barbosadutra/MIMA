import { Component, OnInit } from '@angular/core';
import { EmployeeDTO } from 'src/dto/EmployeeDTO';
import { EmployeeService } from 'src/services/employee.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employee-show',
  templateUrl: './employee-show.component.html',
  styleUrls: ['./employee-show.component.css']
})
export class EmployeeShowComponent implements OnInit {
  private employeeList: Array<EmployeeDTO>;

  constructor(private employeeService: EmployeeService, private router: Router) { }

  ngOnInit() {
    this.employeeService.showEmployee(parseInt(sessionStorage.getItem("idUser"))).subscribe((data: any) =>{
      if(data != null){
        this.employeeList = data;
        console.log(data);
      }
        
    })
  }

  employeeDelete(idUser: number){
    this.employeeService.deleteEmployee(idUser).subscribe((data: any) =>{
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

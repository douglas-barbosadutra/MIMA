import { Component, OnInit } from '@angular/core';
import { EmployeeDTO } from 'src/dto/EmployeeDTO';
import { EmployeeService } from 'src/services/employee.service';

@Component({
  selector: 'app-employee-show',
  templateUrl: './employee-show.component.html',
  styleUrls: ['./employee-show.component.css']
})
export class EmployeeShowComponent implements OnInit {
  private employeeList: Array<EmployeeDTO>;

  constructor(private employeeService: EmployeeService) { }

  ngOnInit() {
    this.employeeService.showEmployee(parseInt(sessionStorage.getItem("idUser"))).subscribe((data: any) =>{
      if(data != null){
        this.employeeList = data;
        console.log(data);
      }
        
    })
  }

}

import { Component, OnInit } from '@angular/core';
import { EmployeeService } from 'src/services/employee.service';
import { ManufactoringService } from 'src/services/manufactoring.service';
import { TimeDTO } from 'src/dto/TimeDTO';
import { Router } from '@angular/router';

@Component({
  selector: 'app-task-employee-show',
  templateUrl: './task-employee-show.component.html',
  styleUrls: ['./task-employee-show.component.css']
})
export class TaskEmployeeShowComponent implements OnInit {
  private idTask: number;
  private timeList: Array<TimeDTO>;

  constructor(private employeeService: EmployeeService, private manufactoringService: ManufactoringService, private router: Router) { }

  ngOnInit() {
    this.findEmployee();
  }

  findEmployee(){
    this.employeeService.findEmployee(parseInt(sessionStorage.getItem("idUser"))).subscribe((data: any) =>{
      if(data != null){
        if(data.idTask == 0){
          alert("Non hai nessun task assegnato");
          this.router.navigateByUrl("homeEmployee");
        }
        else{
          this.idTask = data.idTask;
          this.showTime(this.idTask);
        }
      }
    })
  }

  showTime(idTask: number){
    this.manufactoringService.showTime(idTask).subscribe((data: any) =>{
      if(data != null)
        this.timeList = data;
    })
  }



}

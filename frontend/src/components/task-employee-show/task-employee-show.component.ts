import { Component, OnInit, OnDestroy } from '@angular/core';
import { EmployeeService } from 'src/services/employee.service';
import { ManufactoringService } from 'src/services/manufactoring.service';
import { TimeDTO } from 'src/dto/TimeDTO';
import { Router } from '@angular/router';
import { EmployeeDTO } from 'src/dto/EmployeeDTO';
import { UserDTO } from 'src/dto/UserDTO';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-task-employee-show',
  templateUrl: './task-employee-show.component.html',
  styleUrls: ['./task-employee-show.component.css']
})
export class TaskEmployeeShowComponent implements OnInit,OnDestroy {
  
  timeList: TimeDTO[] = [];
  private userDTO: UserDTO;
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<TimeDTO> = new Subject();

  constructor(private employeeService: EmployeeService, private manufactoringService: ManufactoringService, private router: Router) { }

  ngOnInit() {

    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 10
    };

    this.userDTO = JSON.parse(localStorage.getItem("currentUserData"));
    this.findEmployee();
  }

  ngOnDestroy(): void {
    // Do not forget to unsubscribe the event
    this.dtTrigger.unsubscribe();
  }

  findEmployee(){
    this.employeeService.findEmployeeByIdUser(this.userDTO.id).subscribe((data: EmployeeDTO) =>{
      if(data != null){
        if(data.taskId == null){
          alert("Non hai nessun task assegnato");
          this.router.navigateByUrl("homeEmployee");
        }
        else{
          this.showTime(data.taskId);
        }
      }
    })
  }

  showTime(idTask: number){
    this.manufactoringService.showTime(idTask).subscribe((data: Array<TimeDTO>) =>{
      if(data != null){
        this.timeList = data;
        this.dtTrigger.next();
      }
    })
  }



}

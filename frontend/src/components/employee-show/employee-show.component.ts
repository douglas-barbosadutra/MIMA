import { Component, OnInit } from '@angular/core';
import { EmployeeDTO } from 'src/dto/EmployeeDTO';
import { EmployeeService } from 'src/services/employee.service';
import { Router } from '@angular/router';
import { ParamDTO } from 'src/dto/ParamDTO';
import { UserDTO } from 'src/dto/UserDTO';
import { EmployeeShowDTO } from 'src/dto/EmployeeShowDTO';
import { TaskService } from 'src/services/task.service';
import { TaskDTO } from 'src/dto/TaskDTO';
import { UserService } from 'src/services/user.service';

@Component({
  selector: 'app-employee-show',
  templateUrl: './employee-show.component.html',
  styleUrls: ['./employee-show.component.css']
})
export class EmployeeShowComponent implements OnInit {
  private userLoggedDTO: UserDTO;
  public employeeShowList: Array<EmployeeShowDTO>;
  private employeeList: Array<EmployeeDTO>;

  constructor(private userService: UserService, private taskService: TaskService, private employeeService: EmployeeService, private router: Router) { }

  ngOnInit() {
    this.employeeShowList = new Array<EmployeeShowDTO>();
    this.userLoggedDTO = JSON.parse(localStorage.getItem("currentUserData"));

    this.employeeService.showEmployee(this.userLoggedDTO.id).subscribe((response: Array<EmployeeDTO>) =>{
      if(response != null){
        this.employeeList = response;
        this.showEmployee();
      }
    })
  }

  showEmployee(){

    for(let employee of this.employeeList){
      if(employee.taskId != null){
        this.taskService.findOne(employee.taskId).subscribe((response: TaskDTO) =>{
          if(response != null)
          this.employeeShowList.push(new EmployeeShowDTO(employee.id,employee.idUser,employee.name,response.description));
        })
      }
      else
        this.employeeShowList.push(new EmployeeShowDTO(employee.id,employee.idUser,employee.name,"Nessun task assegnato"));
    }

  }

  employeeDelete(idEmployee: number, idUser: number){

    this.userService.deleteEmployeeUser(idUser).subscribe((response: boolean) =>{
      if(response){
        this.employeeService.deleteEmployee(idEmployee).subscribe((response2: boolean) =>{
          if(response2)
            location.reload(true);
        })
      }
    })
    
  }

  assignTask(idEmployee: number){

    sessionStorage.setItem("idEmployee",JSON.stringify(idEmployee));
    this.router.navigateByUrl("assignTask");
  }

}

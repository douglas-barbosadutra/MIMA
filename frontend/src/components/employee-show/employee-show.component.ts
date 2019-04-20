import { Component, OnInit,ViewChild, ElementRef, OnDestroy } from '@angular/core';
import { EmployeeDTO } from 'src/dto/EmployeeDTO';
import { EmployeeService } from 'src/services/employee.service';
import { Router } from '@angular/router';
import { UserDTO } from 'src/dto/UserDTO';
import { EmployeeShowDTO } from 'src/dto/EmployeeShowDTO';
import { TaskService } from 'src/services/task.service';
import { TaskDTO } from 'src/dto/TaskDTO';
import { UserService } from 'src/services/user.service';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Subject } from 'rxjs';
declare var $;

@Component({
  selector: 'app-employee-show',
  templateUrl: './employee-show.component.html',
  styleUrls: ['./employee-show.component.css'],
  providers: [NgbModalConfig, NgbModal]
})
export class EmployeeShowComponent implements OnInit,OnDestroy {
  private userLoggedDTO: UserDTO;
  employeeShowList: EmployeeShowDTO[] = [];
  public userDTO: UserDTO;
  private employeeList: Array<EmployeeDTO>;
  private employeeDTO: EmployeeDTO;
  private authorities: Array<string>;
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();

  constructor(private config: NgbModalConfig, private modalService: NgbModal, private userService: UserService, private taskService: TaskService, private employeeService: EmployeeService, private router: Router) {
    config.backdrop = 'static';
    config.keyboard = false;
   }

   ngOnDestroy(): void {
    // Do not forget to unsubscribe the event
    this.dtTrigger.unsubscribe();
  }

  ngOnInit() {

    

    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 10
    };

    this.authorities = new Array<string>();
    this.authorities.push("ROLE_USER")
    this.authorities.push("ROLE_EMPLOYEE");
    this.userDTO = new UserDTO(null,null,null,null,null,null,this.authorities);
    this.employeeShowList = new Array<EmployeeShowDTO>();
    this.userLoggedDTO = JSON.parse(localStorage.getItem("currentUserData"));
    this.employeeDTO = new EmployeeDTO(null,null,this.userLoggedDTO.id,null,null);

    this.employeeService.showEmployee(this.userLoggedDTO.id).subscribe((response: Array<EmployeeDTO>) =>{
      if(response != null){
        this.dtTrigger.next();
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

  assignTask(idEmployee: number, nameEmployee: string){
    sessionStorage.setItem("idEmployee",JSON.stringify(idEmployee));
    sessionStorage.setItem("nameEmployee",nameEmployee);
    this.router.navigateByUrl("assignTask");
  }

  open(content) {
    this.modalService.open(content);
  }

  insertEmployee(){

    this.userService.insertEmployeeUser(this.userDTO).subscribe((response: UserDTO) =>{
      if(response != null){
        this.employeeDTO.setIdUser(response.id);
        this.employeeDTO.setName(response.firstName);
        this.employeeService.insertEmployee(this.employeeDTO).subscribe((response: EmployeeDTO) =>{
          if(response != null)
            location.reload(true);
        })
      } 
    })
  }

}

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { EmployeeDTO } from 'src/dto/EmployeeDTO';
import { Observable } from 'rxjs';
import { ParamDTO } from 'src/dto/ParamDTO';
import { UserDTO } from 'src/dto/UserDTO';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http: HttpClient) { }

  auth() {
    var user = JSON.parse(localStorage.getItem("currentUser")) as UserDTO;
    if(user) {
        return "Bearer " + user.authorities;
    } else {
        return "";
    }
  }

  findEmployee(idEmployee: number): Observable<EmployeeDTO>{
    return this.http.get<EmployeeDTO>("http://localhost:8080/machineMicroservice/api/employees/"+idEmployee, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  findEmployeeByIdUser(idUser: number): Observable<EmployeeDTO>{
    return this.http.get<EmployeeDTO>("http://localhost:8080/machineMicroservice/api/employeesByIdUserOwner?id="+idUser, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  insertEmployee(employeeDTO: EmployeeDTO): Observable<EmployeeDTO>{
    return this.http.post<EmployeeDTO>("http://localhost:8080/machineMicroservice/api/employees",employeeDTO, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  showEmployee(idBusinessOwner: number): Observable<Array<EmployeeDTO>>{
    return this.http.get<Array<EmployeeDTO>>("http://localhost:8080/machineMicroservice/api/employeesByBusinessOwner?id="+idBusinessOwner, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  deleteEmployee(idEmployee: number): Observable<Boolean>{
    return this.http.delete<Boolean>("http://localhost:8080/machineMicroservice/api/employees/"+idEmployee, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  assignTask(employeeDTO: EmployeeDTO): Observable<EmployeeDTO>{
    return this.http.put<EmployeeDTO>("http://localhost:8080/machineMicroservice/api/employees",employeeDTO, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }
}

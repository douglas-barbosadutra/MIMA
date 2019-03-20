import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { EmployeeDTO } from 'src/dto/EmployeeDTO';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http: HttpClient) { }

  findEmployee(idUser: number): Observable<EmployeeDTO>{
    return this.http.get<EmployeeDTO>('http://localhost:8080/Employee/findEmployee?idUser='+idUser);
  }

  insertEmployee(employeeDTO: EmployeeDTO): Observable<EmployeeDTO>{
    return this.http.post<EmployeeDTO>('http://localhost:8080/Employee/insertEmployee',employeeDTO);
  }

  showEmployee(idBusinessOwner: number): Observable<Array<EmployeeDTO>>{
    return this.http.get<Array<EmployeeDTO>>('http://localhost:8080/Employee/showEmployee?idBusinessOwner='+idBusinessOwner);
  }

  deleteEmployee(idUser: number){
    return this.http.delete('http://localhost:8080/Employee/deleteEmployee?idUser='+idUser);
  }

  assignTask(employeeDTO: EmployeeDTO): Observable<EmployeeDTO>{
    return this.http.put<EmployeeDTO>('http://localhost:8080/Employee/assignTask',employeeDTO);
  }
}

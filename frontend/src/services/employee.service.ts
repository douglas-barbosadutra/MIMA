import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { EmployeeDTO } from 'src/dto/EmployeeDTO';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http: HttpClient) { }

  findEmployee(idUser: number){
    return this.http.get('http://localhost:8080/Employee/findEmployee?idUser='+idUser);
  }

  insertEmployee(employeeDTO: EmployeeDTO){
    return this.http.post('http://localhost:8080/Employee/insertEmployee',employeeDTO);
  }

  showEmployee(idBusinessOwner: number){
    return this.http.get('http://localhost:8080/Employee/showEmployee?idBusinessOwner='+idBusinessOwner);
  }

  deleteEmployee(idUser: number){
    return this.http.delete('http://localhost:8080/Employee/deleteEmployee?idUser='+idUser);
  }

  assignTask(employeeDTO: EmployeeDTO){
    return this.http.put('http://localhost:8080/Employee/assignTask',employeeDTO);
  }
}

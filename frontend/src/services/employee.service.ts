import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http: HttpClient) { }

  findEmployee(idUser: number){
    return this.http.get('http://localhost:8080/Employee/findEmployee?idUser='+idUser);
  }
}

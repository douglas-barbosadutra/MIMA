import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { EmployeeDTO } from 'src/dto/EmployeeDTO';
import { Observable } from 'rxjs';
import { ParamDTO } from 'src/dto/ParamDTO';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http: HttpClient) { }

  findEmployee(idUser: number): Observable<EmployeeDTO>{
    return this.http.get<EmployeeDTO>('http://localhost:8080/Employee/findEmployee?idUser='+idUser);
  }

  insertEmployee(paramDTO: ParamDTO): Observable<EmployeeDTO>{
    return this.http.post<EmployeeDTO>('http://localhost:8080/Employee/insertEmployee',paramDTO);
  }

  showEmployee(jwt: string): Observable<Array<EmployeeDTO>>{
    return this.http.get<Array<EmployeeDTO>>('http://localhost:8080/Employee/showEmployee?jwt='+jwt);
  }

  deleteEmployee(paramDTO: ParamDTO){
    return this.http.post('http://localhost:8080/Employee/deleteEmployee?',paramDTO);
  }

  assignTask(paramDTO: ParamDTO): Observable<EmployeeDTO>{
    return this.http.put<EmployeeDTO>('http://localhost:8080/Employee/assignTask',paramDTO);
  }
}

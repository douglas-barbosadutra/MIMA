import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserDTO } from 'src/dto/UserDTO';
import { WBSDTO } from 'src/dto/WBSDTO';

@Injectable({
  providedIn: 'root'
})
export class WbsService {

  constructor(private http: HttpClient) { }

  showWbs(userDTO: UserDTO){
    return this.http.post('http://localhost:8080/WBS/showWbs', userDTO);
  }

  insertWbs(wbsDTO: WBSDTO){
    return this.http.post('http://localhost:8080/WBS/insertWbs', wbsDTO);
  }

  deleteWbs(wbsDTO: WBSDTO){
    return this.http.post('http://localhost:8080/WBS/deletetWbs', wbsDTO);
  }
}

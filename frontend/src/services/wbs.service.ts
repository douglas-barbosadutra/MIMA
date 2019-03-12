import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserDTO } from 'src/dto/UserDTO';
import { WBSDTO } from 'src/dto/WBSDTO';

@Injectable({
  providedIn: 'root'
})
export class WbsService {

  constructor(private http: HttpClient) { }

  showWbs(idUser: number){
    return this.http.get('http://localhost:8080/WBS/showWbs?idUser='+idUser);
  }

  insertWbs(wbsDTO: WBSDTO){
    return this.http.post('http://localhost:8080/WBS/insertWbs', wbsDTO);
  }

  deleteWbs(idWbs: number){
    return this.http.delete('http://localhost:8080/WBS/deleteWbs?idWbs='+idWbs);
  }
}

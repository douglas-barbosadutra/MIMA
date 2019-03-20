import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserDTO } from 'src/dto/UserDTO';
import { WBSDTO } from 'src/dto/WBSDTO';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WbsService {

  constructor(private http: HttpClient) { }

  showWbs(idUser: number): Observable<Array<WBSDTO>>{
    return this.http.get<Array<WBSDTO>>('http://localhost:8080/WBS/showWbs?idUser='+idUser);
  }

  insertWbs(wbsDTO: WBSDTO): Observable<WBSDTO>{
    return this.http.post<WBSDTO>('http://localhost:8080/WBS/insertWbs', wbsDTO);
  }

  deleteWbs(idWbs: number){
    return this.http.delete('http://localhost:8080/WBS/deleteWbs?idWbs='+idWbs);
  }
}

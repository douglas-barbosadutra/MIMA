import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { WBSDTO } from 'src/dto/WBSDTO';
import { Observable } from 'rxjs';
import { ParamDTO } from 'src/dto/ParamDTO';

@Injectable({
  providedIn: 'root'
})
export class WbsService {

  constructor(private http: HttpClient) { }

  showWbs(jwt: string): Observable<Array<WBSDTO>>{
    console.log("stiamo partendo");
    return this.http.get<Array<WBSDTO>>('http://localhost:8083/WBS/showWbs?jwt='+jwt);
  }

  insertWbs(param: ParamDTO): Observable<WBSDTO>{
    return this.http.post<WBSDTO>('http://localhost:8083/WBS/insertWbs', param);
  }

  deleteWbs(jwt: string, idWbs: number){
    return this.http.delete('http://localhost:8083/WBS/deleteWbs?jwt='+jwt+'&idWbs='+idWbs);
  }
}

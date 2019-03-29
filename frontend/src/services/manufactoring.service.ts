import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TimeDTO } from 'src/dto/TimeDTO';

@Injectable({
  providedIn: 'root'
})
export class ManufactoringService {

  constructor(private http: HttpClient) { }

  showTime(jwt: string, idTask: number): Observable<Array<TimeDTO>>{
    return this.http.get<Array<TimeDTO>>('http://localhost:8083/Manufacturing/showTime??jwt='+ jwt + 'idTask='+idTask);
  }

}

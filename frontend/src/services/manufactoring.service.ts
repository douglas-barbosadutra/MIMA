import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ManufactoringService {

  constructor(private http: HttpClient) { }

  showTime(idTask: number){
    return this.http.get('http://localhost:8080/Manufacturing/showTime?idTask='+idTask);
  }

}

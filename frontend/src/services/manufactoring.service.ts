import { Injectable } from '@angular/core';
import { TaskDTO } from 'src/dto/TaskDTO';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ManufactoringService {

  constructor(private http: HttpClient) { }

  showTime(taskDTO: TaskDTO){
    return this.http.post('http://localhost:8080/Manufacturing/showTime', taskDTO);
  }

}

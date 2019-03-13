import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TaskScheduledDTO } from 'src/dto/TaskScheduledDTO';
import { OperationSchedulingDTO } from 'src/dto/OperationSchedulingDTO';
import { Observable} from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class TaskScheduledService {

  constructor(private http: HttpClient) { }

  showTaskScheduled(idScheduling: number): Observable<Array<TaskScheduledDTO>>{
    return this.http.get<Array<TaskScheduledDTO>>('http://localhost:8080/TaskScheduled/showTaskScheduled?idScheduling=' + idScheduling);
  }

  insertOperationScheduling(osdto: OperationSchedulingDTO){
    return this.http.post('http://localhost:8080/TaskScheduled/insertOperationScheduling', osdto);
  }

  deleteTaskScheduled(idTaskScheduled: number){
    return this.http.delete('http://localhost:8080/TaskScheduled/deleteTaskScheduled?idTaskScheduled=' + idTaskScheduled);
  }
  insertTaskScheduled(taskScheduledDTO: TaskScheduledDTO) : any{
    return this.http.post('http://localhost:8080/TaskScheduled/insertTaskScheduled', taskScheduledDTO);
  }
}

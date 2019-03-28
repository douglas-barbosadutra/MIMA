import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TaskScheduledDTO } from 'src/dto/TaskScheduledDTO';
import { OperationSchedulingDTO } from 'src/dto/OperationSchedulingDTO';
import { Observable} from 'rxjs';
import { ParamDTO } from 'src/dto/ParamDTO';



@Injectable({
  providedIn: 'root'
})
export class TaskScheduledService {

  constructor(private http: HttpClient) { }

  showTaskScheduled(idScheduling: number, jwt: string): Observable<Array<TaskScheduledDTO>>{
    return this.http.get<Array<TaskScheduledDTO>>('http://localhost:8082/TaskScheduled/showTaskScheduled?idScheduling=' + idScheduling+'&jwt='+jwt);
  }

  showOperationScheduling(idScheduling: number, jwt: string): Observable<Array<OperationSchedulingDTO>>{
    return this.http.get<Array<OperationSchedulingDTO>>('http://localhost:8082/TaskScheduled/showOperationScheduling?idScheduling=' + idScheduling+'&jwt='+jwt);
  }

  insertOperationScheduling(paramDTO: ParamDTO){
    return this.http.post('http://localhost:8082/TaskScheduled/insertOperationScheduling', paramDTO);
  }

  insertOutput(idItem: number, idOperationScheduling: number, jwt: string){
    return this.http.get('http://localhost:8082/TaskScheduled/insertOutput?idItem='+idItem+"&idOperationScheduling="+idOperationScheduling+'&jwt='+jwt);
  }

  deleteTaskScheduled(idTaskScheduled: number){
    return this.http.delete('http://localhost:8082/TaskScheduled/deleteTaskScheduled?idTaskScheduled=' + idTaskScheduled);
  }
  insertTaskScheduled(taskScheduledDTO: TaskScheduledDTO) : any{
    console.log(taskScheduledDTO);
    return this.http.post('http://localhost:8082/TaskScheduled/insertTaskScheduled', taskScheduledDTO);
  }
}

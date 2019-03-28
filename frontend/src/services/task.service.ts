import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { TaskDTO } from 'src/dto/TaskDTO';
import { Observable } from 'rxjs';
import { ParamDTO } from 'src/dto/ParamDTO';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private http: HttpClient) { }

  insertTask(paramDTO: ParamDTO): Observable<TaskDTO>{
    return this.http.post<TaskDTO>( 'http://localhost:8082/Task/insertTask', paramDTO);
  }

  showTask(idMachine: number, jwt: string): Observable<Array<TaskDTO>>{
    return this.http.get<Array<TaskDTO>>('http://localhost:8082/Task/showTask?idMachine='+idMachine+'&jwt='+jwt);
  }

  deleteTask(idTask: number, jwt: string){
    return this.http.delete('http://localhost:8082/Task/deleteTask?idTask='+idTask+'&jwt='+jwt);
  }
}

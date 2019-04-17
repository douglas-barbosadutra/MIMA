import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TaskScheduledDTO } from 'src/dto/TaskScheduledDTO';
import { OperationSchedulingDTO } from 'src/dto/OperationSchedulingDTO';
import { Observable} from 'rxjs';
import { ParamDTO } from 'src/dto/ParamDTO';
import { UserDTO } from 'src/dto/UserDTO';



@Injectable({
  providedIn: 'root'
})
export class TaskScheduledService {

  constructor(private http: HttpClient) { }

  auth() {
    var user = JSON.parse(localStorage.getItem("currentUser")) as UserDTO;
    if(user) {
        return "Bearer " + user.authorities;
    } else {
        return "";
    }
  }

  findOne(id: number): Observable<TaskScheduledDTO>{
    return this.http.get<TaskScheduledDTO>("http://localhost:8080/machineMicroservice/api/task-scheduleds/"+id, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  showTaskScheduled(idScheduling: number): Observable<Array<TaskScheduledDTO>>{
    return this.http.get<Array<TaskScheduledDTO>>("http://localhost:8080/machineMicroservice/api/taskScheduledsByScheduling?id="+idScheduling, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  showOperationScheduling(idScheduling: number): Observable<Array<OperationSchedulingDTO>>{
    return this.http.get<Array<OperationSchedulingDTO>>('http://localhost:8080/machineMicroservice/api/operationScheduling?id=' + idScheduling, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  insertOperationScheduling(osDTO: OperationSchedulingDTO){
    return this.http.post("http://localhost:8080/machineMicroservice/api/operationScheduling",osDTO, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  insertOutput(taskScheduledDTO: TaskScheduledDTO): Observable<TaskScheduledDTO>{
    return this.http.put<TaskScheduledDTO>("http://localhost:8080/machineMicroservice/api/task-scheduleds",taskScheduledDTO, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  deleteTaskScheduled(id: number){
    return this.http.delete("http://localhost:8080/machineMicroservice/api/task-scheduleds/"+id, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }
  insertTaskScheduled(taskScheduledDTO: TaskScheduledDTO): Observable<TaskScheduledDTO>{
    return this.http.post<TaskScheduledDTO>("http://localhost:8080/machineMicroservice/api/task-scheduleds",taskScheduledDTO, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }
}

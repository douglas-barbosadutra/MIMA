import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { TaskDTO } from 'src/dto/TaskDTO';
import { Observable } from 'rxjs';
import { ParamDTO } from 'src/dto/ParamDTO';
import { UserDTO } from 'src/dto/UserDTO';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private http: HttpClient) { }

  auth() {
    var user = JSON.parse(localStorage.getItem("currentUser")) as UserDTO;
    if(user) {
        return "Bearer " + user.authorities;
    } else {
        return "";
    }
  }

  insertTask(taskDTO: TaskDTO): Observable<TaskDTO>{
    return this.http.post<TaskDTO>("http://localhost:8080/machineMicroservice/api/tasks",taskDTO, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  showTask(idMachine: number): Observable<Array<TaskDTO>>{
    return this.http.get<Array<TaskDTO>>("http://localhost:8080/machineMicroservice/api/tasksByMachine/"+idMachine, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  findOne(id: number): Observable<TaskDTO>{
    return this.http.get<TaskDTO>("http://localhost:8080/machineMicroservice/api/tasks/"+id, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  deleteTask(idTask: number){
    return this.http.delete("http://localhost:8080/machineMicroservice/api/tasks/"+idTask, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }
}

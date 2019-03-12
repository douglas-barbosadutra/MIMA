import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { TaskDTO } from 'src/dto/TaskDTO';
import { MachineDTO } from 'src/dto/MachineDTO';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private http: HttpClient) { }

  insertTask(taskDTO: TaskDTO){
    return this.http.post( 'http://localhost:8080/Task/insertTask', taskDTO);
  }

  showTask(idMachine: number){
    return this.http.get('http://localhost:8080/Task/showTask?idMachine='+idMachine);
  }

  deleteTask(idTask: number){
    return this.http.delete('http://localhost:8080/Task/deleteTask?idTask='+idTask);
  }
}

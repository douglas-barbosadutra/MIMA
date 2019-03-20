import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { TaskDTO } from 'src/dto/TaskDTO';
import { MachineDTO } from 'src/dto/MachineDTO';
import { Observable } from 'rxjs';
import { InstructionDTO } from 'src/dto/InstructionDTO';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private http: HttpClient) { }

  insertTask(taskDTO: TaskDTO): Observable<InstructionDTO>{
    return this.http.post<InstructionDTO>( 'http://localhost:8080/Task/insertTask', taskDTO);
  }

  showTask(idMachine: number): Observable<Array<InstructionDTO>>{
    return this.http.get<Array<InstructionDTO>>('http://localhost:8080/Task/showTask?idMachine='+idMachine);
  }

  deleteTask(idTask: number){
    return this.http.delete('http://localhost:8080/Task/deleteTask?idTask='+idTask);
  }
}

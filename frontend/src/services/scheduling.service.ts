import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SchedulingDTO } from 'src/dto/SchedulingDTO';
import { MachineDTO } from 'src/dto/MachineDTO';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SchedulingService {

  constructor(private http: HttpClient) { }

  insertScheduling(schedulingDTO: SchedulingDTO): Observable<SchedulingDTO>{
    return this.http.post<SchedulingDTO>( 'http://localhost:8080/Scheduling/insertScheduling', schedulingDTO);
  }

  showScheduling(idMachine: number): Observable<Array<SchedulingDTO>>{
    return this.http.get<Array<SchedulingDTO>>('http://localhost:8080/Scheduling/showScheduling?idMachine='+idMachine);
  }

  deleteScheduling(idScheduling: number){
    return this.http.delete('http://localhost:8080/Scheduling/deleteScheduling?idScheduling='+idScheduling);
  }

  updateScheduling(schedulingDTO: SchedulingDTO): Observable<SchedulingDTO>{
    return this.http.put<SchedulingDTO>( 'http://localhost:8080/Scheduling/insertScheduling', schedulingDTO);
  }
}

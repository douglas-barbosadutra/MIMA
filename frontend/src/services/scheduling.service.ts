import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SchedulingDTO } from 'src/dto/SchedulingDTO';
import { MachineDTO } from 'src/dto/MachineDTO';

@Injectable({
  providedIn: 'root'
})
export class SchedulingService {

  constructor(private http: HttpClient) { }

  insertScheduling(schedulingDTO: SchedulingDTO){
    return this.http.post( 'http://localhost:8080/Scheduling/insertScheduling', schedulingDTO);
  }

  showScheduling(idMachine: number){
    return this.http.get('http://localhost:8080/Scheduling/showScheduling?idMachine='+idMachine);
  }

  deleteScheduling(idScheduling: number){
    return this.http.delete('http://localhost:8080/Scheduling/deleteScheduling?idScheduling='+idScheduling);
  }

  updateScheduling(schedulingDTO: SchedulingDTO){
    return this.http.put( 'http://localhost:8080/Scheduling/insertScheduling', schedulingDTO);
  }
}

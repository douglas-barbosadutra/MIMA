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

  showScheduling(machineDTO: MachineDTO){
    return this.http.post('http://localhost:8080/Scheduling/showScheduling', machineDTO);
  }

  deleteScheduling(schedulingDTO: SchedulingDTO){
    return this.http.post('http://localhost:8080/Scheduling/deleteScheduling', schedulingDTO);
  }
}

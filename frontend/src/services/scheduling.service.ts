import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SchedulingDTO } from 'src/dto/SchedulingDTO';
import { Observable } from 'rxjs';
import { ParamDTO } from 'src/dto/ParamDTO';

@Injectable({
  providedIn: 'root'
})
export class SchedulingService {

  constructor(private http: HttpClient) { }

  insertScheduling(paramDTO: ParamDTO): Observable<SchedulingDTO>{
    return this.http.post<SchedulingDTO>( 'http://localhost:8082/Scheduling/insertScheduling', paramDTO);
  }

  showScheduling(idMachine: number, jwt: string): Observable<Array<SchedulingDTO>>{
    return this.http.get<Array<SchedulingDTO>>('http://localhost:8082/Scheduling/showScheduling?idMachine='+idMachine+'&jwt='+jwt);
  }

  deleteScheduling(idScheduling: number, jwt: string){
    return this.http.delete('http://localhost:8082/Scheduling/deleteScheduling?idScheduling='+idScheduling+'&jwt='+jwt);
  }

  updateScheduling(paramDTO: ParamDTO): Observable<SchedulingDTO>{
    return this.http.put<SchedulingDTO>( 'http://localhost:8082/Scheduling/updateScheduling', paramDTO);
  }
}

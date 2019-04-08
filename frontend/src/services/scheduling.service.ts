import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SchedulingDTO } from 'src/dto/SchedulingDTO';
import { Observable } from 'rxjs';
import { ParamDTO } from 'src/dto/ParamDTO';
import { UserDTO } from 'src/dto/UserDTO';

@Injectable({
  providedIn: 'root'
})
export class SchedulingService {

  constructor(private http: HttpClient) { }

  auth() {
    var user = JSON.parse(localStorage.getItem("currentUser")) as UserDTO;
    if(user) {
        return "Bearer " + user.authorization;
    } else {
        return "";
    }
  }

  insertScheduling(schedulingDTO: SchedulingDTO): Observable<SchedulingDTO>{
    return this.http.post<SchedulingDTO>("http://localhost:8080/machineMicroservice/api/schedulings",schedulingDTO, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  showScheduling(idMachine: number): Observable<Array<SchedulingDTO>>{
    return this.http.get<Array<SchedulingDTO>>("http://localhost:8080/machineMicroservice/api/schedulingsByIdMachine/"+idMachine, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  deleteScheduling(idScheduling: number){
    return this.http.delete("http://localhost:8080/machineMicroservice/api/schedulings/"+idScheduling, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  updateScheduling(schedulingDTO: SchedulingDTO): Observable<SchedulingDTO>{
    return this.http.put<SchedulingDTO>("http://localhost:8080/machineMicroservice/api/schedulings",schedulingDTO, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }
}

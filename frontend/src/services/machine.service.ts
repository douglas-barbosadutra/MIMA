import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { MachineDTO } from '../dto/MachineDTO';
import { Observable } from 'rxjs';
import { ParamDTO } from 'src/dto/ParamDTO';
import { UserDTO } from 'src/dto/UserDTO';

@Injectable({
  providedIn: 'root'
})
export class MachineService {

  constructor(private http: HttpClient) { }

  auth() {
    var user = JSON.parse(localStorage.getItem("currentUser")) as UserDTO;
    if(user) {
        return "Bearer " + user.authorities;
    } else {
        return "";
    }
  }

  findOne(idMachine: number):Observable<MachineDTO>{
    return this.http.get<MachineDTO>('http://localhost:8080/machineMicroservice/api/machines/'+idMachine, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  insertMachine(machineDTO: MachineDTO): Observable<MachineDTO>{
    return this.http.post<MachineDTO>('http://localhost:8080/machineMicroservice/api/machines', machineDTO, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  showMachine(idUser: number): Observable<Array<MachineDTO>>{
    return this.http.get<Array<MachineDTO>>('http://localhost:8080/machineMicroservice/api/allMachines/'+idUser, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  deleteMachine(idMachine: number){
    return this.http.delete('http://localhost:8080/machineMicroservice/api/machines/'+idMachine, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }
}

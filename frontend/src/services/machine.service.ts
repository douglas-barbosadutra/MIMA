import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { MachineDTO } from '../dto/MachineDTO';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MachineService {

  constructor(private http: HttpClient) { }

  insertMachine(machineDTO: MachineDTO): Observable<MachineDTO>{
    return this.http.post<MachineDTO>( 'http://localhost:8080/Machine/insertMachine', machineDTO);
  }

  showMachine(): Observable<Array<MachineDTO>>{
    return this.http.get<Array<MachineDTO>>('http://localhost:8080/Machine/showMachine?idUser='+parseInt(sessionStorage.getItem("idUser")));
  }

  deleteMachine(idMachine: number){
    return this.http.delete('http://localhost:8080/Machine/deleteMachine?idMachine='+idMachine);
  }
}

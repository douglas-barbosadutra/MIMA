import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { MachineDTO } from '../dto/MachineDTO';

@Injectable({
  providedIn: 'root'
})
export class MachineService {

  constructor(private http: HttpClient) { }

  insertMachine(machineDTO: MachineDTO){
    return this.http.post( 'http://localhost:8080/Machine/insertMachine', machineDTO);
  }

  showMachine(){
    return this.http.get('http://localhost:8080/Machine/showMachine?idUser='+parseInt(sessionStorage.getItem("idUser")));
  }

  deleteMachine(idMachine: number){
    return this.http.delete('http://localhost:8080/Machine/deleteMachine?idMachine='+idMachine);
  }
}

import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { MachineDTO } from '../dto/MachineDTO';
import { UserDTO } from '../dto/UserDTO';

@Injectable({
  providedIn: 'root'
})
export class MachineService {

  constructor(private http: HttpClient) { }

  insertMachine(machineDTO: MachineDTO){
    return this.http.post( 'http://localhost:8080/Machine/insertMachine', machineDTO);
  }

  showMachine(userDTO: UserDTO){
    return this.http.post('http://localhost:8080/Machine/showMachine', userDTO);
  }

  deleteMachine(machineDTO: MachineDTO){
    return this.http.post('http://localhost:8080/Machine/deleteMachine', machineDTO);
  }
}

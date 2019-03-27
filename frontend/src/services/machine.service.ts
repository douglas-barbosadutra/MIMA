import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { MachineDTO } from '../dto/MachineDTO';
import { Observable } from 'rxjs';
import { ParamDTO } from 'src/dto/ParamDTO';

@Injectable({
  providedIn: 'root'
})
export class MachineService {

  constructor(private http: HttpClient) { }

  insertMachine(paramDTO: ParamDTO): Observable<MachineDTO>{
    return this.http.post<MachineDTO>( 'http://localhost:8082/Machine/insertMachine', paramDTO);
  }

  showMachine(jwt: string): Observable<Array<MachineDTO>>{
    return this.http.get<Array<MachineDTO>>('http://localhost:8082/Machine/showMachine?jwt='+jwt);
  }

  deleteMachine(paramDTO: ParamDTO){
    return this.http.post('http://localhost:8082/Machine/deleteMachine',paramDTO);
  }
}

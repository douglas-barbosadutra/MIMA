import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { InstructionDTO } from 'src/dto/InstructionDTO';
import { Observable } from 'rxjs';
import { ParamDTO } from 'src/dto/ParamDTO';

@Injectable({
  providedIn: 'root'
})
export class InstructionService {

  constructor(private http: HttpClient) { }

  insertInstruction(param: ParamDTO): Observable<InstructionDTO>{
    return this.http.post<InstructionDTO>( 'http://localhost:8083/Instruction/insertInstruction', param);
  }

  showInstruction(jwt: string, idTask: number): Observable<Array<InstructionDTO>>{
    return this.http.get<Array<InstructionDTO>>('http://localhost:8083/Instruction/showInstruction?jwt='+jwt+'&idTask='+idTask);
  }

  deleteInstruction(jwt: string, idInstruction: number){
    return this.http.delete('http://localhost:8083/Instruction/deleteInstruction?jwt='+jwt+'&idInstruction='+idInstruction);
  }
}

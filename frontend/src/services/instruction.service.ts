import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { InstructionDTO } from 'src/dto/InstructionDTO';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InstructionService {

  constructor(private http: HttpClient) { }

  insertInstruction(instructionDTO: InstructionDTO): Observable<InstructionDTO>{
    return this.http.post<InstructionDTO>( 'http://localhost:8080/Instruction/insertInstruction', instructionDTO);
  }

  showInstruction(idTask: number): Observable<Array<InstructionDTO>>{
    return this.http.get<Array<InstructionDTO>>('http://localhost:8080/Instruction/showInstruction?idTask='+idTask);
  }

  deleteInstruction(idInstruction: number){
    return this.http.delete('http://localhost:8080/Instruction/deleteInstruction?idInstruction='+idInstruction);
  }
}

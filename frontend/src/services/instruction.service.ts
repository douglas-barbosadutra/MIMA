import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { InstructionDTO } from 'src/dto/InstructionDTO';

@Injectable({
  providedIn: 'root'
})
export class InstructionService {

  constructor(private http: HttpClient) { }

  insertInstruction(instructionDTO: InstructionDTO){
    return this.http.post( 'http://localhost:8080/Instruction/insertInstruction', instructionDTO);
  }

  showInstruction(idTask: number){
    return this.http.get('http://localhost:8080/Instruction/showInstruction?idTask='+idTask);
  }

  deleteInstruction(idInstruction: number){
    return this.http.delete('http://localhost:8080/Instruction/deleteInstruction?idInstruction='+idInstruction);
  }
}

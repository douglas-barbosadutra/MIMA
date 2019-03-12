import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { InstructionDTO } from 'src/dto/InstructionDTO';
import { TaskDTO } from 'src/dto/TaskDTO';

@Injectable({
  providedIn: 'root'
})
export class InstructionService {

  constructor(private http: HttpClient) { }

  insertInstruction(instructionDTO: InstructionDTO){
    return this.http.post( 'http://localhost:8080/Instruction/insertInstruction', instructionDTO);
  }

  showInstruction(taskDTO: TaskDTO){
    return this.http.post('http://localhost:8080/Instruction/showInstruction', taskDTO);
  }

  deleteInstruction(instructionDTO: InstructionDTO){
    return this.http.post('http://localhost:8080/Instruction/deleteInstruction', instructionDTO);
  }
}

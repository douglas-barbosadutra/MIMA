import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { InstructionDTO } from 'src/dto/InstructionDTO';
import { Observable } from 'rxjs';
import { ParamDTO } from 'src/dto/ParamDTO';
import { UserDTO } from 'src/dto/UserDTO';

@Injectable({
  providedIn: 'root'
})
export class InstructionService {

  constructor(private http: HttpClient) { }

  auth() {
    var user = JSON.parse(localStorage.getItem("currentUser")) as UserDTO;
    if(user) {
        return "Bearer " + user.authorities;
    } else {
        return "";
    }
  }

  insertInstruction(instructionDTO: InstructionDTO): Observable<InstructionDTO>{
    return this.http.post<InstructionDTO>("http://localhost:8080/wbsMicroservice/api/instructions",instructionDTO, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  showInstruction(idTask: number): Observable<Array<InstructionDTO>>{
    return this.http.get<Array<InstructionDTO>>("http://localhost:8080/wbsMicroservice/api/instructionsByTask?id="+idTask, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  deleteInstruction(idInstruction: number){
    return this.http.delete("http://localhost:8080/wbsMicroservice/api/instructions/"+idInstruction, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }
}

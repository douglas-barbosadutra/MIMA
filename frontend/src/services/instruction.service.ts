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
        return "Bearer " + user.authorization;
    } else {
        return "";
    }
  }

  insertInstruction(instructionDTO: InstructionDTO){
    return this.http.post("http://localhost:8080/wbsMicroservice/api/instructions",instructionDTO, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  showInstruction(idTask: number){
    return this.http.get("http://localhost:8080/wbsMicroservice/api/instructions/"+idTask, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  deleteInstruction(idInstruction: number){
    return this.http.get("http://localhost:8080/wbsMicroservice/api/instructions/"+idInstruction, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }
}

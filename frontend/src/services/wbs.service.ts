import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { WBSDTO } from 'src/dto/WBSDTO';
import { Observable } from 'rxjs';
import { ParamDTO } from 'src/dto/ParamDTO';
import { UserDTO } from 'src/dto/UserDTO';

@Injectable({
  providedIn: 'root'
})
export class WbsService {

  constructor(private http: HttpClient) { }

  auth() {
    var user = JSON.parse(localStorage.getItem("currentUser")) as UserDTO;
    if(user) {
        return "Bearer " + user.authorization;
    } else {
        return "";
    }
  }

  showWbs(idUser: number) {
    return this.http.get("http://localhost:8080/wbsMicroservice/api/wbsByUser/"+idUser, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  insertWbs(wbsDTO: WBSDTO){
    return this.http.post('http://localhost:8080/wbsMicroservice/api/wbs', wbsDTO,{
      headers: {
        "Authorization": this.auth()
      }
    });
  }

  deleteWbs(idWbs: number){
    return this.http.delete('http://localhost:8080/wbsMicroservice/api/wbs/'+idWbs);
  }
}

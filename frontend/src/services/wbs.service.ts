import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { WBSDTO } from 'src/dto/WBSDTO';
import { Observable } from 'rxjs';
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

  showWbs(idUser: number): Observable<Array<WBSDTO>> {
    return this.http.get<Array<WBSDTO>>("http://localhost:8080/wbsMicroservice/api/wbsByUser/"+idUser, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  insertWbs(wbsDTO: WBSDTO): Observable<WBSDTO>{
    return this.http.post<WBSDTO>('http://localhost:8080/wbsMicroservice/api/wbs', wbsDTO,{
      headers: {
        "Authorization": this.auth()
      }
    });
  }

  deleteWbs(idWbs: number){
    return this.http.delete('http://localhost:8080/wbsMicroservice/api/wbs/'+ idWbs,{
      headers: {
        "Authorization": this.auth()
      }
    });
  }
}

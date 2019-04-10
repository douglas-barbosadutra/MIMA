import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TimeDTO } from 'src/dto/TimeDTO';
import { UserDTO } from 'src/dto/UserDTO';

@Injectable({
  providedIn: 'root'
})
export class ManufactoringService {

  constructor(private http: HttpClient) { }

  auth() {
    var user = JSON.parse(localStorage.getItem("currentUser")) as UserDTO;
    if(user) {
        return "Bearer " + user.authorities;
    } else {
        return "";
    }
  }

  showTime(idTask: number): Observable<Array<TimeDTO>>{
    return this.http.get<Array<TimeDTO>>("http://localhost:8080/wbsMicroservice/api/times/"+idTask, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

}

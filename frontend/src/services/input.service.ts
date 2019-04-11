import { Injectable } from '@angular/core';
import { UserDTO } from 'src/dto/UserDTO';
import { HttpClient } from '@angular/common/http';
import { InputDTO } from 'src/dto/InputDTO';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InputService {

  constructor(private http: HttpClient) { }

  auth() {
    var user = JSON.parse(localStorage.getItem("currentUser")) as UserDTO;
    if(user) {
        return "Bearer " + user.authorities;
    } else {
        return "";
    }
  }

  insertInput(inputDTO: InputDTO): Observable<InputDTO>{
    return this.http.post<InputDTO>('http://localhost:8080/machineMicroservice/api/inputs', inputDTO, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  deleteInput(idInput: number): Observable<Boolean>{
    return this.http.delete<Boolean>('http://localhost:8080/machineMicroservice/api/inputs/'+ idInput, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

}

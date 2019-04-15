import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { UserDTO } from '../dto/UserDTO';
import { Observable } from 'rxjs';
import { ParamDTO } from 'src/dto/ParamDTO';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  auth() {
    var user = JSON.parse(localStorage.getItem("currentUser")) as UserDTO;
    if(user) {
        return "Bearer " + user.authorities;
    } else {
        return "";
    }
  }

  insertEmployeeUser(userDTO: UserDTO): Observable<UserDTO> {
    return this.http.post<UserDTO>("http://localhost:8080/api/employees",userDTO, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  deleteEmployeeUser(idUser: number): Observable<Boolean>{
    return this.http.delete<Boolean>("http://localhost:8080/api/users/"+idUser, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  showUser(jwt: string): Observable<Array<UserDTO>>{
    return this.http.get<Array<UserDTO>>('http://localhost:8080/User/showUser?jwt='+jwt);
  }

  deleteUser(paramDTO: ParamDTO){
    return this.http.post('http://localhost:8080/User/deleteUser', paramDTO);
  }

  updateUser(userDTO: UserDTO): Observable<UserDTO> {
    return this.http.put<UserDTO>("http://localhost:8080/api/users",userDTO, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  findUser(jwt: string): Observable<UserDTO>{
    return this.http.get<UserDTO>('http://localhost:8080/User/findUser?jwt='+jwt);
  }

}

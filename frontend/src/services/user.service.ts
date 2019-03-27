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

  insertUser(paramDTO: ParamDTO): Observable<UserDTO> {
    return this.http.post<UserDTO>( 'http://localhost:8080/User/insertUser', paramDTO);
  }

  showUser(jwt: string): Observable<Array<UserDTO>>{
    return this.http.get<Array<UserDTO>>('http://localhost:8080/User/showUser?jwt='+jwt);
  }

  deleteUser(paramDTO: ParamDTO){
    return this.http.post('http://localhost:8080/User/deleteUser', paramDTO);
  }

  updateUser(paramDTO: ParamDTO): Observable<UserDTO> {
    return this.http.put<UserDTO>( 'http://localhost:8080/User/updateUser', paramDTO);
  }

  findUser(jwt: string): Observable<UserDTO>{
    return this.http.get<UserDTO>('http://localhost:8080/User/findUser?jwt='+jwt);
  }

}

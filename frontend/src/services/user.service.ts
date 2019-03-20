import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { UserDTO } from '../dto/UserDTO';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  insertUser(userDTO: UserDTO): Observable<UserDTO> {
    return this.http.post<UserDTO>( 'http://localhost:8080/User/insertUser', userDTO);
  }

  showUser(): Observable<Array<UserDTO>>{
    return this.http.get<Array<UserDTO>>('http://localhost:8080/User/showUser');
  }

  deleteUser(idUser: number){
    return this.http.delete('http://localhost:8080/User/deleteUser?idUser=' + idUser);
  }

  updateUser(userDTO: UserDTO): Observable<UserDTO> {
    return this.http.put<UserDTO>( 'http://localhost:8080/User/updateUser', userDTO);
  }

  findUser(idUser: number): Observable<UserDTO>{
    return this.http.get<UserDTO>('http://localhost:8080/User/findUser?idUser='+idUser);
  }

}

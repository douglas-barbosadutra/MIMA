import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { UserDTO } from '../dto/UserDTO';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  insertUser(userDTO: UserDTO) {
    return this.http.post( 'http://localhost:8080/User/insertUser', userDTO);
  }

  showUser(){
    return this.http.get('http://localhost:8080/User/showUser');
  }

  deleteUser(idUser: number){
    return this.http.delete('http://localhost:8080/User/deleteUser?idUser=' + idUser);
  }

  updateUser(userDTO: UserDTO) {
    return this.http.put( 'http://localhost:8080/User/updatetUser', userDTO);
  }

  findUser(idUser: number){
    return this.http.get('http://localhost:8080/User/findUser?idUser='+idUser);
  }





}

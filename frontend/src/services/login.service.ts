import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, of, } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { UserDTO } from '../dto/UserDTO';
import { LoginDTO } from 'src/dto/LoginDTO';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  //private logindto: LoginDTO;
  constructor(private http: HttpClient) { }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.log(result);
      console.error(error);
      console.log('${operation} failed: ${error.message}');
      return of(result as T);
    };
  }

  login(loginDTO: LoginDTO){
    //const params = new HttpParams().set('username', username).set('password', password);
    //this.logindto = new LoginDTO(username, password);
    return this.http.post('http://localhost:8080/Login/login', loginDTO);
    //return this.http.post<UserDTO>('http://localhost:8080/Login/login', this.logindto).pipe(tap((response) => console.log(username), catchError(this.handleError("login error", {}))));
  }
}

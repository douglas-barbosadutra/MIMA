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

  login(logindto: LoginDTO): Observable<UserDTO>{
    return this.http.post<UserDTO>('http://localhost:8080/Login/login', logindto);
  }
}

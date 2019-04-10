import { Injectable } from '@angular/core';
import { HttpClient, HttpParams , HttpResponse, HttpClientModule} from '@angular/common/http';
import { Observable, of, } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { UserDTO } from '../dto/UserDTO';
import { LoginDTO } from 'src/dto/LoginDTO';
import { map } from 'rxjs/operators';
import { UserLoggedDTO } from 'src/dto/UserLoggedDTO';

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

  auth() {
    var user = JSON.parse(localStorage.getItem("currentUser")) as UserDTO;
    
    if(user) {
        return "Bearer " + user.authorities;
    } else {
        return "";
    }
  }

  login(logindto: LoginDTO){
    return this.http.post('http://localhost:8080/api/authenticate', logindto);
  }

  getUserLogged(username: string){
    return this.http.get('http://localhost:8080/api/users/'+username, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

}

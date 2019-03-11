import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders} from '@angular/common/http';
import { Observable, of, } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { MachineDTO } from '../dto/MachineDTO';
import { UserDTO } from '../dto/UserDTO';

@Injectable({
  providedIn: 'root'
})
export class MachineService {

  constructor(private http: HttpClient) { }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.log(result);
      console.error(error);
      console.log('${operation} failed: ${error.message}');
      return of(result as T);
    };
  }

  insertMachine(machineDTO: any){
    
    return this.http.post( 'http://localhost:8080/Machine/insertMachine', machineDTO);
    
  }

  showMachine(userDTO: UserDTO){
    
    return this.http.post('http://localhost:8080/Machine/showMachine', userDTO);

  }
}

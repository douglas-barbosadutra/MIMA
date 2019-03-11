import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, of, } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { MachineDTO } from '../dto/MachineDTO';

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

  insertMachine(machineDTO: MachineDTO): Observable<MachineDTO>{
    const params = new HttpParams().set('machinedto', JSON.stringify(machineDTO));
    return this.http.post<MachineDTO>('http://localhost:8080/Machine/insertMachine', params).pipe(tap((response) => console.log(machineDTO), catchError(this.handleError("login error", {}))));
  
  }
}

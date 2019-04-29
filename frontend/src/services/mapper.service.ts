import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserDTO } from 'src/dto/UserDTO';
<<<<<<< HEAD
=======
import { Observable } from 'rxjs';
import { MatrixDTO } from 'src/dto/MatrixDTO';
import { BlackBoxDTO } from 'src/dto/BlackBoxDTO';
>>>>>>> 0624d17a59184770e7a8c103b205d8af27bb26f9

@Injectable({
  providedIn: 'root'
})
export class MapperService {

  constructor(private http: HttpClient) { }

  auth() {
    var user = JSON.parse(localStorage.getItem("currentUser")) as UserDTO;
    if(user) {
        return "Bearer " + user.authorities;
    } else {
        return "";
    }
  }

<<<<<<< HEAD
=======
  insertMatrix(matrixDTO: MatrixDTO): Observable<MatrixDTO>{
    return this.http.post<MatrixDTO>('http://localhost:8080/wbsMicroservice/api/', matrixDTO, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  insertBlackBox(blackboxDTO: BlackBoxDTO[]){
    return this.http.post<BlackBoxDTO>('http://localhost:8080/wbsMicroservice/api/', blackboxDTO, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  
>>>>>>> 0624d17a59184770e7a8c103b205d8af27bb26f9
}

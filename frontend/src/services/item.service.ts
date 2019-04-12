import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ItemDTO } from 'src/dto/ItemDTO';
import { Observable} from 'rxjs';
import { InputDTO } from 'src/dto/InputDTO';
import { ParamDTO } from 'src/dto/ParamDTO';
import { UserDTO } from 'src/dto/UserDTO';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  constructor(private http: HttpClient) { }

  auth() {
    var user = JSON.parse(localStorage.getItem("currentUser")) as UserDTO;
    if(user) {
        return "Bearer " + user.authorities;
    } else {
        return "";
    }
  }

  insertItem(itemDTO: ItemDTO): Observable<ItemDTO>{
    return this.http.post<ItemDTO>('http://localhost:8080/wbsMicroservice/api/items', itemDTO, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  findOne(id: number): Observable<ItemDTO>{
    return this.http.get<ItemDTO>('http://localhost:8080/wbsMicroservice/api/findOne?id='+id, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  deleteItem(idItem: number){
    return this.http.delete('http://localhost:8080/wbsMicroservice/api/items/' + idItem, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  showItemTree(idWBS: number): Observable<ItemDTO>{
    console.log(this.auth());
    return this.http.get<ItemDTO>('http://localhost:8080/wbsMicroservice/api/itemGetRoot/' + idWBS, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  showItem(): Observable<Array<ItemDTO>>{
    return this.http.get<Array<ItemDTO>>('http://localhost:8080/wbsMicroservice/api/items', {
      headers: {
          "Authorization": this.auth()
      }
    });
  }
}

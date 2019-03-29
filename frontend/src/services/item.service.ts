import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ItemDTO } from 'src/dto/ItemDTO';
import { Observable} from 'rxjs';
import { InputDTO } from 'src/dto/InputDTO';
import { ParamDTO } from 'src/dto/ParamDTO';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  constructor(private http: HttpClient) { }

  insertItem(param: ParamDTO): Observable<ItemDTO>{
    return this.http.post<ItemDTO>('http://localhost:8083/Item/addNode', param);
  }

  insertInput(inputDTO: InputDTO){
    return this.http.post('http://localhost:8083/Item/insertInput', inputDTO);
  }

  deleteItem(jwt: string, idItem: number){
    return this.http.delete('http://localhost:8083/Item/removeNode?jwt='+jwt+'&idItem=' + idItem);
  }

  showItemTree(jwt: string, idWBS: number): Observable<ItemDTO>{
    return this.http.get<ItemDTO>('http://localhost:8083/Item/showNodes?jwt='+jwt+'&idWBS=' + idWBS);
  }

  showItem(): Observable<Array<ItemDTO>>{
    return this.http.get<Array<ItemDTO>>('http://localhost:8083/Item/showItem');
  }
}

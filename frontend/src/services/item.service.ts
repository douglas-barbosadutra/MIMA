import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ItemDTO } from 'src/dto/ItemDTO';
import { WBSDTO } from 'src/dto/WBSDTO';
import { Observable} from 'rxjs';
import { InputDTO } from 'src/dto/InputDTO';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  constructor(private http: HttpClient) { }

  insertItem(itemDto: ItemDTO): Observable<ItemDTO>{
    return this.http.post<ItemDTO>('http://localhost:8080/Item/addNode', itemDto);
  }

  insertInput(inputDTO: InputDTO){
    return this.http.post('http://localhost:8080/Item/insertInput', inputDTO);
  }

  deleteItem(idItem: number){
    return this.http.delete('http://localhost:8080/Item/removeNode?idItem=' + idItem);
  }

  showItemTree(wbsDTO: WBSDTO): Observable<ItemDTO>{
    return this.http.post<ItemDTO>('http://localhost:8080/Item/showNodes', wbsDTO);
  }

  showItem(): Observable<Array<ItemDTO>>{
    return this.http.get<Array<ItemDTO>>('http://localhost:8080/Item/showItem');
  }
}

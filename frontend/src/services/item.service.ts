import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ItemDTO } from 'src/dto/ItemDTO';
import { WBSDTO } from 'src/dto/WBSDTO';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  constructor(private http: HttpClient) { }

  insertItem(itemDto: ItemDTO){
    return this.http.post('http://localhost:8080/Item/addNode', itemDto);
  }

  deleteItem(itemDto: ItemDTO){
    return this.http.post('http://localhost:8080/Item/deleteNode', itemDto);
  }

  showItemTree(wbsDTO: WBSDTO){
    return this.http.post('http://localhost:8080/Item/showNodes', wbsDTO);
  }
}

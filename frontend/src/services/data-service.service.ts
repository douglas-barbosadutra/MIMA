import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { TaskScheduledDTO } from 'src/dto/TaskScheduledDTO';


@Injectable({
  providedIn: 'root'
})
export class DataServiceService {

  private dataSourceChildren = new BehaviorSubject<Array<TaskScheduledDTO>>([]);
  currentDataChildren = this.dataSourceChildren.asObservable();

  private dataSourceFather = new BehaviorSubject<Array<TaskScheduledDTO>>([]);
  currentDataFather = this.dataSourceFather.asObservable();

  constructor() { }

  public sendTaskScheduledChildren(taskScheduledList: Array<TaskScheduledDTO>){
    return this.dataSourceChildren.next(taskScheduledList);
  }

  public sendTaskScheduledFathers(taskScheduledList: Array<TaskScheduledDTO>){
    return this.dataSourceFather.next(taskScheduledList);
  }
  
}

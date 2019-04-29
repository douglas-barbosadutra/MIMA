import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { TaskScheduledDTO } from 'src/dto/TaskScheduledDTO';


@Injectable({
  providedIn: 'root'
})
export class DataServiceService {

  private dataSourceList = new BehaviorSubject<Array<TaskScheduledDTO>>([]);
  currentDataList = this.dataSourceList.asObservable();

  private dataSourceListToUpdate = new BehaviorSubject<Array<TaskScheduledDTO>>([]);
  currentDataListToUpdate = this.dataSourceListToUpdate.asObservable();

  constructor() { }

  public sendTaskScheduledList(taskScheduledList: Array<TaskScheduledDTO>){
    return this.dataSourceList.next(taskScheduledList);
  }

  public sendTaskScheduledToUpdate(taskScheduledList: Array<TaskScheduledDTO>){
    return this.dataSourceListToUpdate.next(taskScheduledList);
  }
  
}

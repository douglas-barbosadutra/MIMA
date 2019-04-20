import { Component, OnInit,ViewChild, ElementRef, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { ManufactoringService } from 'src/services/manufactoring.service';
import { TimeDTO } from 'src/dto/TimeDTO';
import { Subject } from 'rxjs';
declare var $;

@Component({
  selector: 'app-time-show',
  templateUrl: './time-show.component.html',
  styleUrls: ['./time-show.component.css']
})
export class TimeShowComponent implements OnInit,OnDestroy {

  public descriptionTask: string;
  timeList: TimeDTO[] = [];
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<TimeDTO> = new Subject();

  constructor(private router: Router, private manufactoringService: ManufactoringService) { }

  ngOnInit() {

    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 10
    };

    this.checkTask();
  }

  ngOnDestroy(): void {
    // Do not forget to unsubscribe the event
    this.dtTrigger.unsubscribe();
  }

  checkTask(){
    if(sessionStorage.getItem("idTask") == null){
      this.router.navigateByUrl("taskShow");
    }
    else{
      this.descriptionTask = sessionStorage.getItem("descriptionTask");
      this.timeShow();
    }
      
  }

  timeShow(){
    this.manufactoringService.showTime(parseInt(sessionStorage.getItem("idTask"))).subscribe((data: Array<TimeDTO>) =>{
      if(data != null){
        this.timeList = data;
        this.dtTrigger.next();
      }
    })
  }
}

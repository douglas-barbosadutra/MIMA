import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ManufactoringService } from 'src/services/manufactoring.service';
import { TaskDTO } from 'src/dto/TaskDTO';
import { TimeDTO } from 'src/dto/TimeDTO';

@Component({
  selector: 'app-time-show',
  templateUrl: './time-show.component.html',
  styleUrls: ['./time-show.component.css']
})
export class TimeShowComponent implements OnInit {

  private taskDTO: TaskDTO;
  private timeList: Array<TimeDTO>;

  constructor(private router: Router, private manufactoringService: ManufactoringService) { }

  ngOnInit() {

    this.checkMachine();
    this.timeShow();
  }

  checkMachine(){
    if(sessionStorage.getItem("idTask") == null){
      alert("Devi prima selezionare un task");
      this.router.navigateByUrl("taskShow");
    }
  }

  timeShow(){
    this.taskDTO = new TaskDTO(parseInt(sessionStorage.getItem("idTask")),"",0);
    this.manufactoringService.showTime(this.taskDTO).subscribe((data: any) =>{
      if(data != null){
        console.log(data);
        this.timeList = data;
      }
    })
  }


}

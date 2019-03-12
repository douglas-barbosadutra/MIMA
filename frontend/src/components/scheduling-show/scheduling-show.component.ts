import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { MachineDTO } from 'src/dto/MachineDTO';
import { SchedulingService } from 'src/services/scheduling.service';
import { SchedulingDTO } from 'src/dto/SchedulingDTO';

@Component({
  selector: 'app-scheduling-show',
  templateUrl: './scheduling-show.component.html',
  styleUrls: ['./scheduling-show.component.css']
})
export class SchedulingShowComponent implements OnInit {
  private machineDTO: MachineDTO;
  private schedulingDTO: SchedulingDTO;
  private schedulingList: Array<SchedulingDTO>;

  constructor(private router: Router, private schedulingSerivce: SchedulingService) { }

  ngOnInit() {
    this.checkMachine();
    this.schedulingShow();
  }

  checkMachine(){
    if(sessionStorage.getItem("idMachine") == null){
      alert("Devi prima selezionare un macchinario");
      this.router.navigateByUrl("homeUser");
    }
  }

  schedulingShow(){
      this.schedulingSerivce.showScheduling(parseInt(sessionStorage.getItem("idMachine"))).subscribe((data: any) =>{
      if(data != null)
        this.schedulingList = data;
    })
  }

  updateScheduling(idScheduling: number, nameScheduling: string){
    sessionStorage.setItem("idScheduling",JSON.stringify(idScheduling));
    sessionStorage.setItem("nameScheduling",nameScheduling);
    this.router.navigateByUrl("schedulingUpdate");
  }

  deleteScheduling(idScheduling: number){
    this.schedulingSerivce.deleteScheduling(idScheduling).subscribe((data: any) =>{
      if(data)
        alert("Cancellazione effettuata");
      else
        alert("Cancellazione fallita");
      this.router.navigateByUrl("homeUser");
    })
  }
}

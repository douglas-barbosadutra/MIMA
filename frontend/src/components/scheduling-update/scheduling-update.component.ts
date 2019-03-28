import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SchedulingService } from 'src/services/scheduling.service';
import { NgForm } from '@angular/forms';
import { SchedulingDTO } from 'src/dto/SchedulingDTO';
import { ParamDTO } from 'src/dto/ParamDTO';

@Component({
  selector: 'app-scheduling-update',
  templateUrl: './scheduling-update.component.html',
  styleUrls: ['./scheduling-update.component.css']
})
export class SchedulingUpdateComponent implements OnInit {
  public schedulingDTO: SchedulingDTO;
  private paramDTO: ParamDTO;

  constructor(private router: Router, private schedulingSerivce: SchedulingService) { }

  ngOnInit() {
    this.schedulingDTO = new SchedulingDTO(parseInt(sessionStorage.getItem("idScheduling")),sessionStorage.getItem("nameScheduling"),"", "",parseInt(sessionStorage.getItem("idMachine")));
  }

  schedulingUpdate(f: NgForm){
    this.schedulingDTO.setStartDate(new Date(f.value.startDate).toLocaleString());
    this.schedulingDTO.setEndDate(new Date(f.value.endDate).toLocaleString());

    this.paramDTO = new ParamDTO(sessionStorage.getItem("userLogged"),this.schedulingDTO);

    this.schedulingSerivce.updateScheduling(this.paramDTO).subscribe((data: any) =>{
      if(data != null)
        alert("Aggiornamento effettuato");
      else
        alert("Aggiornamento fallito");
      this.router.navigateByUrl("homeUser");
    })
  }

}

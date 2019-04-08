import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { NgForm } from '@angular/forms';
import { SchedulingDTO } from 'src/dto/SchedulingDTO';
import { SchedulingService } from 'src/services/scheduling.service';
import { ParamDTO } from 'src/dto/ParamDTO';

@Component({
  selector: 'app-scheduling-insert',
  templateUrl: './scheduling-insert.component.html',
  styleUrls: ['./scheduling-insert.component.css']
})
export class SchedulingInsertComponent implements OnInit {

  public schedulingDTO: SchedulingDTO;
  private paramDTO: ParamDTO;

  constructor(private router: Router, private schedulingService: SchedulingService) { }

  ngOnInit() {
    if(sessionStorage.getItem("idMachine") == null){
      //alert("Devi prima selezionare un macchinario");
      this.router.navigateByUrl("machineShow");
    }
    else
      this.schedulingDTO = new SchedulingDTO(null,"",null,null,parseInt(sessionStorage.getItem("idMachine")));
      console.log(this.schedulingDTO);
    }

  schedulingInsert(f: NgForm){

    this.schedulingDTO.setStartDate(new Date(f.value.startDate).toLocaleString());
    this.schedulingDTO.setEndDate(new Date(f.value.endDate).toLocaleString());

    this.schedulingService.insertScheduling(this.schedulingDTO).subscribe((data: SchedulingDTO) =>{
      if(data != null)
        this.router.navigateByUrl("schedulingShow");
      else{
        alert("Inserimento fallito");
        this.router.navigateByUrl("homeUser");
      }
        
    })
  }


}

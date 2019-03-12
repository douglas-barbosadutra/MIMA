import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { NgForm } from '@angular/forms';
import { SchedulingDTO } from 'src/dto/SchedulingDTO';
import { SchedulingService } from 'src/services/scheduling.service';

@Component({
  selector: 'app-scheduling-insert',
  templateUrl: './scheduling-insert.component.html',
  styleUrls: ['./scheduling-insert.component.css']
})
export class SchedulingInsertComponent implements OnInit {

  public schedulingDTO: SchedulingDTO;
  private shcedulingInsert: SchedulingDTO;

  constructor(private router: Router, private schedulingService: SchedulingService) { }

  ngOnInit() {
    if(sessionStorage.getItem("idMachine") == null){
      alert("Devi prima selezionare un macchinario");
      this.router.navigateByUrl("machineShow");
    }
    this.schedulingDTO = new SchedulingDTO(0,"",new Date(),new Date(),parseInt(sessionStorage.getItem("idMachine")));
  }

  schedulingInsert(f: NgForm){
    console.log(this.schedulingDTO);
    this.schedulingService.insertScheduling(this.schedulingDTO).subscribe((data: any) =>{
      if(data != null)
        alert("Inserimento effettuato");
      else
        alert("Inserimento fallito");
      this.router.navigateByUrl("homeUser");
      
    })
  }


}

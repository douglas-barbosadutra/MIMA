import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { TaskDTO } from 'src/dto/TaskDTO';
import { InstructionService } from 'src/services/instruction.service';
import { InstructionDTO } from 'src/dto/InstructionDTO';

@Component({
  selector: 'app-instruction-show',
  templateUrl: './instruction-show.component.html',
  styleUrls: ['./instruction-show.component.css']
})
export class InstructionShowComponent implements OnInit {

  private instructionDTO: InstructionDTO;
  private instructionList: Array<InstructionDTO>;

  constructor(private router: Router, private instructionService: InstructionService) { }

  ngOnInit() {
    this.checkTask();
  }

  checkTask(){
    if(sessionStorage.getItem("idTask") == null){
      this.router.navigateByUrl("showTask");
    }
    else
      this.instructionShow();
  }

  instructionShow(){
    this.instructionService.showInstruction(parseInt(sessionStorage.getItem("idTask"))).subscribe((data: any) =>{
    if(data != null)
      this.instructionList = data;
    })
  }

  instructionDelete(idInstruction: number){
    this.instructionService.deleteInstruction(idInstruction).subscribe((data: any) =>{
      if(data)
        alert("Cancellazione effettuata");
      else
        alert("Cancellazione fallita");
      this.router.navigateByUrl("homeUser");
    })
  }

}

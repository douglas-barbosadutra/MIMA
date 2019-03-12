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

  private taskDTO: TaskDTO;
  private instructionDTO: InstructionDTO;
  private instructionList: Array<InstructionDTO>;

  constructor(private router: Router, private instructionService: InstructionService) { }

  ngOnInit() {
    this.checkTask();
    this.instructionShow();
  }

  checkTask(){
    if(sessionStorage.getItem("idTask") == null){
      alert("Devi prima selezionare un task");
      this.router.navigateByUrl("showTask");
    }
  }

  instructionShow(){
    this.taskDTO = new TaskDTO(parseInt(sessionStorage.getItem("idTask")),"",0);
    this.instructionService.showInstruction(this.taskDTO).subscribe((data: any) =>{
      if(data != null)
        this.instructionList = data;
    })
  }

  instructionDelete(idInstruction: number){
    this.instructionDTO = new InstructionDTO(idInstruction,0,"","",0);
    this.instructionService.deleteInstruction(this.instructionDTO).subscribe((data: any) =>{
      if(data != null)
        alert("Cancellazione effettuata");
      else
        alert("Cancellazione fallita");
      this.router.navigateByUrl("homeUser");
    })
  }

}

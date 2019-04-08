import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { TaskDTO } from 'src/dto/TaskDTO';
import { InstructionService } from 'src/services/instruction.service';
import { InstructionDTO } from 'src/dto/InstructionDTO';
import { HttpResponse } from '@angular/common/http';

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

  checkTask() {
    if (sessionStorage.getItem("idTask") == null) {
      this.router.navigateByUrl("showTask");
    }
    else
      this.instructionShow();
  }

  instructionShow() {
    this.instructionService.showInstruction(parseInt(sessionStorage.getItem("idTask"))).subscribe((data: Array<InstructionDTO>) => {
      if (data != null)
        this.instructionList = data;
    })
  }

  instructionDelete(idInstruction: number) {
    this.instructionService.deleteInstruction(idInstruction).subscribe();
    location.reload(true);
  }

}

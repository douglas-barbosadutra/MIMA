import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { NgForm } from '@angular/forms';
import { InstructionDTO } from 'src/dto/InstructionDTO';
import { InstructionService } from 'src/services/instruction.service';
import { ParamDTO } from 'src/dto/ParamDTO';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-instruction-insert',
  templateUrl: './instruction-insert.component.html',
  styleUrls: ['./instruction-insert.component.css']
})
export class InstructionInsertComponent implements OnInit {

  public instructionDTO: InstructionDTO;

  constructor(private router: Router, private instructionService: InstructionService) { }

  ngOnInit() {
    if (sessionStorage.getItem("idTask") == null) {
      this.router.navigateByUrl("taskShow");
    }
    this.instructionDTO = new InstructionDTO(null, "", 0, "", parseInt(sessionStorage.getItem("idTask")));
  }

  insertInstruction(f: NgForm) {
    this.instructionService.insertInstruction(this.instructionDTO).subscribe((data: InstructionDTO) => {
      if (data != null)
        this.router.navigateByUrl("instructionShow");
      else{
        alert("Inserimento fallito");
        this.router.navigateByUrl("homeUser");
      }
       
    })

  }

}

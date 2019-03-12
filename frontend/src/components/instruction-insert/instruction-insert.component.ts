import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { NgForm } from '@angular/forms';
import { InstructionDTO } from 'src/dto/InstructionDTO';
import { InstructionService } from 'src/services/instruction.service';

@Component({
  selector: 'app-instruction-insert',
  templateUrl: './instruction-insert.component.html',
  styleUrls: ['./instruction-insert.component.css']
})
export class InstructionInsertComponent implements OnInit {
  
  private instructionDTO: InstructionDTO;

  constructor(private router:  Router, private instructionService: InstructionService) { }

  ngOnInit() {
    if(sessionStorage.getItem("idTask") == null){
      this.router.navigateByUrl("taskShow");
      alert("Devi prima selezionare un task");
    }
  }

  insertInstruction(f: NgForm){
    this.instructionDTO = new InstructionDTO(0,f.value.durata,f.value.codice,f.value.nome,parseInt(sessionStorage.getItem("idTask")));
    this.instructionService.insertInstruction(this.instructionDTO).subscribe((data: any) =>{

      if(data != null)
        alert("Inserimento effettuato");
      else
        alert("Inserimento fallito");
      this.router.navigateByUrl("homeUser");
    })

  }

}

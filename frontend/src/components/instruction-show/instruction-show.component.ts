import { Component, OnInit,ViewChild, ElementRef } from '@angular/core';
import { Router } from "@angular/router";
import { InstructionService } from 'src/services/instruction.service';
import { InstructionDTO } from 'src/dto/InstructionDTO';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
declare var $;

@Component({
  selector: 'app-instruction-show',
  templateUrl: './instruction-show.component.html',
  styleUrls: ['./instruction-show.component.css'],
  providers: [NgbModalConfig, NgbModal]
})
export class InstructionShowComponent implements OnInit {

  public instructionDTO: InstructionDTO;
  private instructionList: Array<InstructionDTO>;

  @ViewChild('dataTable') table: ElementRef;
  dataTable: any;

  constructor(private config: NgbModalConfig, private modalService: NgbModal, private router: Router, private instructionService: InstructionService) { 
    config.backdrop = 'static';
    config.keyboard = false;
  }

  ngOnInit() {
    this.checkTask();
  }

  checkTask() {
    if (sessionStorage.getItem("idTask") == null) {
      this.router.navigateByUrl("taskShow");
    }
    else{
      this.instructionDTO = new InstructionDTO(null,null,null,null,parseInt(sessionStorage.getItem("idTask")));
      this.instructionShow();
    }
      
  }

  instructionShow() {
    this.instructionService.showInstruction(parseInt(sessionStorage.getItem("idTask"))).subscribe((data: Array<InstructionDTO>) => {
      if (data != null)
        this.instructionList = data;
    })
    this.createDataTable();
  }

  createDataTable(){
    this.dataTable = $(this.table.nativeElement);
    this.dataTable.dataTable();
  }

  instructionDelete(idInstruction: number) {
    this.instructionService.deleteInstruction(idInstruction).subscribe((response: any) =>{
      location.reload(true);
    });
    
  }

  instructionInsert() {
    this.instructionService.insertInstruction(this.instructionDTO).subscribe((data: InstructionDTO) => {
      if (data != null)
        location.reload(true);       
    })
  }

  open(content) {
    this.modalService.open(content);
  }

}

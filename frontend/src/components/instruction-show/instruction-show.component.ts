import { Component, OnInit,ViewChild, ElementRef, OnDestroy } from '@angular/core';
import { Router } from "@angular/router";
import { InstructionService } from 'src/services/instruction.service';
import { InstructionDTO } from 'src/dto/InstructionDTO';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-instruction-show',
  templateUrl: './instruction-show.component.html',
  styleUrls: ['./instruction-show.component.css'],
  providers: [NgbModalConfig, NgbModal]
})
export class InstructionShowComponent implements OnInit,OnDestroy {

  public instructionDTO: InstructionDTO;
  public descriptionTask: string;
  instructionList: InstructionDTO[] = [];
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();

  constructor(private config: NgbModalConfig, private modalService: NgbModal, private router: Router, private instructionService: InstructionService) { 
    config.backdrop = 'static';
    config.keyboard = false;
  }

  ngOnInit() {

    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 10
    };

    this.checkTask();
  }

  checkTask() {
    if (sessionStorage.getItem("idTask") == null) {
      this.router.navigateByUrl("taskShow");
    }
    else{
      this.descriptionTask = sessionStorage.getItem("descriptionTask");
      this.instructionDTO = new InstructionDTO(null,null,null,null,parseInt(sessionStorage.getItem("idTask")));
      this.instructionShow();
    }
      
  }

  instructionShow() {
    this.instructionService.showInstruction(parseInt(sessionStorage.getItem("idTask"))).subscribe((data: Array<InstructionDTO>) => {
      if (data != null)
        this.instructionList = data;
        this.dtTrigger.next();
    })
  }

  ngOnDestroy(): void {
    // Do not forget to unsubscribe the event
    this.dtTrigger.unsubscribe();
  }

  instructionDelete(idInstruction: number) {
    if(confirm("Hai già cancellato tutte le entità associate a questa istruzione?"))
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

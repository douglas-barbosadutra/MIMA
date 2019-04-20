import { Component, OnInit,ViewChild, ElementRef } from '@angular/core';
import { Router } from "@angular/router";
import { SchedulingService } from 'src/services/scheduling.service';
import { SchedulingDTO } from 'src/dto/SchedulingDTO';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TaskDTO } from 'src/dto/TaskDTO';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-scheduling-show',
  templateUrl: './scheduling-show.component.html',
  styleUrls: ['./scheduling-show.component.css'],
  providers: [NgbModalConfig, NgbModal]
})
export class SchedulingShowComponent implements OnInit {
  public schedulingDTO: SchedulingDTO;
  public nameMachine: string;
  schedulingList: SchedulingDTO[] = [];
  public taskDTO: TaskDTO;
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<SchedulingDTO> = new Subject();

  constructor(private config: NgbModalConfig, private modalService: NgbModal, private router: Router, private schedulingSerivce: SchedulingService) { 
    config.backdrop = 'static';
    config.keyboard = false;
  }

  ngOnInit() {

    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 10
    };

    this.taskDTO = new TaskDTO(null,null,null);
    this.checkMachine();
  }

  checkMachine(){
    if(sessionStorage.getItem("idMachine") == null){
      //alert("Devi prima selezionare un macchinario");
      this.router.navigateByUrl("machineShow");
    }
    else{
      this.nameMachine = sessionStorage.getItem("nameMachine");
      this.schedulingDTO = new SchedulingDTO(null,null,null,null,parseInt(sessionStorage.getItem("idMachine")));
      this.schedulingShow();
    }
      
  }

  schedulingShow(){
      this.schedulingSerivce.showScheduling(parseInt(sessionStorage.getItem("idMachine"))).subscribe((data: Array<SchedulingDTO>) =>{
      if(data != null){
        this.schedulingList = data;
        this.dtTrigger.next();
      }
    });
  }

  updateScheduling(){
    this.schedulingDTO.setStart(new Date(this.schedulingDTO.getStart()).toLocaleString());
    this.schedulingDTO.setFinish(new Date(this.schedulingDTO.getFinish()).toLocaleString());
    
    this.schedulingSerivce.updateScheduling(this.schedulingDTO).subscribe((response: SchedulingDTO) =>{
      if(response != null)
        location.reload(true);
    })
  }

  updateModal(id: number, name: string,content){
    this.schedulingDTO.setId(id);
    this.schedulingDTO.setName(name);
    this.modalService.open(content);
  }

  deleteScheduling(idScheduling: number){
    if(confirm("Hai già cancellato tutte le entità associate a questo scheduling?"))
      this.schedulingSerivce.deleteScheduling(idScheduling).subscribe((data: any) =>{
        location.reload(true);
      });
  }

  insertScheduling(){
    this.schedulingDTO.setId(null);
    this.schedulingDTO.setStart(new Date(this.schedulingDTO.getStart()).toLocaleString());
    this.schedulingDTO.setFinish(new Date(this.schedulingDTO.getFinish()).toLocaleString());
    //console.log(this.schedulingDTO);
    this.schedulingSerivce.insertScheduling(this.schedulingDTO).subscribe((data: SchedulingDTO) =>{
      if(data != null)
        location.reload(true);

    })
  }

  showGraph(idScheduling: number, nameScheduling){
    sessionStorage.setItem("idScheduling",JSON.stringify(idScheduling));
    sessionStorage.setItem("nameScheduling",nameScheduling);
    this.router.navigateByUrl("/TaskScheduled");
  }

  open(content) {
    this.modalService.open(content);
  }
}

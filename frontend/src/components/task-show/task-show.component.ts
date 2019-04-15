import { Component, OnInit, ViewChild, ElementRef, OnDestroy } from '@angular/core';
import { Router } from "@angular/router";
import { TaskService } from 'src/services/task.service';
import { TaskDTO } from 'src/dto/TaskDTO';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Subject } from 'rxjs';
declare var $;

@Component({
  selector: 'app-task-show',
  templateUrl: './task-show.component.html',
  styleUrls: ['./task-show.component.css'],
  providers: [NgbModalConfig, NgbModal]
})
export class TaskShowComponent implements OnInit,OnDestroy {

  public taskDTO: TaskDTO;
  taskList: TaskDTO[] = [];
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<TaskDTO> = new Subject();

  constructor(private config: NgbModalConfig, private modalService: NgbModal, private router: Router, private taskService: TaskService) {
    config.backdrop = 'static';
    config.keyboard = false;
   }

  ngOnInit() {

    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 10
    };

    this.checkMachineSelected();
  }

  ngOnDestroy(): void {
    // Do not forget to unsubscribe the event
    this.dtTrigger.unsubscribe();
  }

  checkMachineSelected(){
    if(sessionStorage.getItem("idMachine") == null){
      this.router.navigateByUrl("machineShow");
    }
    else{
      this.taskDTO = new TaskDTO(null,null,parseInt(sessionStorage.getItem("idMachine")));
      this.taskShow();
    }
      
  }

  taskShow(){

    this.taskService.showTask(parseInt(sessionStorage.getItem("idMachine"))).subscribe((data: Array<TaskDTO>) =>{

      if(data != null){
        this.taskList = data;
        this.dtTrigger.next();
      }
    })
  }

  chooseTask(idTask: number){
    
    sessionStorage.setItem("idTask",JSON.stringify(idTask));
    alert("Task selezionato");
  }

  deleteTask(idTask: number){
   
    this.taskService.deleteTask(idTask).subscribe((data: any) =>{
        location.reload(true);
    })
  }

  insertTask(){
  
    this.taskService.insertTask(this.taskDTO).subscribe((data: TaskDTO) =>{
      if(data != null)
        location.reload(true);
    })
  }

  timeShow(idTask: number){
    localStorage.setItem("idTask",JSON.stringify(idTask));
    this.router.navigateByUrl("timeShow");
  }

  open(content) {
    this.modalService.open(content);
  }

}

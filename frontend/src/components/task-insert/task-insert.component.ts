import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { NgForm } from '@angular/forms';
import { TaskDTO } from 'src/dto/TaskDTO';
import { TaskService } from 'src/services/task.service';
import { ParamDTO } from 'src/dto/ParamDTO';

@Component({
  selector: 'app-task-insert',
  templateUrl: './task-insert.component.html',
  styleUrls: ['./task-insert.component.css']
})
export class TaskInsertComponent implements OnInit {

  public taskDTO: TaskDTO;
  private paramDTO: ParamDTO;

  constructor(private router: Router, private taskService: TaskService) { }

  ngOnInit() {

    if(sessionStorage.getItem("idMachine") == null){
      this.router.navigateByUrl("machineShow");
      //alert("Devi prima selezionare un macchinario");
    }
    else
      this.taskDTO = new TaskDTO(0,"",parseInt(sessionStorage.getItem("idMachine")));
  }

  insertTask(f: NgForm){

    this.paramDTO = new ParamDTO(sessionStorage.getItem("userLogged"),this.taskDTO);
  
    this.taskService.insertTask(this.paramDTO).subscribe((data: any) =>{

      if(data != null)
        alert("Inserimento effettuato");
      else
        alert("Inserimento fallito");

      this.router.navigateByUrl("homeUser");
    })
  }

}

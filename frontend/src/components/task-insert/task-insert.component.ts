import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { NgForm } from '@angular/forms';
import { TaskDTO } from 'src/dto/TaskDTO';
import { TaskService } from 'src/services/task.service';

@Component({
  selector: 'app-task-insert',
  templateUrl: './task-insert.component.html',
  styleUrls: ['./task-insert.component.css']
})
export class TaskInsertComponent implements OnInit {

  private taskDTO: TaskDTO;

  constructor(private router: Router, private taskService: TaskService) { }

  ngOnInit() {

    if(sessionStorage.getItem("idMachine") == null){
      alert("Devi prima selezionare un macchinario");
      this.router.navigateByUrl("machineShow");
    }
      
  }

  insertTask(f: NgForm){

    this.taskDTO = new TaskDTO(0,f.value.description,parseInt(sessionStorage.getItem("idMachine")));
    this.taskService.insertTask(this.taskDTO).subscribe((data: any) =>{

      if(data != null)
        alert("Inserimento effettuato");
      else
        alert("Inserimento fallito");

      this.router.navigateByUrl("homeUser");
    })
  }

}

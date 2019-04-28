import { Component, OnInit, OnDestroy } from '@angular/core';
import { TaskScheduledService } from 'src/services/task-scheduled.service';
import { TaskService } from 'src/services/task.service';
import { Router } from "@angular/router";
import { TaskScheduledDTO } from 'src/dto/TaskScheduledDTO';
import { TaskDTO } from 'src/dto/TaskDTO';
import { DataServiceService } from 'src/services/data-service.service';
import { Subject } from 'rxjs';
import { TaskScheduledRelationDTO } from 'src/dto/TaskScheduledRelationDTO';

declare var $: any;
declare var arbor: any;

@Component({
  selector: 'app-task-scheduled',
  templateUrl: './task-scheduled.component.html',
  styleUrls: ['./task-scheduled.component.css']
})

export class TaskScheduledComponent implements OnInit,OnDestroy {

  taskList: TaskDTO[] = [];
  public taskScheduledListToUpdateFather: Array<TaskScheduledDTO>;
  public taskScheduledListToUpdateChild: Array<TaskScheduledDTO>;
  public idTaskDeleted: number;
  task: TaskScheduledDTO;
  public idFather: number;
  public idChild: number;
  public taskSelected: number;
  private sys;
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<TaskDTO> = new Subject();

  constructor(private router: Router, private taskScheduledService: TaskScheduledService, private taskService: TaskService, private dataService: DataServiceService) { }

  ngOnDestroy(): void {
    // Do not forget to unsubscribe the event
    this.dtTrigger.unsubscribe();
  }

  ngOnInit() {

    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 10
    };

    this.getTaskScheduledList();
    this.getTaskList();

    var Renderer = function (canvas) {
      var canvas = $(canvas).get(0);
      var ctx = canvas.getContext("2d");
      var particleSystem;
      var that = {
        init: function (system) {
          particleSystem = system;
          particleSystem.screenSize(canvas.width, canvas.height);
          particleSystem.screenPadding(100);
          that.initMouseHandling()
        },
        redraw: function () {
          ctx.fillStyle = "white";
          ctx.fillRect(0, 0, canvas.width, canvas.height);
          particleSystem.eachEdge(function (edge, pt1, pt2) {
            ctx.strokeStyle = edge.data.linkcolor;
            ctx.lineWidth = 3;
            ctx.beginPath();
            ctx.moveTo(pt1.x, pt1.y);
            ctx.lineTo(pt2.x, pt2.y);
            ctx.stroke();
          });
          particleSystem.eachNode(function (node, pt) {
            ctx.beginPath();
            ctx.arc(pt.x, pt.y, 15, 0, 2 * Math.PI);
            ctx.fillStyle = node.data.nodecolor;
            ctx.fill();
            ctx.font = "18px Arial";
            ctx.fillStyle = "#000000";
            ctx.fillText(node.data.name, pt.x + 20, pt.y + 5);
          });
        },
        initMouseHandling: function () {
          var dragged = null;
          var handler = {
            clicked: function (e) {
              var pos = $(canvas).offset();
              var _mouseP = arbor.Point(e.pageX - pos.left, e.pageY - pos.top);
              dragged = particleSystem.nearest(_mouseP);

              $('#task-selected').val(dragged.node.name);

              if (dragged && dragged.node !== null) {
                dragged.node.fixed = true;
              }
              $(canvas).bind('mousemove', handler.dragged);
              $(window).bind('mouseup', handler.dropped);
              return false;
            },
            dragged: function (e) {
              var pos = $(canvas).offset();
              var s = arbor.Point(e.pageX - pos.left, e.pageY - pos.top);
              if (dragged && dragged.node !== null) {
                var p = particleSystem.fromScreen(s);
                dragged.node.p = p
              }
              return false;
            },
            dropped: function (e) {
              if (dragged === null || dragged.node === undefined) return;
              if (dragged.node !== null) dragged.node.fixed = false;
              dragged.node.tempMass = 1000;
              dragged = null;
              $(canvas).unbind('mousemove', handler.dragged);
              $(window).unbind('mouseup', handler.dropped);
              var _mouseP = null;
              return false;
            }
          };
          $(canvas).mousedown(handler.clicked);
        }
      }
      return that;
    }

    this.sys = arbor.ParticleSystem(700, 700, 0.5);
    this.sys.renderer = Renderer("#viewport");

  }

  getTaskScheduledList() {
    this.taskScheduledService.showTaskScheduled(parseInt(sessionStorage.getItem("idScheduling"))).subscribe((data: Array<TaskScheduledDTO>) => {
      if (data != null) {
        
        sessionStorage.setItem("taskScheduledListLength", JSON.stringify(data.length));
        sessionStorage.setItem("taskScheduledList", JSON.stringify(data));

        for (let task of data) {
          this.sys.addNode(task.id, { name: task.name, nodecolor: "#0000ff" });
        }

        this.createTable(data);
      }
    });
  }

  getTaskList() {
    this.taskService.showTask(parseInt(sessionStorage.getItem("idMachine"))).subscribe((data: Array<TaskDTO>) => {
      if (data != null) {
        this.taskList = data;
        this.dtTrigger.next();
      }
    });
  }

  createTable(taskScheduledList: Array<TaskScheduledDTO>) {

    for (let taskScheduled of taskScheduledList) {
      let taskScheduledRelationList: Array<TaskScheduledRelationDTO> = taskScheduled.taskScheduledRelationList;

      for(let taskScheduledRelation of taskScheduledRelationList)
        this.sys.addEdge(taskScheduledRelation.taskScheduledFirstId, taskScheduledRelation.taskScheduledSecondId, { linkcolor: "#808080" });
    }

  }

  chooseChild(idChild: number) {
    this.idChild = idChild;
  }

  chooseFather(idFather: number) {
    console.log(idFather);
    this.idFather = idFather;
  }

  insertTask(idTask: number, taskName: string) {
    this.task = new TaskScheduledDTO(null,taskName,null,parseInt(sessionStorage.getItem("idScheduling")),idTask,null);
    this.taskScheduledService.insertTaskScheduled(this.task).subscribe((data: any) => { 
      location.reload(true);
    });
  }

  delete(idTaskScheduled: number) {

    this.taskScheduledListToUpdateFather = new Array<TaskScheduledDTO>();
    this.taskScheduledListToUpdateChild = new Array<TaskScheduledDTO>();
    let taskScheduledList: TaskScheduledDTO[] = JSON.parse(sessionStorage.getItem("taskScheduledList"));

    for(let taskScheduled of taskScheduledList){
      let taskScheduledRelationList: TaskScheduledRelationDTO[] = taskScheduled.taskScheduledRelationList;

      for (let row of taskScheduledRelationList) {
        if (row.taskScheduledFirstId == idTaskScheduled) {
          if (row.taskScheduledSecondId == taskScheduled.id)
              this.taskScheduledListToUpdateFather.push(taskScheduled);
        }
        else if (row.taskScheduledSecondId == idTaskScheduled) {
          if (row.taskScheduledFirstId == taskScheduled.id)
              this.taskScheduledListToUpdateChild.push(taskScheduled);
        }
      }
    }

    this.dataService.sendTaskScheduledChildren(this.taskScheduledListToUpdateChild);
    this.dataService.sendTaskScheduledFathers(this.taskScheduledListToUpdateFather);
    this.taskScheduledService.deleteTaskScheduled(idTaskScheduled).subscribe((data: any) => { });
    console.log(this.taskScheduledListToUpdateChild);
    console.log(this.taskScheduledListToUpdateFather);
    this.router.navigateByUrl("/TaskScheduledDelete");
  }

  insertTaskScheduled() {
    let taskScheduledRelationDTO: TaskScheduledRelationDTO = new TaskScheduledRelationDTO(null,this.idFather,this.idChild);

    this.taskScheduledService.insertTaskScheduledRelation(taskScheduledRelationDTO).subscribe((response: TaskScheduledRelationDTO) =>{
      if(response != null)
        location.reload(true);
    })

  }
}

import { Component, OnInit } from '@angular/core';
import { TaskScheduledService } from 'src/services/task-scheduled.service';
import { TaskService } from 'src/services/task.service';
import { Router } from "@angular/router";
import { TaskScheduledDTO } from 'src/dto/TaskScheduledDTO';
import { TaskDTO } from 'src/dto/TaskDTO';
import { OperationSchedulingDTO } from 'src/dto/OperationSchedulingDTO';
import { DataServiceService } from 'src/services/data-service.service';
import { NgForm } from '@angular/forms';

declare var $: any;
declare var arbor: any;

@Component({
  selector: 'app-task-scheduled',
  templateUrl: './task-scheduled.component.html',
  styleUrls: ['./task-scheduled.component.css']
})

export class TaskScheduledComponent implements OnInit {
  public taskScheduledList: Array<TaskScheduledDTO>;
  public taskList: Array<TaskDTO>;
  public table: Array<OperationSchedulingDTO>;
  public taskScheduledListToUpdateFather: Array<TaskScheduledDTO>;
  public taskScheduledListToUpdateChild: Array<TaskScheduledDTO>;
  public idTaskDeleted: number;
  osDTO: OperationSchedulingDTO;
  task: TaskScheduledDTO;
  public idFather: number;
  public idChild: number;
  public taskSelected: number;
  private sys;

  constructor(private router: Router, private taskScheduledService: TaskScheduledService, private taskService: TaskService, private dataService: DataServiceService) {
    this.router.onSameUrlNavigation = 'reload';
   }

  ngOnInit() {
    this.getTaskScheduledList();
    this.getTaskList();
    this.createTable();
    
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

                                      //console.log(dragged.node);
                                      //console.log(dragged.node.name);
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
    this.taskScheduledService.showTaskScheduled(parseInt(sessionStorage.getItem("idScheduling"))).subscribe((data) => {
      if (data != null) {
        this.taskScheduledList = new Array();
        this.taskScheduledList = data;
        sessionStorage.setItem("taskScheduledListLength", JSON.stringify(this.taskScheduledList.length));
        
        for(let task of this.taskScheduledList){
          //console.log(task);
          this.sys.addNode(task.id, {name: task.name, nodecolor: "#0000ff"});
        }

      }
    });
  }

  getTaskList() {
    this.taskService.showTask(parseInt(sessionStorage.getItem("idMachine"))).subscribe((data: any) => {
      if (data != null) {
        this.taskList = data;
      }
    });
  }

  createTable() {
    this.table = new Array<OperationSchedulingDTO>();
    this.taskScheduledService.showOperationScheduling(parseInt(sessionStorage.getItem("idScheduling"))).subscribe((data) => {
      if (data != null) {
        this.table = data;

        for(let relazione of this.table){
          //console.log(relazione);
          this.sys.addEdge(relazione.idFather, relazione.idChild, {linkcolor: "#808080"});
        }

      }
    });
  }

  chooseChild(idChild: number) {
    this.idChild = idChild;
  }

  chooseFather(idFather: number) {
    console.log(idFather);
    this.idFather = idFather;
  }

  insertTask(idTask: number, taskName: string) {
    if (parseInt(sessionStorage.getItem("taskScheduledListLength")) == 0) {
      this.task = new TaskScheduledDTO(0, idTask, false, taskName, parseInt(sessionStorage.getItem("idScheduling")), null);
      this.createTaskScheduled();
      window.location.reload();
    }
    else {
      this.idChild = idTask;
      this.osDTO = new OperationSchedulingDTO(this.idFather, this.idChild, idTask, parseInt(sessionStorage.getItem("idScheduling")));
    }
  }

  createOperationScheduling() {
    this.taskScheduledService.insertOperationScheduling(this.osDTO).subscribe((data: any) => { });
    this.getTaskScheduledList();
    this.getTaskList();
    this.createTable();
  }

  createTaskScheduled() {
    this.taskScheduledService.insertTaskScheduled(this.task).subscribe((data: any) => { });
    this.getTaskScheduledList();
    this.getTaskList();
    this.createTable();
  }

  delete(idTaskScheduled: number) {

    this.taskScheduledListToUpdateFather = new Array<TaskScheduledDTO>();
    this.taskScheduledListToUpdateChild = new Array<TaskScheduledDTO>();

    for (let row of this.table) {
      if (row.idFather == idTaskScheduled) {
        for (let t of this.taskScheduledList) {
          if (row.idChild == t.id)
            this.taskScheduledListToUpdateFather.push(t);
        }
      }
      else if (row.idChild == idTaskScheduled) {
        for (let t of this.taskScheduledList) {
          if (row.idFather == t.id)
            this.taskScheduledListToUpdateChild.push(t);
        }
      }
    }
    this.dataService.sendTaskScheduledChildren(this.taskScheduledListToUpdateChild);
    this.dataService.sendTaskScheduledFathers(this.taskScheduledListToUpdateFather);
    this.taskScheduledService.deleteTaskScheduled(idTaskScheduled).subscribe((data: any) => { });
    this.router.navigateByUrl("/TaskScheduledDelete");
  }


  insertTaskScheduled() {
    if ((this.idFather != undefined) && (this.idFather != null) && (this.idChild != undefined) && (this.idChild != null)) {
      if ((this.osDTO != undefined) && (this.osDTO != null)) {
        this.osDTO.idFather = this.idFather;
        this.osDTO.idChild = this.idChild;
        this.osDTO.idScheduling = parseInt(sessionStorage.getItem("idScheduling"));
        this.osDTO.idTask = this.idChild;
        this.taskScheduledService.insertOperationScheduling(this.osDTO).subscribe((data: any) => { });
      }
      else {
        this.osDTO = new OperationSchedulingDTO(this.idFather, this.idChild, 0, parseInt(sessionStorage.getItem("idScheduling")));
        this.taskScheduledService.insertOperationScheduling(this.osDTO).subscribe((data: any) => { });
      }
    }
    window.location.reload();
  }
}

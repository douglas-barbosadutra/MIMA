import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";

@Component({
  selector: 'app-scheduling-insert',
  templateUrl: './scheduling-insert.component.html',
  styleUrls: ['./scheduling-insert.component.css']
})
export class SchedulingInsertComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

}

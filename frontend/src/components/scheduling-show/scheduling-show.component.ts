import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";

@Component({
  selector: 'app-scheduling-show',
  templateUrl: './scheduling-show.component.html',
  styleUrls: ['./scheduling-show.component.css']
})
export class SchedulingShowComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

}

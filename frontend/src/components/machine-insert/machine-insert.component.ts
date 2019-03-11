import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";

@Component({
  selector: 'app-machine-insert',
  templateUrl: './machine-insert.component.html',
  styleUrls: ['./machine-insert.component.css']
})
export class MachineInsertComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

}

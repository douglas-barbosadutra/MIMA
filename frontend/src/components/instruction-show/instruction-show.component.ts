import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";

@Component({
  selector: 'app-instruction-show',
  templateUrl: './instruction-show.component.html',
  styleUrls: ['./instruction-show.component.css']
})
export class InstructionShowComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

}

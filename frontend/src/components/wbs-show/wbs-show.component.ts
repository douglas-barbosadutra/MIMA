import { Component, OnInit } from '@angular/core';
import { WBSDTO } from 'src/dto/WBSDTO';

@Component({
  selector: 'app-wbs-show',
  templateUrl: './wbs-show.component.html',
  styleUrls: ['./wbs-show.component.css']
})
export class WbsShowComponent implements OnInit {

  private wbsList : Array<WBSDTO>;
  
  constructor() { }

  ngOnInit() {
  }

}

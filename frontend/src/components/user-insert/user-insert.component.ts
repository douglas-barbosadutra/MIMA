import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-user-insert',
  templateUrl: './user-insert.component.html',
  styleUrls: ['./user-insert.component.css']
})
export class UserInsertComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  insert(f:NgForm): void{
    console.log(f.value);
  }

}

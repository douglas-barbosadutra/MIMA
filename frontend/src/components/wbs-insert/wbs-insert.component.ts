import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { NgForm } from "@angular/forms";
import { WBSDTO } from 'src/dto/WBSDTO';
import { WbsService } from "src/services/wbs.service";
import { ParamDTO } from 'src/dto/ParamDTO';
import { HttpResponse } from '@angular/common/http';
import { UserDTO } from 'src/dto/UserDTO';

@Component({
  selector: 'app-wbs-insert',
  templateUrl: './wbs-insert.component.html',
  styleUrls: ['./wbs-insert.component.css']
})
export class WbsInsertComponent implements OnInit {
  public wbsdto: WBSDTO;
  private userDTO: UserDTO;

  constructor(private router: Router, private wbsService: WbsService) { }

  ngOnInit() {
    this.userDTO = JSON.parse(localStorage.getItem("currentUserData"));
    this.wbsdto = new WBSDTO(null, "", this.userDTO.id);
  }

  insertWbs(f: NgForm) {

    this.wbsService.insertWbs(this.wbsdto).subscribe((data: WBSDTO) => {
      if (data != null)
        this.router.navigateByUrl("wbsShow");
      else{
        alert("Inserimento fallito");
        this.router.navigateByUrl("/homeUser");
      }
        
    });
    
  }
}

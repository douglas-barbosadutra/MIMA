import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { NgForm } from "@angular/forms";
import { WBSDTO } from 'src/dto/WBSDTO';
import { WbsService } from "src/services/wbs.service";
import { ParamDTO } from 'src/dto/ParamDTO';

@Component({
  selector: 'app-wbs-insert',
  templateUrl: './wbs-insert.component.html',
  styleUrls: ['./wbs-insert.component.css']
})
export class WbsInsertComponent implements OnInit {
  public wbsdto: WBSDTO;
  private paramDTO: ParamDTO;

  constructor(private router: Router, private wbsService: WbsService) { }

  ngOnInit() {
    this.wbsdto = new WBSDTO(0, "", 0);
  }

  insertWbs(f: NgForm) {
    this.paramDTO = new ParamDTO(sessionStorage.getItem("userLogged"), this.wbsdto);
    this.wbsService.insertWbs(this.paramDTO).subscribe((data: any) => {
      if (data != null)
        alert("Inserimento effettuato");
      else
        alert("Inserimento fallito");
    });
    this.router.navigateByUrl("/homeUser");
  }
}

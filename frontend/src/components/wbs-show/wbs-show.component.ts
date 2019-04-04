import { Component, OnInit } from '@angular/core';
import { WBSDTO } from 'src/dto/WBSDTO';
import { WbsService } from 'src/services/wbs.service';
import { Router } from '@angular/router';
import { UserDTO } from 'src/dto/UserDTO';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-wbs-show',
  templateUrl: './wbs-show.component.html',
  styleUrls: ['./wbs-show.component.css']
})
export class WbsShowComponent implements OnInit {

  private wbsList: Array<WBSDTO>;
  private userDTO: UserDTO;
  private wbsDTO: WBSDTO;

  constructor(private wbsService: WbsService, private router: Router) { }

  ngOnInit() {
    this.wbsService.showWbs(1).subscribe((data: HttpResponse<any>) => {
      if (data != null) {
        this.wbsList = data.body;
      }
    });
  }

  chooseWbs(idWbs: number) {
    sessionStorage.setItem("idWbs", JSON.stringify(idWbs));
    this.router.navigateByUrl("/itemShow");
  }

  deleteWbs(idWbs: number) {
    this.wbsService.deleteWbs(idWbs).subscribe();
    this.router.navigateByUrl("homeUser");
  }
}

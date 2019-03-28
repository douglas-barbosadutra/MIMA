import { Component, OnInit } from '@angular/core';
import { WBSDTO } from 'src/dto/WBSDTO';
import { WbsService } from 'src/services/wbs.service';
import { Router } from '@angular/router';
import { UserDTO } from 'src/dto/UserDTO';

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
    this.wbsService.showWbs(sessionStorage.getItem("userLogged")).subscribe((data: any) => {
      if (data != null) {
        this.wbsList = data;
      }
    });
  }

  chooseWbs(idWbs: number) {
    sessionStorage.setItem("idWbs", JSON.stringify(idWbs));
    this.router.navigateByUrl("/itemShow");
  }

  deleteWbs(idWbs: number) {
    this.wbsService.deleteWbs(sessionStorage.getItem("userLogged"), idWbs).subscribe((data: any) => {
      this.router.navigateByUrl("homeUser");
    });
  }
}

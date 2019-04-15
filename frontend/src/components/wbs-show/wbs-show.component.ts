import { Component, OnInit, ViewChild, ElementRef, OnDestroy } from '@angular/core';
import { WBSDTO } from 'src/dto/WBSDTO';
import { WbsService } from 'src/services/wbs.service';
import { Router } from '@angular/router';
import { UserDTO } from 'src/dto/UserDTO';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Subject } from 'rxjs';
declare var $;

@Component({
  selector: 'app-wbs-show',
  templateUrl: './wbs-show.component.html',
  styleUrls: ['./wbs-show.component.css'],
  providers: [NgbModalConfig, NgbModal]
})
export class WbsShowComponent implements OnInit,OnDestroy {

  wbsList: WBSDTO[] = [];
  private userDTO: UserDTO;
  public wbsDTO: WBSDTO;
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<WBSDTO> = new Subject();

  constructor(private config: NgbModalConfig, private modalService: NgbModal, private wbsService: WbsService, private router: Router) {
    config.backdrop = 'static';
    config.keyboard = false;
  }

  ngOnInit() {

    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 10
    };

    this.userDTO = JSON.parse(localStorage.getItem("currentUserData"));
    this.wbsDTO = new WBSDTO(null,null,this.userDTO.id);
    this.wbsService.showWbs(this.userDTO.id).subscribe((data: Array<WBSDTO>) => {
      if (data != null) {
        this.wbsList = data;
        this.dtTrigger.next();
      }
    });
  }

  ngOnDestroy(): void {
    // Do not forget to unsubscribe the event
    this.dtTrigger.unsubscribe();
  }

  chooseWbs(idWbs: number) {
    sessionStorage.setItem("idWbs", JSON.stringify(idWbs));
    this.router.navigateByUrl("/itemShow");
  }

  deleteWbs(idWbs: number) {
    this.wbsService.deleteWbs(idWbs).subscribe((response: any) =>{
      location.reload(true);
    });
    
  }

  insertWbs() {

    this.wbsService.insertWbs(this.wbsDTO).subscribe((data: WBSDTO) => {
      if (data != null)
        location.reload(true);        
    });
    
  }

  open(content) {
    this.modalService.open(content);
  }
}

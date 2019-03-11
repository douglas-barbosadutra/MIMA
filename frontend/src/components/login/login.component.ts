import { Component, OnInit } from '@angular/core';
import { NgForm } from "@angular/forms";
import { Router } from "@angular/router";
import {LoginService} from "src/services/login.service";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private utenteLocale: string;
  constructor(private loginService: LoginService, private router:  Router) { }

  ngOnInit(){
    console.log("eccolo");

  }

  login(f:NgForm): void{
    this.loginService.login(f.value.username, f.value.password).subscribe((response) => {

      if(response != null){
        this.utenteLocale = response.name;
        sessionStorage.setItem("user", JSON.stringify(this.utenteLocale));

        if(response.rank == 0)
          this.router.navigateByUrl("/homeUser");

        else if(response.rank == 1)
          this.router.navigateByUrl("/homeAdmin");

        else
          this.router.navigateByUrl("/homeEmployee");
            
      }
      else{
        alert("user o pass errati");
      }
    });
  }
}

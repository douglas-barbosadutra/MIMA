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
  constructor(private loginService: LoginService) { }

  ngOnInit(){
    console.log("eccolo");

  }

  login(f:NgForm): void{
    this.loginService.login(f.value.username, f.value.password).subscribe((response) => {
      if(response != null){
        console.log(response);
      }
      else{
        console.log("user o pass errati");
      }
    });
  }
}

import { Component, OnInit } from '@angular/core';
import { NgForm } from "@angular/forms";
import { Router } from "@angular/router";
import {LoginService} from "src/services/login.service";
import { UserDTO } from 'src/dto/UserDTO';
import { LoginDTO } from 'src/dto/LoginDTO';
import { UserLoggedDTO } from 'src/dto/UserLoggedDTO';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { UserService } from 'src/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private idUtenteLocale: number;
  public loginDTO: LoginDTO;

  constructor(private loginService: LoginService, private router:  Router, private http: HttpClient) { }

  ngOnInit(){
    this.loginDTO = new LoginDTO("","");
  }

  login(f:NgForm): void{

    this.loginService.login(this.loginDTO).subscribe((response: any) => {
      console.log(response);
      localStorage.setItem("currentUser", JSON.stringify({ "authorities": response.id_token }));

      this.loginService.getUserLogged(this.loginDTO.username).subscribe((response: UserDTO) => {
        console.log(response);
        localStorage.setItem("currentUserData", JSON.stringify(response));

        if(response.authorities.includes("ROLE_EMPLOYEE"))
          this.router.navigateByUrl("homeEmployee");
        else
        this.router.navigateByUrl("homeUser");
      })
    });
    
  }

}

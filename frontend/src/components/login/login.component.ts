import { Component, OnInit } from '@angular/core';
import { NgForm } from "@angular/forms";
import { Router } from "@angular/router";
import {LoginService} from "src/services/login.service";
import { UserDTO } from 'src/dto/UserDTO';
import { LoginDTO } from 'src/dto/LoginDTO';
import { UserLoggedDTO } from 'src/dto/UserLoggedDTO';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

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

  /*login(f:NgForm): void{
    this.loginService.login(this.loginDTO).subscribe((data: UserLoggedDTO) => {

      if(data != null){
        console.log(data.jwt)

        sessionStorage.setItem("userLogged",data.jwt);

        if(data.rank == 0)
          this.router.navigateByUrl("/homeUser");

        else if(data.rank == 1)
          this.router.navigateByUrl("/homeAdmin");

        else
          this.router.navigateByUrl("/homeEmployee");
            
      }
      else{
        alert("user o pass errati");
      }
    });
  }*/

  public login() {
    console.log(this.loginDTO);
    return this.http.post("http://localhost:8080/api/authenticate", {
        username: this.loginDTO.username,
        password: this.loginDTO.password
    }).pipe(map((response: any) => {
        //console.log(response);
        //localStorage.setItem("currentUser", JSON.stringify({ "authorization": response.id_token }));
        localStorage.setItem("userLogged", JSON.stringify(response.id_token));
        console.log(response);
    })).subscribe(() =>{
      this.router.navigateByUrl("homeUser");
    });
}
}

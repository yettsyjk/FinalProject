import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private auth: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  login(form: NgForm){
    const loggedUser = form.value;

    this.auth.login(loggedUser.username, loggedUser.password).subscribe(
      logged => {
            console.log('LoginComponent.login(): user logged in, routing to /todo.');
            this.router.navigateByUrl('/todo');
          },
          notLogged => {
            console.error('LoginComponent.login(): error logging in.');
          }

    );


  }

}

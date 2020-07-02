import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  errMessage = 'Password OR Username Incorrect';
  invalidLogin = false;
  loginSuccessful = false;
  successMessage = '';

  constructor(private auth: AuthService, private router: Router) {}

  ngOnInit(): void {}

  login(form: NgForm) {
    const loggedUser = form.value;

    this.auth.login(loggedUser.username, loggedUser.password).subscribe(
      (logged) => {
        this.invalidLogin = false;
        // this.loginSuccessful = true;
        this.successMessage = 'Login Successful';
        console.log(
          'LoginComponent.login(): user logged in, routing to /home.' + logged
        );
        this.router.navigateByUrl('/home');
      },
      (notLogged) => {
        this.invalidLogin = true;
        this.loginSuccessful = false;
        console.error('LoginComponent.login(): error logging in.' + notLogged);
      }
    );
  }
}

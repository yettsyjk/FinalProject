import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  newUser = new User();

  constructor(
    private authService: AuthService,
    private router: Router,

  ) { }

  ngOnInit() {
    console.log('Register component OnInit');
    this.router.navigateByUrl('/register');

  }

//functions
register(user: User){
  console.log(user);
  this.authService.register(user).subscribe(
    data => {
      console.log(data);
      console.log('RegisterComponent.register(): user registered.');
      this.authService.login(user.username, user.password).subscribe(
       loggedIn => {
          this.router.navigateByUrl('/home');
          console.log(loggedIn);
          console.log(user.username);
          // console.log(this.newUser.password);
          console.log('RegisterComponent.register(): user logged in, routing to /todo.');

        },
        notLoggedIn => {
          console.log(notLoggedIn);

        }
      );
    },
        error => {
          console.error('RegisterComponent.register(): error logging in.' + error );
        }
      );
    }







}

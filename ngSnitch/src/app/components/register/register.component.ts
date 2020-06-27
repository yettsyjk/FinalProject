import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';

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
    // private userService: UserService
  ) { }

  ngOnInit(): void {
    console.log('Register component OnInit');
  }

//functions
register(user: User){
  this.authService.register(this.newUser).subscribe(
    data => {
      console.log(data);
      console.log('RegisterComponent.register(): user registered.');
      this.authService.login(this.newUser.username, this.newUser.password).subscribe(
       loggedIn => {
          this.router.navigateByUrl('/home');
          console.log(loggedIn);
          console.log(this.newUser.username);
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

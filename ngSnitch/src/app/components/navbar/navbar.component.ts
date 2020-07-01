import { Component, OnInit, AfterViewChecked, AfterViewInit, OnChanges } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit, OnChanges {

  user = new User();



  constructor(
    private authService: AuthService,
    private router: Router,
    private userService: UserService

  ) { }
  ngOnChanges(): void {
    if (this.loggedIn()){
      console.log(this.loggedIn);
      this.getLoggedInUser();
    }
  }

  ngOnInit(): void {
    console.log('Navbar OnInit');
    if (this.loggedIn()){
      console.log(this.loggedIn);
      this.getLoggedInUser();
    }
  }


// verifyLoggedInUser(){
//   this.userService.displayLoggedInUser(id).subscribe(
//     data => {
//       this.currentUser = data;
//       console.log(data);
//     },
//     err => {
//       console.error('error in verify navbar' + err);
//     }
//   );
// }

  loggedIn(){
    return this.authService.checkLogin();
  }

  checkLogin(){
    return this.authService.checkLogin();
  }


  getLoggedInUser(){
    this.userService.displayLoggedInUser().subscribe(
      displayTheLoggedInUser => {
        console.log(' displayTheLoggedInUser: ' + displayTheLoggedInUser );
        this.user = displayTheLoggedInUser;
      },
      errorDisplayUser => {
        console.error('errorDisplayUser: ' +  errorDisplayUser);
      }
    );
  }


  adminLoggedIn(){
    if (this.loggedIn()) {
      return this.authService.getUserRole() === 'ADMIN';
    }
    return false;
  }


}

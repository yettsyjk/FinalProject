import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(
    private authService: AuthService,
    // private userService: UserService,
    private router: Router


  ) { }

  ngOnInit(): void {
    console.log('Navbar OnInit');

  }



  loggedIn(){
    return this.authService.checkLogin();
  }

  checkLogin(){
    return this.authService.checkLogin();
  }

  // getLoggedInUser(){
  //   //authService function called checkLoggedInUser() to subscribe
  // }


  // adminLoggedIn(){
  //   if (this.loggedIn()) {//getCurrentUserRole
  //     return this.authService.getCurrentUserProfile() === 'admin';

  //   }
  //   return false;
  // }


}

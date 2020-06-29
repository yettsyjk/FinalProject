import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(
    private authService: AuthService,
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

  // possibly use for filterring navBar view (guest, user, admin)
  // getLoggedInUser(){
  //   //authService function called checkLoggedInUser() to subscribe
  // }


  // adminLoggedIn(){
  //   if (this.loggedIn()) {//getCurrentUserRole
  //     return this.authService.getUserRole() === 'ADMIN';

  //   }
  //   return false;
  // }


}

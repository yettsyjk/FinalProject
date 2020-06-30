import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  currentUser = null;



  constructor(
    private authService: AuthService,
    private router: Router,
    private userService: UserService

  ) { }

  ngOnInit(): void {
    console.log('Navbar OnInit');

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

  // possibly use for filterring navBar view (guest, user, admin)
  getLoggedInUser(){
    //authService function called checkLoggedInUser() to subscribe
  this.authService.getUserRole();
  }


  adminLoggedIn(){
    if (this.loggedIn()) {
      return this.authService.getUserRole() === 'admin';
    }
    return false;
  }


}

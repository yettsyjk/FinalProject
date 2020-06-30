import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-userprofile',
  templateUrl: './userprofile.component.html',
  styleUrls: ['./userprofile.component.css']
})
export class UserprofileComponent implements OnInit {
userId = '';
user = null;

  constructor(private userService: UserService,
              private router: Router,
              private authService: AuthService) {}

  ngOnInit(): void {
    this.loadUser();
  }

  getUserId(){
  return this.authService.getUserId();
  }

  loadUser(){

   this.userId = this.getUserId();

   return this.userService.displayLoggedInUser(this.userId).subscribe(
      data => {
        this.user = data;
        console.log(data);
      },
      error => {
        console.error(error);
      }
    );
  }
}

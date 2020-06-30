import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { SnitchService } from 'src/app/services/snitch.service';
import { Router } from '@angular/router';
import { Alert } from 'src/app/models/alert';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  alert = new Alert();
  selectedUser = null;
  newUser = new User();
  users: User[] = [];

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private snitchService: SnitchService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadUser();
  }

  loadUser(){
    this.userService.index().subscribe(
      loadedUser => {
          this.users = loadedUser;
          console.log('this is the loaded User:' + loadedUser);
      },
      error => {
          console.error('this is the loaded User error: ' + error);
      }
    );
  }

  disableUser(id: number){
    this.userService.disableUser(id).subscribe(
    disableTheUser => {
    this.loadUser();
    this.selectedUser = null;
    console.log(disableTheUser);
  },
  errorDisable => {
    console.error('issues with disable user' + errorDisable);
  }
    );

  }

}

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';


import { AuthService } from 'src/app/services/auth.service';
import { SnitchService } from 'src/app/services/snitch.service';
import { UserService } from 'src/app/services/user.service';

import { Snitch } from 'src/app/models/snitch';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  comment = new Comment();
  selectedComment = null;
  disabledComment = null;
  comments: Comment[] = [];


  snitch = new Snitch();
  selectedSnitch = null;
  disabledSnitch = null;
  snitches: Snitch[] = [];



  selectedUser = null;
  newUser = new User();
  users: User[] = [];

  constructor(
    private authService: AuthService,
    private snitchService: SnitchService,
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadUser();
    this.loadSnitch();
  }

  loadUser(){
    this.userService.index().subscribe(
      loadedUser => {
          this.users = loadedUser;
          console.log('this is the loaded User:' + loadedUser);
          console.log(loadedUser);
      },
      error => {
          console.error('this is the loaded User error: ' + error);
      }
    );
  }

  loadSnitch(){
    this.snitchService.findAll().subscribe(
      loadedSnitch => {
        this.snitches = loadedSnitch;
        console.log(loadedSnitch);
        console.log('loading the snitches: ' + loadedSnitch);
      },
      wickedSnitch => {
        console.error(wickedSnitch);
        console.error('check the loadSnitch we have problems' + wickedSnitch);
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

  disableSnitch(id: number){
    this.snitchService.disable(id).subscribe(
        snitchesStitches => {
          this.loadSnitch();
          this.selectedSnitch = null;
          console.log(snitchesStitches);
        },
        myBad => {
          console.error(myBad);
          console.log('disbaleSnitch no worky: ' + myBad);
        }
    );
  }



}

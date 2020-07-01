import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { SnitchService } from 'src/app/services/snitch.service';
import { Router } from '@angular/router';
import { Alert } from 'src/app/models/alert';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { AlertService } from 'src/app/services/alert.service';
import { Snitch } from 'src/app/models/snitch';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  comment = new Comment();
  selectedComment = null;
  disabledComment = null;


  snitch = new Snitch();
  selectedSnitch = null;
  disabledSnitch = null;

  alert = new Alert();
  selectedAlert = null;
  editAlert = null;

  selectedUser = null;
  newUser = new User();
  users: User[] = [];

  constructor(
    private alertService: AlertService,
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
          console.log(loadedUser);
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


  // disableAlert(id: number){//I need a disable function from alertService
  //   this.alertService.disable(id).subscribe(
  //     alertRemoved => {
  //       this.selectedAlert =  null;
  //        console.log(alertRemoved);
  //     },
  //     errorRemoveAlert => {
  //       console.error(errorRemoveAlert);
  //     }
  //   );
  // }

  disableSnitch(snitchId){
    this.snitchService.disable(snitchId).subscribe(
        snitchesStitches => {
            this.selectedSnitch = snitchesStitches;
            console.log(snitchesStitches);
        },
        myBad => {
          console.error(myBad);
        }
    );
  }

  disableComment(){

  }

}

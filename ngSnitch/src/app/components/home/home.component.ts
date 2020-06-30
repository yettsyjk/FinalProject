import { Component, OnInit } from '@angular/core';
import {NgbAccordionConfig} from '@ng-bootstrap/ng-bootstrap';
import { SnitchService } from 'src/app/services/snitch.service';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']

})
export class HomeComponent implements OnInit {


  constructor(config: NgbAccordionConfig,
              private snitchService: SnitchService,
              private router: Router,
              private authService: AuthService ) {
    config.closeOthers = true;
    config.type = ''; // changes color of accordion background
    // config.type.bold=;
  }

  ngOnInit(): void {
  }
//this.router.navigateByUrl('/snitches');

checkLogin(){
  return this.authService.checkLogin();
}

  }






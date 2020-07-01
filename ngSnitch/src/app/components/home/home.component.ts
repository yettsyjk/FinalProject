import { Component, OnInit } from '@angular/core';
import { NgbAccordionConfig } from '@ng-bootstrap/ng-bootstrap';
import { SnitchService } from 'src/app/services/snitch.service';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { SnitchCountPipe } from 'src/app/pipes/snitch-count.pipe';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  snitches = [];

  constructor(
    config: NgbAccordionConfig,
    private snitchService: SnitchService,
    private router: Router,
    private authService: AuthService,
    private snitchCount: SnitchCountPipe
  ) {
    config.closeOthers = true;
    config.type = ''; // changes color of accordion background
    // config.type.bold=;
  }

  ngOnInit(): void {
    this.loadSnitches();
  }
  //this.router.navigateByUrl('/snitches');

  checkLogin() {
    return this.authService.checkLogin();
  }

  loadSnitches() {
    this.snitchService.findAll().subscribe(
      (snitches) => {
        console.log(snitches);
        snitches.reverse();
        this.snitches = snitches;
      },
      (noGo) => {
        console.error('SnitchListComponent.index(): error retrieving snitches');
        console.error(noGo);
      }
    );
  }

  getNumberOfAllSnitches() {
    return this.snitchCount.transform(this.snitches).length;
  }
}

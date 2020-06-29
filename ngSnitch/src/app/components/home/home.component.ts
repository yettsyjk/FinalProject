import { Component, OnInit } from '@angular/core';
import {NgbAccordionConfig} from '@ng-bootstrap/ng-bootstrap';
import { SnitchService } from 'src/app/services/snitch.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']

})
export class HomeComponent implements OnInit {


  constructor(config: NgbAccordionConfig,
              private snitchService: SnitchService,
              private router: Router ) {
    config.closeOthers = true;
    config.type = 'info'; // changes color of accordion background
    // config.type.bold=;
  }

  ngOnInit(): void {
  }
//this.router.navigateByUrl('/snitches');


  }






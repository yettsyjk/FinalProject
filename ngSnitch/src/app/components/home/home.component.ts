import { Component, OnInit } from '@angular/core';
import {NgbAccordionConfig} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']

})
export class HomeComponent implements OnInit {

  constructor(config: NgbAccordionConfig) {
    config.closeOthers = true;
    config.type = 'info'; // changes color of accordion background
    // config.type.bold=;
  }

  ngOnInit(): void {
  }

  }






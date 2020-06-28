import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';


@Component({
  selector: 'app-alert-list',
  templateUrl: './alert-list.component.html',
  styleUrls: ['./alert-list.component.css']
})
export class AlertListComponent implements OnInit {
  title = 'ALERT';
  editAlert = null;
  newAlert = null;


  alert: Alert[] = [];
  alerts = [];

  alertCategory = [
    'Traffic',
    'Lost Animal',
    'Gigs'
  ];

  constructor(
    private router: Router,
    private authService: AuthService,
    private alertService: AlertService
  ) { }

  ngOnInit() {
    this.loadAlert();
  }

  loadAlert(){//this function is dependant on alertService index()
    this.alertService.index().subscribe(
      data => {
        this.alerts = data;
        console.log('Alert array data: ' + data);
      },
      error => {
        console.log('error in Alert: ' + error);
      }
    );
  }


displayAlert(alert: Alert){
  this.router.navigateByUrl(`/alerts/${alert.id}`);
}




}

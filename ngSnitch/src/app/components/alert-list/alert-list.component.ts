import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { AlertService } from 'src/app/services/alert.service';
import { Alert } from 'src/app/models/alert';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-alert-list',
  templateUrl: './alert-list.component.html',
  styleUrls: ['./alert-list.component.css'],
  providers: [NgbCarouselConfig]  // add NgbCarouselConfig to the component providers
})
export class AlertListComponent implements OnInit {
  title = 'ALERT';
  editAlert = null;
  newAlert = null;
  showNavigationArrows = false;
  showNavigationIndicators = false;
  images = [1055, 194, 368].map((n) => `https://picsum.photos/id/${n}/900/500`);
  image = "https://img.icons8.com/pastel-glyph/2x/truck.png";
  imgUrl = '';
  // alert: Alert[] = [];
  alerts: Alert[] = [];

  alertCategory = [
    'Traffic',
    'Lost Animal',
    'Gigs'
  ];

  constructor(
    private router: Router,
    private authService: AuthService,
    private alertService: AlertService,
    config: NgbCarouselConfig
  ) {
    config.showNavigationArrows = true;
    config.showNavigationIndicators = true;
  }

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


// displayAlert(alert: Alert){
//   this.router.navigateByUrl(`/alerts/${alert.id}`);
// }




}

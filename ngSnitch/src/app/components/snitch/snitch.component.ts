import { Component, OnInit } from '@angular/core';
import { Snitch } from 'src/app/models/snitch';
import { SnitchService } from 'src/app/services/snitch.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-snitch',
  templateUrl: './snitch.component.html',
  styleUrls: ['./snitch.component.css']
})
export class SnitchComponent implements OnInit {

  newSnitch: Snitch = new Snitch();
  selected: Snitch = null;

  constructor(private snitchService: SnitchService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.router.navigateByUrl('/snitch');
  }

  addSnitch(snitch: Snitch) {
    this.snitchService.create(snitch).subscribe(
      snitches=> { //data stream returns from the server
        this.newSnitch = new Snitch();
        this.router.navigateByUrl('/snitches');
      },
      fail => { //when the server responds with an Http status code in the error range
        console.error('SnitchListComponent.addSnitch()');
        console.error(fail);
      }
    );
  }


}

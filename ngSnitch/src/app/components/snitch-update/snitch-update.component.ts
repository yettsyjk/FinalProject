import { Component, OnInit } from '@angular/core';
import { Snitch } from 'src/app/models/snitch';
import { SnitchService } from 'src/app/services/snitch.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-snitch-update',
  templateUrl: './snitch-update.component.html',
  styleUrls: ['./snitch-update.component.css']
})
export class SnitchUpdateComponent implements OnInit {

  constructor(private snitchService: SnitchService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.router.navigateByUrl('/snitchUpdate');
  }


//   updateSnitch(snitch: Snitch) {


//     this.snitchService.update(snitch).subscribe(
//      updated => {
//       this.router.navigateByUrl('/snitches');
//     },
//     failed=> {
// console.error('SnitchListComponent.updateSnitch()');
// console.error(failed);
//     }
//     );

//   }

}

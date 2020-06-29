import { Component, OnInit } from '@angular/core';
import { Snitch } from 'src/app/models/snitch';
import { SnitchService } from 'src/app/services/snitch.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-snitch-list',
  templateUrl: './snitch-list.component.html',
  styleUrls: ['./snitch-list.component.css']
})
export class SnitchListComponent implements OnInit {
 snitches: Snitch[] = [];
 newSnitch: Snitch = new Snitch();
 selected: Snitch = null;

  constructor(private snitchService: SnitchService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
  }



  displaySnitch(snitch): void {
    this.selected = snitch;
  }

  loadAll() { //sets the property snitchess to the response data
    this.snitchService.findAll().subscribe(
      snitches => {
        this.snitches = snitches;
      },
      noGo => {
        console.error('TodoListComponent.index(): error retrieving todos');
        console.error(noGo);
      }
    );
    // this.redirect(); //method called from home
  }



  addSnitch(snitch: Snitch) {
    this.snitchService.create(snitch).subscribe(
      snitches=> { //data stream returns from the server
        this.newSnitch = new Snitch();
        this.loadAll();
      },
      fail => { //when the server responds with an Http status code in the error range
        console.error('SnitchListComponent.addSnitch()');
        console.error(fail);
      }
    );
  }


  updateSnitch(snitch: Snitch) {
    this.snitchService.update(snitch).subscribe(
     updated => {
     this.loadAll();
    },
    failed=> {
console.error('SnitchListComponent.updateSnitch()');
console.error(failed);
    }
    );

  }

  delete(id: number) {
    this.snitchService.disable(id).subscribe(
  snitches => {
      this.loadAll();
    },
    fail => {
      console.error('SnitchListComponent.deleteSnitch()');
      console.error(fail);
    }
    );

  }
//   redirect() { //takes to snitch-list html
//     this.router.navigate(['snitch-list']);
// }


}

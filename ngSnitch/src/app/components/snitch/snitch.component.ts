import { Component, OnInit } from '@angular/core';
import { Snitch } from 'src/app/models/snitch';
import { SnitchService } from 'src/app/services/snitch.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Category } from 'src/app/models/category';
import { Address } from 'src/app/models/address';

@Component({
  selector: 'app-snitch',
  templateUrl: './snitch.component.html',
  styleUrls: ['./snitch.component.css']
})
export class SnitchComponent implements OnInit {

  newSnitch: Snitch = new Snitch();
  selected: Snitch = null;
  selectedCategory: Category = new Category();
  selectedAddress: Address = new Address();

  constructor(private snitchService: SnitchService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.router.navigateByUrl('/snitch');
  }

  addSnitch(snitch, selectedCategory, selectedAddress) {
    console.log(snitch);
    console.log(selectedCategory);
    console.log(selectedAddress);
    snitch.category = selectedCategory;
    snitch.address = selectedAddress;

    this.snitchService.create(snitch).subscribe(
      snitches => { // data stream returns from the server
        this.newSnitch = new Snitch();
        this.router.navigateByUrl('/snitches');
      },
      fail => { // when the server responds with an Http status code in the error range
        console.error('SnitchListComponent.addSnitch()');
        console.error(fail);
      }
    );
  }



}

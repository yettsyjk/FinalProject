import { Component, OnInit } from '@angular/core';
import { SnitchService } from 'src/app/services/snitch.service';
import { Snitch } from 'src/app/models/snitch';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  snitches: Snitch[] = [];
  searchResults = [];

  constructor(private snitchService: SnitchService,
    private router :  Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
  //   this.route.paramMap.subscribe(
  //     params => {
  //       if (params.get('keyword')) {
  //         this.keyword = params.get('keyword');
  //         console.log("Search parameter being used: " + this.keyword);
  //       }
  //         this.generalSearch(params.get('keyword'));
  //       } else if (params.get('category')) {
  //         this.categorySearch(params.get('category'));
  //         this.keyword = params.get('category');

  // }

}
  }



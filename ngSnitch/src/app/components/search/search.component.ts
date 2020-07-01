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
    const keyword = this.route.snapshot.paramMap.get('keyword');
    this.search(keyword);
  }



  search(keyword){
    this.snitchService.searchByKeyword(keyword).subscribe(
      snitches => {
        console.log(snitches);
        this.snitches = snitches;
      },
      noGo => {
        console.error('SearchListComponent.index(): error retrieving snitches');
        console.error(noGo);
      }
    );
  }

}

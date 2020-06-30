import { Component, OnInit } from '@angular/core';
import { Snitch } from 'src/app/models/snitch';
import { SnitchService } from 'src/app/services/snitch.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { Category } from 'src/app/models/category';
import { Address } from 'src/app/models/address';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-snitch-list',
  templateUrl: './snitch-list.component.html',
  styleUrls: ['./snitch-list.component.css']
})
export class SnitchListComponent implements OnInit {
  closeResult = '';

 snitches: Snitch[] = [];
 newSnitch: Snitch = new Snitch();
 selected: Snitch = null;
 editSnitch: Snitch = null;
 clicked: boolean = false;
 showAddress: boolean = false;

 selectedCategory: Category = new Category();
  selectedAddress: Address = new Address();

  constructor(private snitchService: SnitchService,
    private authService: AuthService,
              private route: ActivatedRoute,
              private router: Router,
              private modalService: NgbModal) { }

  ngOnInit(): void {
    if(!this.checkLogin()){
    this.loadAll();
    }
    else{
      this.index();
    }
  }

  updateSnitch(snitch, selectedCategory, selectedAddress) {

    snitch.category = selectedCategory;
    snitch.address = selectedAddress;
    this.snitchService.update(snitch).subscribe(
     updated => {
      this.router.navigateByUrl('/snitches');
    },
    failed=> {
console.error('SnitchListComponent.updateSnitch()');
console.error(failed);
    }
    );

  }

  edit(snitch: Snitch){
    this.editSnitch = snitch;

  }

  address(){
    if(this.showAddress===false){
    this.showAddress = true;
  }
  else{
    this.showAddress = false;
  }
  }

  checkLogin(){
    return this.authService.checkLogin();
  }

  index(){
    this.snitchService.index().subscribe(
      snitches => {
        this.snitches = snitches;
      },
      noGo => {
        console.error('TodoListComponent.index(): error retrieving snitches');
        console.error(noGo);
      }
    );
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
        console.error('TodoListComponent.index(): error retrieving snitches');
        console.error(noGo);
      }
    );
    // this.redirect(); //method called from home
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

//********************************************** */

open(content, snitch) {
  this.editSnitch = snitch;
  this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
    this.closeResult = `Closed with: ${result}`;
  }, (reason) => {
    this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
  });
}

private getDismissReason(reason: any): string {
  if (reason === ModalDismissReasons.ESC) {
    return 'by pressing ESC';
  } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
    return 'by clicking on a backdrop';
  } else {
    return `with: ${reason}`;
  }
}

}

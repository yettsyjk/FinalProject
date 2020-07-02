import { Component, OnInit } from '@angular/core';
import { SnitchService } from 'src/app/services/snitch.service';
import { AuthService } from 'src/app/services/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { Snitch } from 'src/app/models/snitch';
import { Category } from 'src/app/models/category';
import { Address } from 'src/app/models/address';

@Component({
  selector: 'app-snitch-personal',
  templateUrl: './snitch-personal.component.html',
  styleUrls: ['./snitch-personal.component.css']
})
export class SnitchPersonalComponent implements OnInit {

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
              private modalService: NgbModal,
              ) { }

  ngOnInit(): void {
    this.index();
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
        console.error('SnitchListComponent.index(): error retrieving snitches');
        console.error(noGo);
      }
    );
  }

  address(){
    if(this.showAddress===false){
    this.showAddress = true;
  }
  else{
    this.showAddress = false;
  }
  }

  edit(snitch: Snitch){
    this.editSnitch = snitch;

  }

  updateSnitch(snitch, selectedCategory, selectedAddress) {

    snitch.category = selectedCategory;
    snitch.address = selectedAddress;
    this.snitchService.update(snitch).subscribe(
     updated => {
       this.index();
       this.router.navigateByUrl('/snitchPersonal');
    },
    failed=> {
console.error('SnitchListComponent.updateSnitch()');
console.error(failed);
    }
    );

  }

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

  close(){
    this.modalService.dismissAll();
  }

}

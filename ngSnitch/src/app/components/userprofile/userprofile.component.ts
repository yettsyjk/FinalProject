import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { User } from 'src/app/models/user';


@Component({
  selector: 'app-userprofile',
  templateUrl: './userprofile.component.html',
  styleUrls: ['./userprofile.component.css']
})
export class UserprofileComponent implements OnInit {
userId = '';
user = null;
users: User[] = [];
editUser: User = null;
closeResult = '';
selected: User = null;



  constructor(private userService: UserService,
              private router: Router,
              private authService: AuthService,
              private modalService: NgbModal) {}

  ngOnInit(): void {
    this.loadUser();
  }

  index(){
    this.userService.index().subscribe(
      indexOfUsers => {
        this.users = indexOfUsers;
      },
      badIndex => {
        console.error('userprofileComponent.index(): error retrieving users');
        console.error(badIndex);
      }
    );
  }

  edit(user: User){
    this.editUser = user;

  }

  updateUser(user) {
    this.userService.updateUser(user).subscribe(
     updated => {
      this.index();
      this.router.navigateByUrl('/userprofile');
    },
    failed => {
console.error('UpdateUserComponent.updateUser()');
console.error(failed);
    }
    );
  }


  open(content, user) {
    this.editUser = user;
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

  getUserId(){
  return this.authService.getUserId();
  }

  loadUser(){

   this.userId = this.getUserId();

   return this.userService.displayLoggedInUser(this.userId).subscribe(
      data => {
        this.user = data;
        console.log(data);
      },
      error => {
        console.error(error);
      }
    );
  }
}

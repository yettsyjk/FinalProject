import { Component, OnInit } from '@angular/core';
import { Snitch } from 'src/app/models/snitch';
import { Comment } from 'src/app/models/comment';
import { SnitchService } from 'src/app/services/snitch.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
// import { Category } from 'src/app/models/category';
// import { Address } from 'src/app/models/address';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CommentService } from 'src/app/services/comment.service';

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
 showComments: boolean = false;

 commentsList: Comment[] = [];
  newComment: Comment = new Comment();


  constructor(private snitchService: SnitchService,
    private authService: AuthService,
              private route: ActivatedRoute,
              private router: Router,
              private modalService: NgbModal,
              private commentService: CommentService) { }

  ngOnInit(): void {
    this.loadAll();
  }



  address(){
    if(this.showAddress===false){
    this.showAddress = true;
  }
  else{
    this.showAddress = false;
  }
  }
  comments(){
    if(this.showComments===false){
    this.showComments = true;
  }
  else{
    this.showComments = false;
  }
  }

  createComment(){
    this.router.navigateByUrl('/comments');
  }

  listComment(){
    this.router.navigateByUrl('/comments');
  }

  checkLogin(){
    return this.authService.checkLogin();
  }


  displaySnitch(snitch): void {
    this.selected = snitch;
  }

  loadAll() { //sets the property snitchess to the response data
    this.snitchService.findAll().subscribe(
      snitches => {
        console.log(snitches);

        this.snitches = snitches;
      },
      noGo => {
        console.error('SnitchListComponent.index(): error retrieving snitches');
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

hideComments(){
  this.showComments = false;
}

loadAllComments(snitchId) {
  this.showComments = true;
  console.log('snitch ID of: ' + snitchId);

  this.commentService.commentsForPost(snitchId).subscribe(
    comments => {
      console.log(comments);

      this.commentsList = comments;
    },
    noGo => {
      console.error('SnitchListComponent.index(): error retrieving comments');
      console.error(noGo);
    }
  );
}

addComment(comment, sId) {

  this.commentService.create(comment, sId).subscribe(
    comments => { // data stream returns from the server
      this.newComment = new Comment();
      // this.router.navigateByUrl('/snitches');
    },
    fail => { // when the server responds with an Http status code in the error range
      console.error('SnitchListComponent.addComment()');
      console.error(fail);
    }
  );
}


}

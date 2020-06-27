import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(
    private authService: AuthService,
    private router: Router

  ) { }

  ngOnInit(): void {
    console.log('logout component');
    this.logout();
  }

  logout(){
    console.log('logged out user');
    this.authService.logout();
    this.router.navigateByUrl('/home');
  }

}

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { User } from '../models/user';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private url = 'http://localhost:8090/' + 'api/users';



  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) { }


  index() {
   const httpOptions = this.getHttpOptions();
   return this.http.get<User[]>(this.url + '/', httpOptions).pipe(
     catchError((err: any) => {
       console.log(err);
       return throwError('index in userService');
     })
   );
  }

  displayLoggedInUser(){

      const httpOptions = this.getHttpOptions();
      console.log('displayLoggedInUser: ');
      return this.http.get<User>(`${this.url}/username`, httpOptions).pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError(' displayLoggedInUser oops' + err);
        })
      );

    }

  private getHttpOptions(){
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return httpOptions;
  }



  disableUser(id: number){
    const httpOptions = this.getHttpOptions();
    if (this.authService.checkLogin()){//i need to get the id
      //if user enabled === true
      return this.http.delete<User>(`${this.url}/${id}`, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError('disable the user');
        })
      );
    }
  }
}

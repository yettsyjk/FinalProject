import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { Alert } from '../models/alert';

@Injectable({
  providedIn: 'root'
})
export class AlertService {
private url = 'http://localhost:8090/' + 'api/alerts';



  constructor(
    private authService: AuthService,
    private http: HttpClient
  ) { }

  //getHttpOptions is stubbed out but on front end Alert is not created/updated/deleted
  private getHttpOptions(){
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${this.authService.getCredentials()}`,
        'X-Requested-With': 'XMLHtppRequest'
      })
    };
    return httpOptions;
  }
//load function in alertlist awaits index and Alert model
index(){
  return this.http.get<Alert[]>(this.url).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError('error in alert Service');
    })
  );
}

}

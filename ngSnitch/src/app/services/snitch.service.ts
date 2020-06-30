import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { Snitch } from '../models/snitch';
import { Address } from '../models/address';

@Injectable({
  providedIn: 'root'
})
export class SnitchService {
  private baseUrl = 'http://localhost:8090/';
  private url = this.baseUrl + 'api/snitches';

  constructor(
    private http: HttpClient, // inject to connect to Controller/DB\
    private auth: AuthService)
    { }

    index(){ // a request to the API's index route: loads all personal snitches
    const credentials = this.auth.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    };

    return this.http.get<Snitch[]>(this.url, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Error retrieving snitches' + err);
        })

      );
    }

    findAll(){ // retrieves snitches for a guest user
      return this.http.get<Snitch[]>(this.url)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('error in snitch service findAll method');
        })
      );
    }

      show(snitchId: number){
        const credentials = this.auth.getCredentials();
        const httpOptions = {
          headers: new HttpHeaders({
            Authorization: `Basic ${credentials}`,
            'X-Requested-With': 'XMLHttpRequest'
          })
        };

        return this.http.get<Snitch>(`${this.url}/${snitchId}`, httpOptions).pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('Error retrieving snitch' + err);
          })

        );
      }
      create(snitch: Snitch){
        const credentials = this.auth.getCredentials();
        const httpOptions = {
          headers: new HttpHeaders({
            Authorization: `Basic ${credentials}`,
            'X-Requested-With': 'XMLHttpRequest'
          })
        };

        return this.http.post<Snitch>(this.url, snitch, httpOptions).pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('Error creating a snitch' + err);
          })
        );

      }

      update(snitch: Snitch){
        const credentials = this.auth.getCredentials();
        const httpOptions = {
          headers: new HttpHeaders({
            Authorization: `Basic ${credentials}`,
            'X-Requested-With': 'XMLHttpRequest'
          })
        };
    // if(snitch.resolved === true){
    //   snitch.resolutionDate = this.datePipe.transform(Date.now(), 'longDate');
    // } else{
    //   snitch.resolutionDate = "";
    // }

        return this.http.put<Snitch>(`${this.url}/${snitch.id}`, snitch, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error updating a snitch' + err);
      })

    );

      }

      disable(id: number){
        const credentials = this.auth.getCredentials();
        const httpOptions = {
          headers: new HttpHeaders({
            Authorization: `Basic ${credentials}`,
            'X-Requested-With': 'XMLHttpRequest'
          })
        };
        return this.http.delete<Snitch>(`${this.url}/${id}`, httpOptions).pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('Error deleting a snitch' + err);
          })
        );
  }



}

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  private url = environment.baseUrl + 'api/snitches';

  private baseUrl = 'http://localhost:8090/';
  // private url = this.baseUrl + 'api/snitches';

  constructor(private http: HttpClient, // inject to connect to Controller/DB\
    private auth: AuthService) { }


  commentsForPost(snitchId){ // retrieves comments for a post
    return this.http.get<Comment[]>(`${this.url}/${snitchId}/` + 'comments')
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('error in comment service commentsForPost method');
      })
    );
  }

  create(comment, snitchId){
    const credentials = this.auth.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    };

    return this.http.post<Comment>(`${this.url}/${snitchId}/` + 'comments', comment, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error creating a comment' + err);
      })
    );

  }



}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Review } from '../models/review';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  private url = environment.baseUrl + 'api/reviews'

  constructor(private http: HttpClient, private auth: AuthService) { }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  index(): Observable<Review> {
    return this.http.get<Review>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('ReviewService.index(): error retreiving reviews: ' + err)
        );
      })
    )
  }

  show(id: number): Observable<Review> {
    return this.http.get<Review>(this.url + '/' + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError (
          () => new Error('ReviewService.show(): error retreiving review: ' + err)
        );
      })
    );
  }

  update(review: Review, id: number): Observable<Review> {
    return this.http.put<Review>(this.url + '/' + id, review, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('ReviewService.update(): error updating review: ' + err)
        );
      })
    );
  }

  destroy(id: number): Observable<void> {
    return this.http.delete<void>(this.url + '/' + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('ReviewService.destroy(): error destroying review: ' + err)
        );
      })
    )
  }

  create(review: Review): Observable<Review> {
    return this.http.post<Review>(this.url + '/review', review, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('ReviewService.create(): error creating review: ' + err)
        );
      })
    )
  }


}

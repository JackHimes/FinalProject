import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Genre } from '../models/genre';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class GenreService {
  private url = environment.baseUrl +'genres';


  constructor(
  private http: HttpClient,
  private auth: AuthService
  ) { }

getHttpOptions() {
 let options = {
    headers: {
      Authorization: 'Basic ' + this.auth.getCredentials(),
      'X-Requested-With': 'XMLHttpRequest',
    },
  };
  return options;
}

show(id: number): Observable<Genre> {
  return this.http.get<Genre>(this.url + '/' + id, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError (
        () => new Error('GenreService.show(): error retreiving genre: ' + err)
      );
    })
  );
}

index(): Observable<Genre[]> {
  return this.http.get<Genre[]>(this.url, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () =>
          new Error('GenreService.index(): error retreiving genre: ' + err)
      );
    })
  )
}

update(genre: Genre, id: number): Observable<Genre> {
  return this.http.put<Genre>(this.url + '/' + id, genre, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error('GenreService.create(): error updating genre: ' + err)
      );
    })
  );
}

destroy(id: number): Observable<void> {
  return this.http.delete<void>(this.url + '/' + id, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error('GenreService.create(): error destroying genre: ' + err)
      );
    })
  )
}

}

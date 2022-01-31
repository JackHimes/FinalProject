import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Gameevent } from '../models/gameevent';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class GameeventService {


  private url = environment.baseUrl + 'api/gameevents';



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

   show(id: number): Observable<Gameevent> {
     return this.http.get<Gameevent>(this.url + '/' + id, this.getHttpOptions()).pipe(
       catchError((err: any) => {
         console.log(err);
         return throwError (
           () => new Error('GameeventService.show(): error retreiving Gameevent: ' + err)
         );
       })
     );
   }

   searchByKeyword(keyword: string): Observable<Gameevent[]> {
    return this.http.get<Gameevent[]>(this.url + '/search/' + keyword, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('GameEventService.searchByKeyword(): error retrieving game events' + err)
        );
      })
    );
  }

   index(): Observable<Gameevent> {
     return this.http.get<Gameevent>(this.url, this.getHttpOptions()).pipe(
       catchError((err: any) => {
         console.log(err);
         return throwError(
           () =>
             new Error('GameeventService.index(): error retreiving Gameevent: ' + err)
         );
       })
     )
   }

   update(gameevent: Gameevent, id: number): Observable<Gameevent> {
     return this.http.put<Gameevent>(this.url + '/' + id, gameevent, this.getHttpOptions()).pipe(
       catchError((err: any) => {
         console.log(err);
         return throwError(
           () => new Error('GameeventService.create(): error updating Gameevent: ' + err)
         );
       })
     );
   }

   destroy(id: number): Observable<void> {
     return this.http.delete<void>(this.url + '/' + id, this.getHttpOptions()).pipe(
       catchError((err: any) => {
         console.log(err);
         return throwError(
           () => new Error('GameeventService.create(): error destroying Gameevent: ' + err)
         );
       })
     )
   }
}

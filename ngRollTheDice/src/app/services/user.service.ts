import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Game } from '../models/game';
import { User } from '../models/user';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url = environment.baseUrl + 'api/users';

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

  index(): Observable<User> {
    return this.http.get<User>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('UserService.index(): error retreiving users: ' + err)
        );
      })
    )
  }

  show(id: number): Observable<User> {
    return this.http.get<User>(this.url + '/' + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError (
          () => new Error('UserService.show(): error retreiving user: ' + err)
        );
      })
    );
  }

  searchByKeyword(keyword: string): Observable<User[]> {
    return this.http.get<User[]>(this.url + '/search/' + keyword, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('UserService.searchByKeyword(): error retrieving users' + err)
        );
      })
    );
  }

  update(user: User, id: number): Observable<User> {
    return this.http.put<User>(this.url + '/' + id, user, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('UserService.update(): error updating user: ' + err)
        );
      })
    );
  }

  destroy(id: number): Observable<void> {
    return this.http.delete<void>(this.url + '/' + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('UserService.destroy(): error destroying user: ' + err)
        );
      })
    )
  }

  addGuestToGameEvent(user: User): Observable<User> {
    return this.http.put<User>(this.url, user, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error('UserService.addGuestToGameEvent(): error adding guest to GameEvent: ' + err)
          );
        })
      );
  }

  addGame(gameId: number): Observable<Game> {
    return this.http.put<Game>(`${this.url}/games/${gameId}`, {}, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('UserService.addGame(): error adding game to user: ' + err)
        );
      })
    );
  }


}

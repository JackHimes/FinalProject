import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Game } from '../models/game';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  private url = environment.baseUrl +'api/games';

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

show(id: number): Observable<Game> {
  return this.http.get<Game>(this.url + '/' + id, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError (
        () => new Error('GameService.show(): error retreiving game: ' + err)
      );
    })
  );
}


searchByKeyword(keyword: string): Observable<Game[]> {
  return this.http.get<Game[]>(this.url + '/search/' + keyword, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error('GameService.searchByKeyword(): error retrieving games' + err)
      );
    })
  );
}

index(): Observable<Game[]> {
  return this.http.get<Game[]>(this.url, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () =>
          new Error('GameSvc.index(): error retreiving games: ' + err)
      );
    })
  )
}

create(game: Game): Observable<Game> {
  return this.http.post<Game>(this.url, game, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () =>
          new Error('GameService.create(): error creating game: ' + err)
      );
    })
  )
}

update(game: Game): Observable<Game> {
  return this.http.put<Game>(this.url + `/${game.id}`, game, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error('GameService.update(): error updating game: ' + err)
      );
    })
  );
}

destroy(gameId: number): Observable<void> {
  return this.http.delete<void>(this.url + '/' + gameId, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error('GameService.destroy(): error destroying game: ' + err)
      );
    })
  )
}

}

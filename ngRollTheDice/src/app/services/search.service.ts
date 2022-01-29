import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Game } from '../models/game';
import { Gameevent } from '../models/gameevent';
import { User } from '../models/user';
import { GameService } from './game.service';
import { GameeventService } from './gameevent.service';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class SearchService {
  private url = environment.baseUrl + '/api'


  constructor(
    private gameSvc: GameService,
    private gameEventSvc: GameeventService,
    private userSvc: UserService
  ) { }

  searchGameByKeyword(keyword: string): Observable<Game[]> {
    return this.gameSvc.searchByKeyword(keyword);
  }

  searchGameEventByKeyword(keyword: string): Observable<Gameevent[]> {
    return this.gameEventSvc.searchByKeyword(keyword);
  }

  searchUserByKeyword(keyword: string): Observable<User[]> {
    return this.userSvc.searchByKeyword(keyword);
  }

}

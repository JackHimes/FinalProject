import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Game } from 'src/app/models/game';
import { Gameevent } from 'src/app/models/gameevent';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { GameService } from 'src/app/services/game.service';
import { SearchService } from 'src/app/services/search.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  keyword: string = '';
  searchResults: any[] = [];

  games: Game[] = [];
  gameEvents: Gameevent[] = [];
  users: User[] = [];

  constructor(
    private authService: AuthService,
    private router: Router,
    private searchSvc: SearchService

  ) { }

  ngOnInit(): void {
  }

  checkKeyword() {
    console.log(this.keyword);
  }

  executeSearch(keyword: string): any[] {

    this.searchSvc.searchGameByKeyword(keyword).subscribe({
      next: (game) => {
        console.log('success searching game by keyword');
        this.games.push(game);
        this.searchResults.push(this.games);
      },
      error: (fail) => {
        console.log("searchComponent.executeSearch error retrieving games by keyword");
        console.log(fail);
      }
    });

    this.searchSvc.searchGameEventByKeyword(keyword).subscribe({
      next: (gameEvent) => {
        console.log('success searching game event by keyword')
        this.gameEvents.push(gameEvent);
        this.searchResults.push(this.gameEvents);
      },
      error: (fail) => {
        console.log("searchComponent.executeSearch error retrieving gameEvents by keyword");
        console.log(fail);
      }
    });

    this.searchSvc.searchUserByKeyword(keyword).subscribe({
      next: (user) => {
        console.log('success searching user by keyword');
        this.users.push(user);
        this.searchResults.push(this.users);
      },
      error: (fail) => {
        console.log("searchComponent.executeSearch error retrieving users by keyword");
        console.log(fail);
      }
    });
    console.log('searchResults Array prints below this');
    console.log(this.searchResults);
    return this.searchResults;
  }




}

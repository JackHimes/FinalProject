import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Game } from 'src/app/models/game';
import { Gameevent } from 'src/app/models/gameevent';
import { User } from 'src/app/models/user';
import { SearchService } from 'src/app/services/search.service';

@Component({
  selector: 'app-display',
  templateUrl: './display.component.html',
  styleUrls: ['./display.component.css']
})
export class DisplayComponent implements OnInit {
  keyword: string | null = '';
  userKeyword: string | null = '';
  gameKeyword: string | null = '';
  gameEventKeyword: string | null = '';
  searchResults: any[] = [];
  games: Game[] = [];
  gameEvents: Gameevent[] = [];
  users: User[] = [];
  // collapseOneDiv = document.getElementById("collapseOne");
  // collapseTwoDiv = document.getElementById("collapseTwo");
  // collapseThreeDiv = document.getElementById("collapseThree");


  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private searchSvc: SearchService
    ) { }

  ngOnInit(): void {
    this.keyword = this.route.snapshot.params['keyword'];
    if (this.keyword) {
      this.executeSearch(this.keyword);
    }

  }

  executeSearch(keyword: string): any[] {

    this.searchSvc.searchGameByKeyword(keyword).subscribe({
      next: (game) => {
        console.log('success searching game by keyword!' + keyword);
        this.games = game;
        this.searchResults.push(this.games);
      },
      error: (fail) => {
        console.log("searchComponent.executeSearch error retrieving games by keyword");
        console.log(fail);
      }
    });

    this.searchSvc.searchGameEventByKeyword(keyword).subscribe({
      next: (gameEvent) => {
        console.log('success searching game event by keyword!' + keyword)
        this.gameEvents = gameEvent;
        this.searchResults.push(this.gameEvents);
      },
      error: (fail) => {
        console.log("searchComponent.executeSearch error retrieving gameEvents by keyword");
        console.log(fail);
      }
    });

    this.searchSvc.searchUserByKeyword(keyword).subscribe({
      next: (user) => {
        console.log('success searching user by keyword!' + keyword);
        this.users = user;
        this.searchResults.push(this.users);
      },
      error: (fail) => {
        console.log("searchComponent.executeSearch error retrieving users by keyword");
        console.log(fail);
      }
    });
    return this.searchResults;
  }

  toggleCollapseOne() {
    let collapseOneDiv = document.getElementById("collapseOne");
    collapseOneDiv?.classList.toggle("show");
  }

  toggleCollapseTwo() {
    let collapseTwoDiv = document.getElementById("collapseTwo");
    collapseTwoDiv?.classList.toggle("show");
  }

  toggleCollapseThree() {
    let collapseThreeDiv = document.getElementById("collapseThree");
    collapseThreeDiv?.classList.toggle("show");
  }

  executeUserSearch(userKeyword: string): void {
    this.searchSvc.searchUserByKeyword(userKeyword).subscribe({
      next: (user) => {
        console.log('success searching user by keyword' + userKeyword);
        console.log(user);
        this.users = user;
        this.userKeyword = '';
      },
      error: (fail) => {
        console.log("searchComponent.executeSearch error retrieving users by keyword");
        console.log(fail);
      }
    });
  }

  executeGameSearch(gameKeyword: string): void {
    this.searchSvc.searchGameByKeyword(gameKeyword).subscribe({
      next: (game) => {
        console.log('success searching game by keyword' + gameKeyword);
        this.games = game;
        this.gameKeyword = '';
      },
      error: (fail) => {
        console.log("searchComponent.executeSearch error retrieving games by keyword");
        console.log(fail);
      }
    });
  }

  executeGameEventSearch(gameEventKeyword: string): void {
    this.searchSvc.searchGameEventByKeyword(gameEventKeyword).subscribe({
      next: (gameEvent) => {
        console.log('success searching gamevent by keyword' + gameEventKeyword);
        this.gameEvents = gameEvent;
        this.gameEventKeyword = '';
      },
      error: (fail) => {
        console.log("searchComponent.executeSearch error retrieving gameevents by keyword");
        console.log(fail);
      }
    });
  }

  loadUsers() {
    this.clearResultsArrays();
    if (this.userKeyword) {
      console.log(this.userKeyword);
      this.executeUserSearch(this.userKeyword);

    }
  }

  loadGames() {
    this.clearResultsArrays();
    if (this.gameKeyword) {
      console.log(this.gameKeyword);
      this.executeGameSearch(this.gameKeyword);

    }
  }

  loadGameEvents() {
    this.clearResultsArrays();
    if (this.gameEventKeyword) {
      console.log(this.gameEventKeyword);
      this.executeGameEventSearch(this.gameEventKeyword);

    }
  }

  clearResultsArrays() {
  this.games = [];
  this.gameEvents= [];
  this.users= [];
  }

}

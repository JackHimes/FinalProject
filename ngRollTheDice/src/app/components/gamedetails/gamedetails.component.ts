import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Game } from 'src/app/models/game';
import { FilterGenrePipe } from 'src/app/pipes/filter-genre.pipe';
import { GameService } from 'src/app/services/game.service';


@Component({
  selector: 'app-gamedetails',
  templateUrl: './gamedetails.component.html',
  styleUrls: ['./gamedetails.component.css']
})
export class GamedetailsComponent implements OnInit {
  game: Game = new Game();
  id: number = 0;
  games: Game[] = [];
  similarGames: Game[] = [];
  displayGame1: Game = new Game();
  displayGame2: Game = new Game();
  displayGame3: Game = new Game();
  buttonClick = false;
  currentRate = Math.random() * 10;

  constructor(
    private gameSvc: GameService,
    private route: ActivatedRoute,
    private router: Router,
    private filterGenrePipe: FilterGenrePipe
  ) { }

  ngOnInit(): void {
    let idStr = this.route.snapshot.paramMap.get('gameId');
    if (idStr) {
      this.id = Number.parseInt(idStr);
    }
    this.loadMainGame();
    this.loadGames();
    setTimeout(() => {
      this.randomGames();
    }, 10000);
  }

  randomGames() {
    if (this.game.genres) {
      this.similarGames = this.filterGenrePipe.transform(this.games, this.game.genres, this.game);
    }
    let num1 = this.getRandomNum(this.similarGames.length);
    num1 = parseInt(num1.toString());
    let num2 = this.getRandomNum(this.similarGames.length);
    num2 = parseInt(num2.toString());
    let num3 = this.getRandomNum(this.similarGames.length);
    num3 = parseInt(num3.toString());

    this.displayGame1 = this.similarGames[num1];
    this.displayGame2 = this.similarGames[num2];
    this.displayGame3 = this.similarGames[num3];

  }

  loadGames() {
    this.gameSvc.index().subscribe({
      next: (g) => {
        this.games = g;
      },
      error: (fail) => {
        console.error("error in GameDetails Component.loadGame... Here the error: " + fail)
      }
    });
  }

  loadMainGame() {
    this.gameSvc.show(this.id).subscribe({
      next: (g) => {
        this.game = g;
      },
      error: (fail) => {
        console.error("error in GameDetails Component.loadGame... Here the error: " + fail);
      }
    });
  }

  getRandomNum(num: number): number {
    return (Math.random() * num);
  }

  redirect(num: number) {
    this.router.navigateByUrl('gamedetails/' + num);
  }

}



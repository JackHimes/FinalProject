import { Component, OnInit } from '@angular/core';
import { Game } from 'src/app/models/game';
import { Genre } from 'src/app/models/genre';
import { GameService } from 'src/app/services/game.service';
import { GenreService } from 'src/app/services/genre.service';

@Component({
  selector: 'app-creation',
  templateUrl: './creation.component.html',
  styleUrls: ['./creation.component.css']
})
export class CreationComponent implements OnInit {

  newGame: Game = new Game();
  genres: Genre [] = [];

  constructor(
    private gameService: GameService,
    private genreService: GenreService,
  ) { }

  ngOnInit(): void {
    this.loadGenres();
    
  }

  addGame(game : Game): void{
    this.gameService.create(game).subscribe({
      next: (game) => {
        this.newGame = new Game();
      }, error: (fail) => {
        console.error("Failed to post game")
      }

    });
  }

  loadGenres(){
    this.genreService.index().subscribe({
      next: (allGenres) => {
        this.genres = allGenres;
      },
      error: (fail) => {
        console.error("Failed to loadGenres() in creation.component.ts")
      }
    });
  }


  // addTodo(todo : Todo): void{
  //   this.todoService.create(todo).subscribe({
  //       next: (todo) => {
  //       this.reload();
  //       this.newTodo = new Todo();
  //     }, error : (fail) =>{
  //       console.error("FAILED TODO POST")
  //     } 
  //   });
    
  //   }

}

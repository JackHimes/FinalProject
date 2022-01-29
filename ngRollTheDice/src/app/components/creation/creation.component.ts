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
  checked: Genre [] = [];

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
        console.error("Failed to loadGenres() in creation.component.ts" + fail)

      }
    });
  }

  addGenresToChecked(genre : Genre){

    const checkedGenre = this.genres.find(g => g.id === genre.id);
    if(checkedGenre){
      this.genres.splice(this.genres.indexOf(checkedGenre), 1);
    } else{
      this.genres.push(genre);
    }
    
  }


//   checkChanged(car)
// {
//   const checkedCar = this.cars.find(c => c.id === car.id);
//   if(checkedCar)
//   {
//     this.cars.splice(this.cars.indexOf(checkedCar), 1);
//   }
//   else
//   {
//     this.cars.push(car);
//   }
// }

  removeGenre(){

  }




}

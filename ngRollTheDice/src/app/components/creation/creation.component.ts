import { Component, OnInit } from '@angular/core';
import { Address } from 'src/app/models/address';
import { Game } from 'src/app/models/game';
import { Genre } from 'src/app/models/genre';
import { AddressService } from 'src/app/services/address.service';
import { GameService } from 'src/app/services/game.service';
import { GenreService } from 'src/app/services/genre.service';

@Component({
  selector: 'app-creation',
  templateUrl: './creation.component.html',
  styleUrls: ['./creation.component.css']
})
export class CreationComponent implements OnInit {

  newGame: Game = new Game();
  newAddress: Address = new Address();
  genres: Genre [] = [];
  checked: Genre [] = [];
  addGameBoolean: boolean = false;

  constructor(
    private gameService: GameService,
    private genreService: GenreService,
    private addressService: AddressService,
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
  addAddress(address : Address): void{
    this.addressService.createFavoriteVenueAddress(address).subscribe({
      next: (game) => {
        this.newAddress = new Address();
      }, error: (fail) => {
        console.error("Failed to post Address")
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
  ////////////////////////MAGIC ONLINE ANSWER FOR BELOW/////////////////////////
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
    ////////////////////////MAGIC ONLINE ANSWER FOR BELOW/////////////////////////

  addGenresToChecked(genre : Genre){

    const checkedGenre = this.checked.find(g => g.id === genre.id);
    if(checkedGenre){
      this.checked.splice(this.genres.indexOf(checkedGenre), 1);
      console.log(this.checked);
      
    } else{
      this.checked.push(genre);
      console.log(this.checked);
      
    }
    
  }

  addSelectedGenresBeforeAddingGame(){
    this.newGame.genres = this.checked;
    this.addGame(this.newGame);
    console.log(this.newGame);
    
  }


  removeGenre(){

  }




}

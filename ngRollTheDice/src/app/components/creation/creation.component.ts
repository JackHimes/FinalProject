import { Component, OnInit } from '@angular/core';
import { Address } from 'src/app/models/address';
import { Game } from 'src/app/models/game';
import { Gameevent } from 'src/app/models/gameevent';
import { Genre } from 'src/app/models/genre';
import { User } from 'src/app/models/user';
import { AddressService } from 'src/app/services/address.service';
import { AuthService } from 'src/app/services/auth.service';
import { GameService } from 'src/app/services/game.service';
import { GameeventService } from 'src/app/services/gameevent.service';
import { GenreService } from 'src/app/services/genre.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-creation',
  templateUrl: './creation.component.html',
  styleUrls: ['./creation.component.css']
})
export class CreationComponent implements OnInit {

  newGame: Game = new Game();
  newAddress: Address = new Address();
  newGameEvent: Gameevent = new Gameevent();

  loggedInUser: User = new User();
  userAddressess: Address[] = [];
  genres: Genre [] = [];
  checked: Genre [] = [];

  addGameBoolean: boolean = false;
  addAddressBoolean: boolean = false;
  addGameEventBoolean: boolean = false;

  constructor(
    private gameService: GameService,
    private genreService: GenreService,
    private addressService: AddressService,
    private gameEventService: GameeventService,
    private authService: AuthService,
    private userService: UserService,
  ) { }

  ngOnInit(): void {
    this.loadGenres();
    // this.loadAddressess();
    this.findLoggedInUser();
    
  }

  addGame(game : Game): void{
    this.gameService.create(game).subscribe({
      next: (game) => {
        console.log(game);
        
        // this.loggedInUser.games?.push(game);
        // this.updateLoggedInUser(this.loggedInUser);
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
  addGameEvent(gameEvent : Gameevent): void{
    this.gameEventService.create(gameEvent).subscribe({
      next: (gameEventData) => {
        this.newGameEvent = new Gameevent();
      }, error: (fail) => {
        console.error("Failed to post GameEvent")
      }

    });
  }
  updateLoggedInUser(user: User){
    this.userService.update(user, user.id).subscribe({
      next: (data) => {
        console.log("Success updating user in updateLoggedInUser()" + data)
      },
      error: (err) => {
        console.error("Error updating user in updateLoggedInUser() " + err)
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
  // loadAddressess(){
  //   this.addressService.index().subscribe({
  //     next: (allAddressess) => {
  //       this.addressess = allAddressess;
  //     },
  //     error: (fail) => {
  //       console.error("Failed to loadAddresses() in creation.component.ts" + fail)

  //     }
  //   });
  // }
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
    console.log(this.newGame.genres);
    this.newGame.genres = this.checked;
    console.log(this.newGame.genres);
    
    this.addGame(this.newGame);
    

    console.log(this.loggedInUser.games);
    this.loggedInUser.games?.push(this.newGame);
    console.log(this.loggedInUser.games);
    this.loadGenres();
    document.getElementById("genreCheckbox");

    // this.updateLoggedInUser(this.loggedInUser);
    
  }


  findLoggedInUser(){
    this.userService.show(this.authService.getCurrentUserId()).subscribe({
      next: (foundUser) => {
        this.loggedInUser = foundUser;
        
      },
      error: (err) => {
        console.error("Failed to retrieve user in findAddressessOfLoggedInUser(): " + err)
      }
    });





  }


}

import { Pipe, PipeTransform } from '@angular/core';
import { Game } from '../models/game';
import { Genre } from '../models/genre';

@Pipe({
  name: 'filterGenre',
})
export class FilterGenrePipe implements PipeTransform {
  transform(games: Game[], genres: Genre[], mainGame: Game): Game[] {
    let result: Game[] = [];
    for (const game of games) {
      if (game.genres) {
        for (const g of game.genres) {
          for (const genre of genres) {
            if (g.id === genre.id && game.id != mainGame.id && !result.includes(game)) {
              result.push(game);
          }
          }
        }
      }
    }
    return result;
  }
}

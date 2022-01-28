import { Game } from "./game";

export class Genre {
  id: number;
  name: string | undefined;
  description: string | undefined;
  games: Game[] | undefined;

  constructor(
    id: number = 0,
    name: string,
    description: string,
    games: Game[]
  ){
    this.id = id;
    this.name = name;
    this.description = description;
    this.games = games;
  }

}

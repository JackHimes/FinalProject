import { Gameevent } from "./gameevent";
import { Genre } from "./genre";
import { User } from "./user";

export class Game {

id: number | undefined;
name: string | undefined;
maxPlayers: number | undefined;
linkToGame: string | undefined;
timeToPlay: string | undefined;
description: string | undefined;
imageUrl: string | undefined;
users: User[] | undefined;
gameEvents: Gameevent[] | undefined;
gameOwner: User | undefined;
genres: Genre[] | undefined;

constructor(id?: number, name?: string, maxPlayers?: number, linkToGame?: string, timeToPlay?: string, description?: string, imageUrl?: string,
  users?: User[], gameEvents?: Gameevent[], gameOwner?: User, genres?: Genre[]) {
  this.id = id;
  this.name = name;
  this.maxPlayers = maxPlayers;
  this.linkToGame = linkToGame;
  this.timeToPlay = timeToPlay;
  this.description = description;
  this.imageUrl = imageUrl;
  this.users = users;
  this.gameEvents = gameEvents;
  this.gameOwner = gameOwner;
  this.genres = genres;
}

}

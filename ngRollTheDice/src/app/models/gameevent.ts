import { max } from "rxjs";
import { Address } from "./address";
import { Comment } from "./comment";
import { Eventtag } from "./eventtag";
import { Game } from "./game";
import { Review } from "./review";
import { User } from "./user";

export class Gameevent {
  //host id & address id can't be null
  id: number;
  dateOfEvent: string | undefined;
  maxNumberOfGuests: number | undefined;
  enabled: boolean;
  startTime: string | undefined;
  endTime: string | undefined;
  imageUrl: string | undefined;
  description: string | undefined;
  title: string | undefined;
  comments: Comment[] | undefined;
  reviews: Review[] | undefined;
  games: Game[] | undefined;
  guests: User[] | undefined;
  eventTags: Eventtag[] | undefined;
  address: Address;
  host: User;

  constructor(
  id: number = 0,
  dateOfEvent: string | undefined,
  maxNumberOfGuests: number | undefined,
  enabled: boolean = true,
  startTime: string | undefined,
  endTime: string | undefined,
  imageUrl: string | undefined,
  description: string | undefined,
  title: string | undefined,
  comments: Comment[] | undefined,
  reviews: Review[] | undefined,
  games: Game[] | undefined,
  guests: User[] | undefined,
  eventTags: Eventtag[] | undefined,
  address: Address = new Address(),
  host: User = new User(),
  ) {
    this.id = id;
    this.dateOfEvent = dateOfEvent;
    this.maxNumberOfGuests = maxNumberOfGuests;
    this.enabled = enabled;
    this.startTime = startTime;
    this.endTime = endTime;
    this.imageUrl = imageUrl;
    this.description = description;
    this.title = title;
    this.comments = comments;
    this.reviews = reviews;
    this.games = games;
    this.guests = guests;
    this.eventTags = eventTags;
    this.address = address;
    this.host = host;
  }

}

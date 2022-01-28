import { Address } from "./address";
import { Gameevent } from "./gameevent";
import { Game } from "./game";
import { Review } from "./review";
import { Comment } from "./comment";

export class User {
  id: number;
  username: string;
  password: string;
  enabled: boolean;
  role: string | undefined;
  firstName: string | undefined;
  lastName: string | undefined;
  email: string | undefined;
  profilePictureUrl: string | undefined;
  addresses: Address[] | undefined;
  homeAddress: Address | undefined;
  gameEvents: Gameevent[] | undefined;
  hostedGameEvents: Gameevent[] | undefined;
  games: Game[] | undefined;
  comments: Comment[] | undefined;
  reviews: Review[] | undefined;
  friends: User[] | undefined;
  biography: string | undefined;


  constructor(
    id: number = 0,
    username: string = '',
    password: string = '',
    enabled: boolean = true,
    role?: string | undefined,
    firstName?: string | undefined,
    lastName?: string | undefined,
    email?: string | undefined,
    profilePictureUrl?: string | undefined,
    addresses?: Address[] | undefined,
    homeAddress?: Address | undefined,
    gameEvents?: Gameevent[] | undefined,
    hostedGameEvents?: Gameevent[] | undefined,
    games?: Game[] | undefined,
    comments?: Comment[] | undefined,
    reviews?: Review[] | undefined,
    friends?: User[] | undefined,
    biography: string = '') {
      this.id = id;
      this.username = username;
      this.password = password;
      this.enabled = enabled;
      this.role = role;
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.profilePictureUrl = profilePictureUrl;
      this.addresses = addresses;
      this.homeAddress = homeAddress;
      this.gameEvents = gameEvents;
      this.hostedGameEvents = hostedGameEvents;
      this.games = games;
      this.comments = comments;
      this.reviews = reviews;
      this.friends = friends;
      this.biography = biography;

  }



}

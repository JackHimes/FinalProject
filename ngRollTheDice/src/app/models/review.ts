import { Gameevent } from "./gameevent";
import { User } from "./user";

export class Review {
  id: number;
  message: string | undefined;
  reviewDate: string | undefined;
  rating: number | undefined;
  gameEvent: Gameevent | undefined;
  user: User | undefined;

  constructor(
    id: number = 0,
    message?: string | undefined,
    reviewDate?: string | undefined,
    rating?: number | undefined,
    gameEvent?: Gameevent | undefined,
    user?: User | undefined
  ) {
  this.id = id;
  this.message = message;
  this.reviewDate = reviewDate;
  this.rating = rating;
  this.gameEvent = gameEvent;
  this.user = user;
  }
}

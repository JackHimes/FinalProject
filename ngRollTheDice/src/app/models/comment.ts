import { Gameevent } from "./gameevent";
import { User } from "./user";

export class Comment {

  id: number | undefined;
  message: string | undefined;
  postDate: Date | undefined;
  user: User | undefined;
  gameEvent: Gameevent | undefined;

  constructor(id?: number, message?: string, postDate?: Date, user?: User, gameEvent?: Gameevent) {
    this.id = id;
    this.message = message;
    this.postDate = postDate;
    this.user = user;
    this.gameEvent = gameEvent;
  }

}

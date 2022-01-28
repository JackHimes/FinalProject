import { Gameevent } from "./gameevent";
import { User } from "./user";

export class Eventtag {
    id: number | undefined;
    name : string | undefined;
    description: string | undefined;
    gameEvents: Gameevent [] | undefined;
    user: User | undefined;

    constructor(id?: number, name? : string, description?: string, gameEvents?: Gameevent [], user?: User){
        this.id = id;
        this.name = name;
        this.description = description;
        this.gameEvents = gameEvents;
        this.user = user;
    }
}

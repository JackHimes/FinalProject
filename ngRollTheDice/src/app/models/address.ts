import { User } from "./user";

export class Address {

  id: number | undefined;
  address: string | undefined;
  city: string | undefined;
  state: string | undefined;
  postalCode: string | undefined
  users: User [] | undefined;


  constructor(id?: number, address?: string, city?: string, state?: string, postalCode?: string, users?: User []) {
    this.id = id;
    this.address = address;
    this.city = city;
    this.state = state;
    this.postalCode = postalCode;
    this.users = users;
  }
}

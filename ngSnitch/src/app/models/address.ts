export class Address {
  id: number;
  pinName: string;
  street: string;
  city: string;
  state: string;
  zip: number;

  constructor(
    id?: number,
    pinName?: string,
    street?: string,
    city?: string,
    state?: string,
    zip?: number) {

      this.id = id;
      this.pinName = pinName;
      this.street = street;
      this.city = city;
      this.state = state;
      this.zip = zip;
  }
}

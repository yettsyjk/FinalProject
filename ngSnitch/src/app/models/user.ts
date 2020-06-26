export class User {
  id: number;
  username: string;
  email: string;
  password: string;
  firstName: string;
  lastName: string;
  role: string; // enum in entity??? string?
  enabled: boolean;
  pictureUrl: string;
  createDate: string;

  constructor(
    id?: number,
    username?: string,
    email?: string,
    password?: string,
    firstName?: string,
    lastName?: string,
    role?: string, // enum in entity??? string?
    enabled?: boolean,
    pictureUrl?: string,
    createDate?: string) {

  this.id = id;
  this.username = username;
  this.email = email;
  this.password = password;
  this.firstName = firstName;
  this.lastName = lastName;
  this.role = role; // enum in entity??? string?
  this.enabled = enabled;
  this.pictureUrl = pictureUrl;
  this.createDate = createDate;
  }
}

import { Snitch } from './snitch';
import { User } from './user';
import { config } from 'rxjs';

export class Comment {
  id: number;
  content: string;
  createDate: string;
  snitch: Snitch;
  user: User;

  constructor(
    id?: number,
    content?: string,
    createDate?: string,
    snitch?: Snitch,
    user?: User) {

      this.id = id;
      this.content = content;
      this.createDate = createDate;
      this.snitch = snitch;
      this.user = user;
    }
}

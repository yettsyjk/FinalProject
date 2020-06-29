import { User } from './user';
import { Snitch } from './snitch';

export class SnitchVote {
  vote: boolean;
  createTime: string;
  note: string;
  snitch: Snitch;
  user: User;

  constructor(
    vote?: boolean,
    createTime?: string,
    note?: string,
    snitch?: Snitch,
    user?: User) {

      this.vote = vote;
      this.createTime = createTime;
      this.note = note;
      this.snitch = snitch;
      this.user = user;
    }
}


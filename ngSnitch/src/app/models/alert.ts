import { User } from './user';
import { Category } from './category';

export class Alert {
  id: number;
  subject: string;
  content: string;
  createdAt: string;
  expired: boolean;
  user: User;
  category: Category;

  constructor(
    id?: number,
    subject?: string,
    content?: string,
    createdAt?: string,
    expired?: boolean,
    user?: User,
    category?: Category) {

      this.id = id;
      this.subject = subject;
      this.content = content;
      this.createdAt = createdAt;
      this.expired = expired;
      this.user = user;
      this.category = category;
  }
}

import { User } from './user';
import { Category } from './category';
import { SnitchVote } from './snitch-vote';
import { Address } from './address';
import { Comment } from './comment';


export class Snitch {
  id: number;
  title: string;
  description: string;
  createDate: string;
  imgUrl: string;
  enabled: boolean;
  resolved: boolean;
  resolutionDate: string;
  resolution: string;
  user: User;
  votes: SnitchVote[];
  address: Address;
  category: Category;
  comments: Comment[];

  constructor(
    id?: number,
    title?: string,
    description?: string,
    createDate?: string,
    imgUrl?: string,
    enabled?: boolean,
    resolved?: boolean,
    resolutionDate?: string,
    resolution?: string,
    user?: User,
    votes?: SnitchVote[],
    address?: Address,
    category?: Category,
    comments?: Comment[]) {

      this.id = id;
      this.title = title;
      this.description = description;
      this.createDate = createDate;
      this.imgUrl = imgUrl;
      this.enabled = enabled;
      this.resolved = resolved;
      this.resolutionDate = resolutionDate;
      this.resolution = resolution;
      this.user = user;
      this.votes =  votes;
      this.address =  address;
      this.category =  category;
      this.comments =  comments;
    }
  }

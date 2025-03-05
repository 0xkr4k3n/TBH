import {CategoryInterface} from './category.interface';

export interface ChallengeInterface{
  id: number
  name : string
  description: string
  difficulty: String
  createdAt: Date;
  solves: number;
  category: CategoryInterface,
  points: number,
  solved:boolean
}

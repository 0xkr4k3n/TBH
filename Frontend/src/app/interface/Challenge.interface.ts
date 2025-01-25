export interface ChallengeInterface{
  id?: number
  name : string
  description: string
  difficulty: String
  createdAt: Date;
  solves: number;
  category?: string,
  points: number,
  solved:boolean
}

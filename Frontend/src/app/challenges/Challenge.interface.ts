export interface ChallengeInterface{
  id?: number
  name : string
  description: string
  difficulty: String
  solves: number
  createdAt: Date;
  solved: boolean;
  solvedBy: number;
  category?: string,
  points?: number
}

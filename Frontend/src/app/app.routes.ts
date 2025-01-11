import { Routes } from '@angular/router';
import {ChallengesComponent} from './challenges/challenges.component';
import {ProfileComponent} from './profile/profile.component';
import {ScoreboardComponent} from './scoreboard/scoreboard.component';

export const routes: Routes = [
  { path: 'challenges',component: ChallengesComponent },
  { path: 'profile',component: ProfileComponent },
  { path: 'scoreboard',component: ScoreboardComponent },
];

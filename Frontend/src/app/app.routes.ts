import { Routes } from '@angular/router';
import {ChallengesComponent} from './challenges/challenges.component';
import {ProfileComponent} from './profile/profile.component';
import {ScoreboardComponent} from './scoreboard/scoreboard.component';
import {HomeComponent} from './home/home.component';

export const routes: Routes = [
  { path: '',redirectTo: "/home",pathMatch:"full" },
  { path: 'home',component: HomeComponent },
  { path: 'challenges',component: ChallengesComponent },
  { path: 'profile',component: ProfileComponent },
  { path: 'scoreboard',component: ScoreboardComponent },
];

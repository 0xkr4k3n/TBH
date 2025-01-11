import {Component, input} from '@angular/core';
import {FormsModule} from '@angular/forms';
interface Achievement {
  name: string;
  description: string;
  unlockedAt: string;
  icon: string;
}

interface SolvedChallenge {
  name: string;
  category: string;
  points: number;
  solvedAt: string;
}
@Component({
  selector: 'app-profile',
  imports: [
    FormsModule
  ],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})

export class ProfileComponent {
  userProfile = {
    username: 'CyberPhr3ak',
    email: 'cyber@hack.er',
    country: 'usa',
    rank: 42,
    points: 1337,
    solvedChallenges: 13
  };

  recentSolves: SolvedChallenge[] = [
    {name: 'Buffer Overflow 101', category: 'Binary', points: 100, solvedAt: '2 hours ago'},
    {name: 'SQL Injection Basics', category: 'Web', points: 150, solvedAt: '5 hours ago'},
    {name: 'Reverse Me', category: 'RE', points: 200, solvedAt: '1 day ago'}
  ];

  achievements: Achievement[] = [
    {
      name: 'First Blood',
      description: 'First to solve a challenge',
      unlockedAt: '2023-12-01',
      icon: '🩸'
    },
    {
      name: 'Web Warrior',
      description: 'Solved 5 web challenges',
      unlockedAt: '2023-12-05',
      icon: '🌐'
    },
    {
      name: 'Binary Beast',
      description: 'Solved a hard binary challenge',
      unlockedAt: '2023-12-10',
      icon: '💻'
    }
  ];
}

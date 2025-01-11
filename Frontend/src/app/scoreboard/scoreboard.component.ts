import { Component } from '@angular/core';
import {NgFor} from '@angular/common';
interface TeamScore {
  rank: number;
  name: string;
  points: number;
  solvedChallenges: number;
  lastSolve: string;
  country: string;
}

@Component({
  selector: 'app-scoreboard',
  imports: [NgFor],
  templateUrl: './scoreboard.component.html',
  styleUrl: './scoreboard.component.css'
})
export class ScoreboardComponent {
  teams: TeamScore[] = [
    { "rank": 1, "name": "yvsei", "points": 4500, "solvedChallenges": 15, "lastSolve": "2 mins ago", "country": "🇺🇸 USA" },
    { "rank": 2, "name": "kraken", "points": 4200, "solvedChallenges": 14, "lastSolve": "5 mins ago", "country": "🇬🇧 UK" },
    { "rank": 3, "name": "NinjaV0id", "points": 3800, "solvedChallenges": 13, "lastSolve": "15 mins ago", "country": "🇯🇵 Japan" },
    { "rank": 4, "name": "HexWraith", "points": 3500, "solvedChallenges": 12, "lastSolve": "20 mins ago", "country": "🇩🇪 Germany" },
    { "rank": 5, "name": "DataDaemon", "points": 3200, "solvedChallenges": 11, "lastSolve": "25 mins ago", "country": "🇨🇦 Canada" },
    { "rank": 6, "name": "NullSpectre", "points": 3000, "solvedChallenges": 10, "lastSolve": "30 mins ago", "country": "🇫🇷 France" },
    { "rank": 7, "name": "OverFlowQueen", "points": 2800, "solvedChallenges": 9, "lastSolve": "45 mins ago", "country": "🇮🇳 India" },
    { "rank": 8, "name": "ShellPhantom", "points": 2500, "solvedChallenges": 8, "lastSolve": "1 hour ago", "country": "🇧🇷 Brazil" },
    { "rank": 9, "name": "RootReaper", "points": 2200, "solvedChallenges": 7, "lastSolve": "1.5 hours ago", "country": "🇷🇺 Russia" },
    { "rank": 10, "name": "PacketSniper", "points": 2000, "solvedChallenges": 6, "lastSolve": "2 hours ago", "country": "🇨🇳 China" }
  ];

  get totalPoints(): number {
    return this.teams.reduce((sum, team) => sum + team.points, 0);
  }

  lastUpdate = new Date().toLocaleTimeString();
}

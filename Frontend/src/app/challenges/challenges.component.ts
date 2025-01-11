import { Component } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgFor} from '@angular/common';
interface Challenge {
  id: number;
  name: string;
  category: string;
  difficulty: string;
  points: number;
  solved: boolean;
  solvedBy: number;
}
@Component({
  selector: 'app-challenges',
  templateUrl: './challenges.component.html',
  imports: [
    FormsModule,NgFor
  ],
  styleUrl: './challenges.component.css'
})
export class ChallengesComponent {
  challenges: Challenge[] = [
    { "id": 1, "name": "SQL Injection Basics", "category": "Web", "difficulty": "Easy", "points": 100, "solved": true, "solvedBy": 89 },
    { "id": 2, "name": "Advanced XSS", "category": "Web", "difficulty": "Hard", "points": 400, "solved": false, "solvedBy": 12 },
    { "id": 3, "name": "Session Hijacking Practice", "category": "Web", "difficulty": "Medium", "points": 200, "solved": false, "solvedBy": 37 },
    { "id": 4, "name": "CSRF Challenge", "category": "Web", "difficulty": "Medium", "points": 250, "solved": false, "solvedBy": 21 },
    { "id": 7, "name": "Path Traversal Exploit", "category": "Web", "difficulty": "Medium", "points": 220, "solved": false, "solvedBy": 31 },
    { "id": 8, "name": "Clickjacking Defense", "category": "Web", "difficulty": "Easy", "points": 100, "solved": true, "solvedBy": 74 },
    { "id": 9, "name": "File Inclusion Vulnerability", "category": "Web", "difficulty": "Hard", "points": 350, "solved": false, "solvedBy": 18 },
    { "id": 10, "name": "HTTP Response Splitting", "category": "Web", "difficulty": "Medium", "points": 250, "solved": false, "solvedBy": 16 },
    { "id": 11, "name": "Security Misconfiguration Lab", "category": "Web", "difficulty": "Easy", "points": 120, "solved": true, "solvedBy": 88 },
    { "id": 12, "name": "Broken Authentication Flow", "category": "Web", "difficulty": "Medium", "points": 210, "solved": false, "solvedBy": 26 },
    { "id": 13, "name": "Sensitive Data Exposure Test", "category": "Web", "difficulty": "Hard", "points": 400, "solved": false, "solvedBy": 10 },
    { "id": 14, "name": "XML External Entities (XXE)", "category": "Web", "difficulty": "Medium", "points": 270, "solved": false, "solvedBy": 22 },
    { "id": 15, "name": "Cross-Site Script Inclusion (XSSI)", "category": "Web", "difficulty": "Medium", "points": 230, "solved": false, "solvedBy": 29 },
    { "id": 16, "name": "Insecure Deserialization Lab", "category": "Web", "difficulty": "Hard", "points": 450, "solved": false, "solvedBy": 8 },
    { "id": 17, "name": "Open Redirects Practice", "category": "Web", "difficulty": "Easy", "points": 150, "solved": true, "solvedBy": 55 },
    { "id": 18, "name": "API Security Breach Simulation", "category": "Web", "difficulty": "Hard", "points": 500, "solved": false, "solvedBy": 5 },
    { "id": 19, "name": "Web Application Firewall (WAF) Bypass", "category": "Web", "difficulty": "Hard", "points": 400, "solved": false, "solvedBy": 12 },
    { "id": 20, "name": "Subdomain Takeover Exploit", "category": "Web", "difficulty": "Medium", "points": 280, "solved": false, "solvedBy": 20 },
    { "id": 21, "name": "Improper Access Control Test", "category": "Web", "difficulty": "Medium", "points": 300, "solved": false, "solvedBy": 19 },
    { "id": 22, "name": "Web Cache Poisoning", "category": "Web", "difficulty": "Hard", "points": 400, "solved": false, "solvedBy": 7 }
  ]


  categories = ['Binary Exploitation', 'Web', 'Reverse Engineering', 'Cryptography', 'Networking', 'Forensics'];
  difficulties = ['Easy', 'Medium', 'Hard'];

  selectedCategory = '';
  selectedDifficulty = '';
  selectedStatus = '';


  get filteredChallenges(): Challenge[] {
    return this.challenges.filter(challenge => {
      const categoryMatch = !this.selectedCategory || challenge.category === this.selectedCategory;
      const difficultyMatch = !this.selectedDifficulty || challenge.difficulty === this.selectedDifficulty;
      const statusMatch = !this.selectedStatus ||
        (this.selectedStatus === 'solved' && challenge.solved) ||
        (this.selectedStatus === 'unsolved' && !challenge.solved);

      return categoryMatch && difficultyMatch && statusMatch;
    });
  }

  get solvedCount(): number {
    return this.challenges.filter(c => c.solved).length;
  }

  get availablePoints(): number {
    return this.challenges.reduce((sum, c) => sum + (c.solved ? 0 : c.points), 0);
  }


}

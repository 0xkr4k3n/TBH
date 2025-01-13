import {Component, OnInit} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgFor} from '@angular/common';
import {ChallengesService} from './challenges.service';
import {ChallengeInterface} from './Challenge.interface';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Component ({
  selector: 'app-challenges',
  templateUrl: './challenges.component.html',
  imports: [
    FormsModule,NgFor
  ],
  styleUrl: './challenges.component.css'
})
export class ChallengesComponent implements OnInit {
  challenges: ChallengeInterface[]=[];
  constructor(private readonly challengeService: ChallengesService) {}
  ngOnInit(): void {
     this.challengeService.getAllChallenges().subscribe((response: ChallengeInterface[])=>{
       console.log(response);
       this.challenges=response;
     })
  }

  categories = [
    "Web",
    "Network",
    "Reverse Engineering",
    "Crypto",
    "Forensics",
    "Mobile",
    "OSINT",
    "Cloud",
    "Application"
  ];
  difficulties = ['Easy', 'Medium', 'Hard'];

  selectedCategory = '';
  selectedDifficulty = '';
  selectedStatus = '';


  get filteredChallenges(): ChallengeInterface[] {
    return this.challenges.filter(challenge => {
      const categoryMatch = !this.selectedCategory || challenge.category === this.selectedCategory;
      const difficultyMatch = !this.selectedDifficulty || challenge.difficulty === this.selectedDifficulty;
      const statusMatch = !this.selectedStatus ||
        (this.selectedStatus === 'solved' && challenge.solves) ||
        (this.selectedStatus === 'unsolved' && !challenge.solves);

      return categoryMatch && difficultyMatch && statusMatch;
    });
  }

  get solvedCount(): number {
    return this.challenges.filter(c => c.solves).length;
  }

  get availablePoints(): number {
    // @ts-ignore
    return this.challenges.reduce((sum, c) => sum + (c.solved ? 0 : c.points), 0);
  }





}

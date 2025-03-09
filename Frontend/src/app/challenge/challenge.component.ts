import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ChallengesService } from '../service/challenges.service';
import { ChallengeInterface } from '../interface/Challenge.interface';
import { HttpClientModule } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';

interface ChallengeHistoryEntry {
  id: number;
  user: string;
  points: number;
}

@Component({
  selector: 'app-challenge',
  templateUrl: './challenge.component.html',
  styleUrls: ['./challenge.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule]
})
export class ChallengeComponent implements OnInit, OnDestroy {
  challengeStarted = false;
  timeRemaining = 3600; // 1 hour in seconds
  flagSubmitted = '';
  challengeOutput = '';
  private timerInterval: any;
  challengeId = 0;
  url = "";

  // Challenge data from service
  challenge: ChallengeInterface | null = null;

  constructor(private challengesService: ChallengesService,private route: ActivatedRoute,
              private router: Router,
              private ChallengesService: ChallengesService) {}

  ngOnInit() {

    this.route.queryParams.subscribe((params) => {
      this.challengeId = params['id'] ? +params['id'] : 0;

      if (this.challengeId) {
        this.loadChallengeData(this.challengeId);
      } else {
        this.router.navigate(['/']);
      }
    });
  }

  loadChallengeData(id: number) {
    this.challengesService.getChallengeById(id).subscribe({
      next: (challengeData) => {
        this.challenge = challengeData;
        // You might want to populate additional fields from the service data
      },
      error: (error) => {
        console.error('Error loading challenge data', error);
        this.challengeOutput = 'Failed to load challenge data';
      }
    });
  }

  startChallenge() {
    console.log("entered startchallenge")
    this.ChallengesService.runChallenge(this.challengeId).subscribe({
      next: (response: any) => {
        this.url = response;

        console.log(response)
        console.log(this.url)
        this.challengeStarted = true;
        this.startTimer();
      },
      error: (err) => {
        console.error('Error running challenge', err);
        // Handle the error appropriately
      }
    });
  }

  extendTime() {
    this.timeRemaining += 3600; // Add 1 hour
  }

  stopChallenge() {
    this.ChallengesService.stopChallenge(this.challengeId).subscribe({
      next: (response: any) => {
        this.challengeStarted = false;
        this.timeRemaining = 3600;
        this.clearTimer();
      },
      error: (err) => {
        console.error('Error running challenge', err);

      }
    });
  }

  submitFlag() {
    // Placeholder for flag submission logic
    // You would typically call a service method to verify the flag
    console.log('Flag submitted:', this.flagSubmitted);
    this.challengeOutput = `Flag verification in progress: ${this.flagSubmitted}`;


  }

  formatTime(seconds: number): string {
    const hours = Math.floor(seconds / 3600);
    const minutes = Math.floor((seconds % 3600) / 60);
    const secs = seconds % 60;
    return `${this.pad(hours)}:${this.pad(minutes)}:${this.pad(secs)}`;
  }

  private pad(num: number): string {
    return num.toString().padStart(2, '0');
  }

  private startTimer() {
    this.clearTimer();
    this.timerInterval = setInterval(() => {
      if (this.timeRemaining > 0) {
        this.timeRemaining--;
      } else {
        this.stopChallenge();
      }
    }, 1000);
  }

  private clearTimer() {
    if (this.timerInterval) {
      clearInterval(this.timerInterval);
    }
  }

  ngOnDestroy() {
    this.clearTimer();
  }
}

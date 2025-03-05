import {Component, OnInit} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgFor} from '@angular/common';
import {ChallengesService} from '../service/challenges.service';
import {ChallengeInterface} from '../interface/Challenge.interface';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {CategoriesService} from '../service/categories.service';
import {CategoryInterface} from '../interface/category.interface';
import { Router } from '@angular/router';

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
  categories: CategoryInterface[]= [];
  constructor(private readonly challengeService: ChallengesService,
              private  readonly categoriesService: CategoriesService,
              private router: Router) {}
  ngOnInit(): void {
     this.challengeService.getAllChallenges().subscribe((response: ChallengeInterface[])=>{
       console.log(response);
       this.challenges=response;
     })

    this.categoriesService.getAllCategories().subscribe((response:  CategoryInterface[])=>{
      console.log(response);
      this.categories=response;
    })
  }

  difficulties = ['Easy', 'Medium', 'Hard'];

  selectedCategory!: CategoryInterface;
  selectedDifficulty = '';
  selectedStatus = '';


  get filteredChallenges(): ChallengeInterface[] {
    return this.challenges.filter(challenge => {
      const categoryMatch = !this.selectedCategory || challenge.category.id ==- this.selectedCategory.id;
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

  loadChallenges(): void{
    this.categoriesService.getChallengesByCategory(this.selectedCategory.id).subscribe((response: ChallengeInterface[])=>{
      console.log(response);
      this.challenges=response;
    })
  }

  navigateToChallenge(id: number | undefined) {
    console.log("id of chall " + id)
    this.router.navigate(['/challenge'], { queryParams: { id: id } });
  }


}

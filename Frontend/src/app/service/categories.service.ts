import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment.development';
import {Observable} from 'rxjs';
import {ChallengeInterface} from '../interface/Challenge.interface';
import {HttpClient} from '@angular/common/http';
import {CategoryInterface} from '../interface/category.interface';

@Injectable({
  providedIn: 'root'
})
export class CategoriesService {
  private readonly API_ROUTE = environment.apiBaseUrl+"api/categories";

  constructor(private readonly httpClient : HttpClient) {
  }

  getAllCategories() : Observable<CategoryInterface[]>{
    return this.httpClient.get<CategoryInterface[]>(this.API_ROUTE);
  }
  getChallengesByCategory(categoryId: Number): Observable<ChallengeInterface[]> {
    if (categoryId === null) {
      return this.httpClient.get<any[]>(this.API_ROUTE); // Fetch all challenges
    }
    return this.httpClient.get<ChallengeInterface[]>(`${this.API_ROUTE}/${categoryId}/challenges`);
  }
}

import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment.development';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ChallengeInterface} from './Challenge.interface';

@Injectable({
  providedIn: 'root'
})
export class ChallengesService {
  private readonly API_ROUTE = environment.apiBaseUrl+"api/challenges";

  constructor(private readonly httpClient : HttpClient) {

  }
  getAllChallenges() : Observable<ChallengeInterface[]>{
    return this.httpClient.get<ChallengeInterface[]>(this.API_ROUTE);
  }
  getChallengeById(id: number): Observable<ChallengeInterface>{
    return this.httpClient.get<ChallengeInterface>(this.API_ROUTE+`/${id}`)
  }
  createChallenge(challenge :ChallengeInterface){
    return this.httpClient.post(this.API_ROUTE, challenge)
  }
}

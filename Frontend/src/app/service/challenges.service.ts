import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment.development';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ChallengeInterface} from '../interface/Challenge.interface';

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
  runChallenge(challengeId : number){
    console.log("running challenge" + challengeId)
    return this.httpClient.get(this.API_ROUTE + `/run/${challengeId}` + '?userId=1', {
        responseType: 'text' // Specify that the response is plain text
      });
  }
  stopChallenge(challengeId : number){
    return this.httpClient.get(this.API_ROUTE + `/stop/${challengeId}` + '?userId=1', {
      responseType: 'text' // Specify that the response is plain text
    });
  }
}

import { Injectable } from '@angular/core';
import { Score } from '../models/score';

@Injectable({
  providedIn: 'root'
})
export class ScoreService {

  constructor() {

    this.scores.sort((score1:Score,score2:Score)=>{
      if(score1.points< score2.points){
        return 1;
      }
      if(score1.points> score2.points){
        return -1;
      }
      return 0;
    }); 

   }

  description:string = "Hi I am a value in the score service"

  readonly scores:Array<Score> =[
    new Score("ACR",1000), 
    new Score("RLO",100),
    new Score("EF",235)
  ]

  addScore(score:Score):void{
    this.scores.push(score);

    this.scores.sort((score1:Score,score2:Score)=>{
      if(score1.points< score2.points){
        return 1;
      }
      if(score1.points> score2.points){
        return -1;
      }
      return 0;
    }); 
    // takes in a callback function
    // same logic as a comparator
  }

}

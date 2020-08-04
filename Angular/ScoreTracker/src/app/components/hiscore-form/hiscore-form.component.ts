import { Component, OnInit } from '@angular/core';
import { Score } from 'src/app/models/score';
import { ScoreService } from 'src/app/services/score.service';

@Component({
  selector: 'app-hiscore-form',
  templateUrl: './hiscore-form.component.html',
  styleUrls: ['./hiscore-form.component.css']
})
export class HiscoreFormComponent implements OnInit {

  // this will inject our score service singleton into our
  // component
  constructor(private scoreService:ScoreService) { }

  intials:string = "";
  points:number = 0;

  ngOnInit(): void {
  }

  submitScore(){
    const score:Score = new Score(this.intials,this.points);

    this.intials = "";
    this.points =0;
    
    this.scoreService.addScore(score);
    
  }

}

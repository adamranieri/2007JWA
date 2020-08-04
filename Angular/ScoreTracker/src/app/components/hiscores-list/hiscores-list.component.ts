import { Component, OnInit } from '@angular/core';
import {Score} from '../../models/score'
import { ScoreService } from 'src/app/services/score.service';

@Component({
  selector: 'app-hiscores-list',
  templateUrl: './hiscores-list.component.html',
  styleUrls: ['./hiscores-list.component.css']
})
export class HiscoresListComponent implements OnInit {

  constructor(private scoreService:ScoreService) { }

  showScores:boolean = false;
  btnText:string = "Show Scores"

  games:Array<string> = ["Pacman","Dig Dig","Ms. Pacman","Galaga"]
  scores:Array<Score> = this.scoreService.scores;

  ngOnInit(): void {
     
  }

  //rather than a function that
  // would get the element
  // our function will just change the ts value

  revealScores() {
    this.showScores = !this.showScores;

    if(this.showScores === true){
      this.btnText = "Hide Scores"
    }else{
      this.btnText = "Show Scores"
    }
  }

  alter(){
    this.scoreService.description = "I have been altered"
  }

}

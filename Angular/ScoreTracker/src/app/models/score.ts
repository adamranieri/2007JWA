
// a model class is just to store information
// you do not have to register it anywhere
export class Score{

    initials:string;
    points:number;

    constructor(initials:string,points:number){
        this.initials = initials;
        this.points = points;
    }
}
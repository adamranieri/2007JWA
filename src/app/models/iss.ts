export class ISS{
    
    iss_position:Array<number>; 
    message:string;
    timestamp:number;

    constructor(iss_position:Array<number>, message:string,timestamp:number){
        this.iss_position=iss_position;
        this.message=message;
        this.timestamp=timestamp;
    }

}
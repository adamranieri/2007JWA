let fighters = ['Zelda', 'Mario', 'Samus', 'Lucina','Peach', 'Dark Pit'];

// console.table(fighters)


let introGreeting = (fighter)=>{
    return fighter + " has joined the fight!!!!";
}
// you can pass in a callback function that will return a new array with all the elements altered in some way
let intros = fighters.map(introGreeting);

//console.log(intros)


class Trainer{

    name;
    age;
    location;

    constructor(name,age,location){
        this.name = name;
        this.age = age;
        this.location = location;
    }
}

let adam = new Trainer('Adam',25,'WVU');
let ryan = new Trainer('Ryan',26,'WVU');
let william = new Trainer('William',26,'UTA');
let fred = new Trainer('Fred',35,'UTA');
let trainers = [adam,ryan,william,fred];
console.table(trainers)

let wvuTrainerFunction = (trainer) =>{
    if(trainer.location === 'WVU'){
        return true;
    }else{
        return false;
    }
}

let wvuTrainers = trainers.filter(wvuTrainerFunction);
console.table(wvuTrainers);
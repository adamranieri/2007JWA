// ways to create an object
// object literals
let adam = {name:"Adam Ranieri", age:25};

// function that creates an object literal and returns it
function createTrainer(nameParam, ageParam){

    return {name:nameParam,age:ageParam}
}

let richard = createTrainer('Richard',28);

// constructor functions
function Trainer(name, age){
    this.name = name;
    this.age = age;
}

let sierra = new Trainer('Sierra', 24);
sierra.lastname = 'Nicholes';

console.log(sierra);
console.log(richard);

// In ES6 (ECMAScript 6) ECMA (European Computer Manufacturer Association ) is the organization in charge of JS

class Person{

    name ; // instance variables
    age ;

   constructor(name = "McDefault",age= 0){
    this.name = name;
    this.age = age;
   }

   describeSelf(){
       console.log("Hello my name is " + this.name + "I am this old " +this.age)
   }

}

let steve = new Person('Steven',30);
let mike = new Person('Mike',45);
steve.describeSelf();
mike.describeSelf();


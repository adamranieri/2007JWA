// ways to create an object

// object literals
let adam = {
    name: "Adam Ranieri",
    age:25
};

// function makes object literal and return it
function createTrainer(nameParam, ageParam) {
    return {name:nameParam, age:ageParam};
}

let richard = createTrainer("Richard", 28);
console.log(richard);

// constructor functions
function Trainer(name, age) {
    this.name = name;
    this.age = age;
    this.describeSelf =  function() {
        console.log("Hello my name is " + this.name + ". I am " + this.age + " years old.");
    }
}

let sierra = new Trainer("Sierra", 24);
sierra.lastname = "Nicholes";
console.log(sierra);
sierra.describeSelf();

// In ES6 (ECMAScript 6) ECMA is the organization in charge of JS you can do classes
class Person {
    name; // instance variables
    age = 0; // set default values here

    constructor(name, age) {
        this.name = name;
        this.age = age;
    }

    describeSelf() {
        console.log("Hello my name is " + this.name + ". I am " + this.age + " years old.");
    }
}

let steve = new Person("Steven", 30);
let mike = new Person("Mike", 45);
steve.describeSelf();
mike.describeSelf();
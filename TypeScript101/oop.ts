
// interaces in TS can also support instance variables
interface Teachable{

    location:string;
    teachPerson(pupil:Person):boolean;
}


// TS had classes before es6 actually made them a standard in JS
abstract class Person{

    // instance variables
    name:string;
    private age:number; // you can have access modifiers

    constructor(name:string,age:number){
        this.name = name;
        this.age = age;
    }

    describeSelf():void{
        console.log(`Hello my name is ${name}`);
    }


}

class Trainer extends Person implements Teachable{

    specialty:string;
    location: string;

    constructor(name:string,age:number,specialty:string){
        super(name,age);
        this.specialty = specialty;
    }
    
    teachPerson(pupil: Person): boolean {
        throw new Error("Method not implemented.");
    }


}

// TS has interfaces and abstract classes




let adam:Person = new Trainer("Adam",25,"Java");
let sierra:Trainer = new Trainer("Sierra",23,"Java");


sierra.describeSelf()

// the any type means that this variable or method can return anything
let something:any = 90;
something = "K";

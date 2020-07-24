// spread operator
// it allows you to take an array and break it down into individual arguments

function add(num1,num2){
    return num1 + num2;
}

let sum = add(10,50);

let nums = [90,10];

sum = add(...nums) // occationally helpful
//console.log(sum);

// just a reference so editing nums also changes num2
let nums2 = nums;

// to copy an array
let num3 = [...nums]

// null coalescing operator

// let hello = null;
// // assigns greeting the second value if hello is null or undefined
// let greeting = hello ?? "Default greeting";
// console.log(greeting)


// origin of classes

// function are object which means that functions can return functions
// this is a closure
// a function returns function
// the inner function uses a variable that was defined in the outer function
function createCustomGreeting(nameParam){

    const name = nameParam;

    return function(){
        console.log("Hello " + name);
    }

}

let adamGreeter = createCustomGreeting("Adam");// return different function objects
let mikeGreeeter = createCustomGreeting("Mike");

adamGreeter();
adamGreeter();
adamGreeter();
mikeGreeeter();
mikeGreeeter();


function carClass(){

    let amount = 0; // this will be a static variable

    // this function will be a "class"
    return function(makeParam,modelParam){

        amount++;

        return{
            make:makeParam,
            model:modelParam,
            amount:amount
        }
        
    }

}

let carConstructor = carClass();

let crosstrek = carConstructor('Subaru', 'Crosstrek');
let civic = carConstructor('Honda', 'Civic');

console.log(civic)
// originally, JS had 2 scopes, Global Scope and Function Scope
function hola() {
    name = "John"; // this variable is a global variable and accessible anywhere in the script
    console.log("Hola todos");
}
// all global variables are attached to the global object in the DOM
// the global object is window on the browser
function bonjour() {
    console.log("Bounjour " + name);
}
console.log(name);
hola();
console.log(name);
bonjour();

// Moral of the story: svoid Global Variables

function sayHello(name = "") {
    var greeting = "Hello "; // var creates a function scope variable that exists only in that function
    console.log(greeting + name);
}

sayHello("Tim");
//console.log(greeting);//greeting not defined in this scope

// When JS executes a function, BEFORE it actually executes the code, it gives all vairables memory addresses
// the default value put in that memory is Undefined
function hoisting() {
    // var does hoisting, it can use variables defined later in the program and it will be undefineds
    console.log(x);
    var x = "hello";
}

hoisting();

// DO NOT use var. Friends don't let friends use var
// USE let and const, they follow block scope and are not hoisted
function gutentag() {
    //console.log(name); // fails because lets do not get hoisted
    let name = "Hans";
}

function varProblem() {
    {
        var x= 100; // function scoped
        let y = 200; // block scoped
    }
    console.log(x);
    //console.log(y); // block scope doesn't let us do this
}

varProblem();

// const works exactly like let except you cannot change the value
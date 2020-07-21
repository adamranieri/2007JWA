// scoping in JS
// Orginally JS had two scopes
// global and function scope

// to make a variable global you don't put anything in front of it

function hola(){
    name = "John";// this variable is a global variable and accesible anywhere in the script
    console.log("Hola todos");
}
// all global variables are attached to the global object
// the global object is window on your browser

//console.log(name);
// hola();
//console.log(name);

function bonjur(){
    console.log("Bonjur" + name)
}

// Avoid gloabl variables 

function sayHello(name = ""){
    var greeting = "Hello "; // var creates a function scope variable. variable that exists only in that function
    console.log(greeting + name);
}

//sayHello("Tim");

//console.log(greeting)

// When JS executes a function BEFORE it actually executes the code it gives all variable in the code
// a default value. The default of everything JS is undefined
function hoisting(){
    console.log(x);
    var x = 100;
}

//hoisting();

// DO not use var. friends do not let friends use var

// USE let and const
// let and const follow block scope 
// they are not hoisted

function gutentag(){
    console.log(name);
    // lets do not get hoisted
    let name = "Hans";
}

function varProblem(){
    
    {
        var x = 1000; // Function scoped
        let y = 90; // block scope
    }
    console.log(x);
}


varProblem();

// 3 scopes in JS
// global
// function
// block
// JS is mostly a functional programming language in my opinion
// FUNCTIONS ARE OBJECTS!!! JUST LIKE LAMBDAS IN JAVA
// you can store a function in a variable

// just like a lambda in Java
let hello = function(){
    console.log("Hello");
};
// a function is just an object that you can invoke using ()

hello()
// just like any other object you can add properties at anytime
hello.something = 'tim';
hello.something = function(){
    console.log("I print something")
}

// arrow notation. Alternative to writing the function keyword
let hola = ()=>{
    console.log("Hola")
}

hola()

let add = (num1, num2) =>{
    return (num1 + num2);
}

let sum = add(90,5);
console.log(sum);
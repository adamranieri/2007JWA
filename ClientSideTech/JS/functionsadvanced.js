// JS is mostly a functional programming language
// this means that functions are objects just like java lambdas
// you can store a function in a variable

let hello = function() {
    console.log("hello");
}

// a function in JS is just an object that you can invoke using ()
hello; // YOU NEED THE () TO RUN THE FUNCTION (to invoke the function)
hello();
// just like other objects, you can add properties at any time
hello.something = "tim";
console.log(hello.something);

// arrow notation, alternative to the function keyword
// can't use the this variable in an arrow function
let hola = () => {
    console.log("Hola");
}

let add = (num1, num2) => {
    return num1 + num2;
}

hola();

sum = add(1, 2);
console.log(sum);

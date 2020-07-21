// JS is a loosely typed language
// Your variables type is fluid. It can be changed based on context and operation.
// This is called type coercion. JS has INCREDIBLY aggressive type coercion
// JS type coercion rules are not consistent and not transitive

// console.log("jon"=="jon"); // true
// console.log(6==6); // true
// console.log(true==false);// false

// console.log("hank"==9); // JS turns the number into a string in which case "hank" != "9"
// console.log("9"==9); // JS turns the string into a number so it doese 9 == 9; true
// console.log("10"*10 + true);
// console.log((true+true+true)*false); // turns booleans into numbers to add them together
// // true when turned into a number is 1. false when a number turns to 0
// console.log("hello"*"world");

// every value in JS has a truthy or falsy value
// inherit boolean value of that variable
// console.log(Boolean(true)) // true
// console.log(Boolean(false)) // false 
// console.log(Boolean("adam")) // true
// console.log(Boolean(50)) // true

// the falsy values are the values that are inheritly false
// the falsy values are associated with nothingness 
// console.log(Boolean(false)) 
// console.log(Boolean(null))
// console.log(Boolean(undefined))
// console.log(Boolean(0))
// console.log(Boolean("0"))
// console.log(Boolean(NaN))

console.log("0"==0);// true
// console.log(0 == false) //true
// console.log("0"== true)// false turns "0" into number 0 . 0 turns into the boolean false. then false does not equal true

// === compares both the value and the type

console.log("0"===0); // false JS will not perfrom type coercion if you use ===
// IN JS alwsys use === unless you have a really good specific reason to use ==

let result = 100/"k"; // return NaN
console.log(result);
console.log(result === result);
// NaN always returns false. Even if you comapare it with itself

console.log(typeof  result); // NaN is of type number

// null and undefined are different values in JS
// null is an explicit value that a programmer has to assign or return
// undefined is the default value of all variables in JS and default return value
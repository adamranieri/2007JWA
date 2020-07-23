// JS i sa loosely typed language
// Your variable's type is fluid, it can be changed based on context and the operation

// JS has INCREDITBLY aggressive type coercion
// JS type conversion is not consistent and not transitive

// These make sense, both sides are the same type
console.log("jon"=="jon");
console.log(6==6);
console.log(true==false);

console.log("hank"==9); //JS turns the number into a string in which case "hank != 9"
console.log("9"== 9); //JS turns the either the number or the string to the other and 9 == 9 is true
console.log("10" - 10); // turns the string into a number
console.log("10" + 10); // turns the number into a string
console.log("10" * 10 + true); // turns true into 1 and "10" into 10
console.log(true + true + true); // turns booleans into numbers to add them together
console.log("hello"*"world");

// every value in JS has a truthy or valsy value
// inherit boolean value of that variable
console.log(Boolean(true));
console.log(Boolean(false));
console.log(Boolean("Adam"));
console.log(Boolean(50));

// the falsy values in JavaScript, everything else is truthy
console.log(Boolean(false));
console.log(Boolean(null));
console.log(Boolean(undefined));
console.log(Boolean(0));
console.log(Boolean(""));
console.log(Boolean(NaN))

console.log(Boolean("false")); // the string false evaluates to true

// comparison is not transitive
console.log("0" == 0); // true
console.log(0 == false); // true
console.log(Boolean("0") == false); // false because it's not a falsy value
console.log("0" == false); //true (because it got converted into a number)

//  === compares both the value and the type
console.log("0" === 0); // false, === blocks type coercion
console.log(typeof(NaN)); // Not a Number is of type number
// in JS ALWAYS use === unless you have a REALLY GOOD, SPECIFIC reason to use ==

// null and undefined are different values in JS,
// null is an explicit value a programmer has to assign
// undefined is the default value of all JS variables

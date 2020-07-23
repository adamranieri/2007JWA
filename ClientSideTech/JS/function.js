// JS (in Adam's opinion) is a mostly functional programming language
// in pure JS you do not write classes much (since es6 you can)

function add(num1, num2) {
    return num1 + num2;
}

function minus(num1, num2) {
    return num1 - num2;
}

sum = add(90,60);
diff = minus(90,60);
sum2 = add ("90", 60);
diff2 = minus("90", 60);

console.log(sum);
console.log(sum2);
console.log(diff);
console.log(diff2);

// THIS DOES NOT WORK, you cannot overload in JS
function hello() {
    console.log("Hello Adam");
}

function hello(name) {
    console.log("Hello " + name);
}

hello(); // if you call a function with too few parameters, it passes undefined as a value
hello("Adam");

// you cannot overload functions in JS (dynamically typed)

// Newest version of JS has default 
// 
function helloDefault(name = "McDefault") {
    console.log("Hello " + name);
}

helloDefault();
helloDefault("Adam");

function multiply(num1 = 0, num2 = 0) {
    return num1*num2;
}

result = multiply(23, 45);
// JS is (in my opinion) a mostly functional programming language
// In pure JS you do not write classes that much (since es6 you can create classes)

// JS being loosely typed you do not have return types and parmeters can be whatever
// defines a function
function add(num1, num2){
    return num1 + num2;
}

function minus(num1,num2){
    return num1 - num2;
}

// sum = add("40",60);
// diff = minus("40",60);
// console.log(sum);
// console.log(diff);

// you cannot overload functions in JS

function hello(){
    console.log("Hello Everyone");
}

// this function will overwrite the function above it
function hello(name ="McDefault"){
    console.log("hello " + name);
}

// you can call a function without correct number of parameters

hello(); // if you call a function with too few parameters it will pass in the value as undefined
hello("Adam","Ranieri",false,true, 90)

// Newest version of JS has default parmaters
// default parameters allows your text editor to infer the type

function multiply(num1 =0,num2=0){
    return num1*num2;
}

result = multiply(90,60);

console.log(1/0);
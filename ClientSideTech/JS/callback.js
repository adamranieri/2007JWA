// A callback function is a function passed in as a parameter to another function

let add = (num1, num2) => {
    return num1 + num2;
}

let subtract = (num1, num2) => {
    return num1 - num2;
}

let multiply = (num1, num2) => {
    return num1 * num2;
}

let divide = (num1, num2) => {
    return num1 / num2;
}

// the function that takes in a callback is called a higher order function
let calculate = (num1, num2, operation) => {
    // if a function has no return, it returns undefined
    let result = operation(num1, num2);
    console.log(result);
}

calculate(75, 3, add);
calculate(75, 3, subtract);
calculate(75, 3, multiply)
calculate(75, 3, divide);
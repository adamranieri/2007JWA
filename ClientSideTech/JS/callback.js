// A callback function is a function passed in as a parameter to another function

let add = (num1,num2)=>{
    return num1 + num2;
}

let minus = (num1,num2) =>{
    return num1-num2;
}

let multiply = (num1, num2) =>{
    return num1 * num2;
}

let divide = (num1, num2) =>{
    return num1 / num2;
}
let crazyOperation = (num1, num2, num3) =>{
    return num1 / num2 * num3;
}
let j = ()=>{
    console.log("The letter J")
}
// JS only has number. It does not have int
// operation is the callback function
// The function that takes in a callback is called a hihger order function
let calculate = (num1,num2, operation) =>{
    let result = operation(num1,num2); // if a function has no return it returns undefined
    console.log(result)
}

calculate(75,3,add)
calculate(75,3,minus)
calculate(75,3,crazyOperation)
calculate(75,3,j)
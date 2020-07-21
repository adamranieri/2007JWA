// Arrays are just a linear collection of objects
// you can store anything in an array

let obj = {prop:"I am property"}
let ray = [10,true,false, "Hello",obj,null,undefined];

//console.log(ray)

// you can iterate over an array using the of syntax
// for(let element of ray){
//     console.log(element)
// }

// to add to an array
// ray.push("Adam")
// console.log(ray)
// ray.pop()// remove the last element
// console.log(ray)
// ray.shift() // remove first element
// console.log(ray)
// ray.unshift(76) // adds an element to the beginig
// ray[22]= 'tim';
// console.log(ray)

// Arrays lend themselves to callback functions

let print = (something)=>{
    console.log(typeof something)
    console.log(something);
}

// forEach is an array method that takes in a callback function
// it will invoke that function for each element in the array 
ray.forEach(print)

let nullFilter = (something)=>{
    if( something === null){
        return true;
    }else{
        return false;
    }
}

let nullRay = ray.filter(nullFilter)
console.log(nullRay)
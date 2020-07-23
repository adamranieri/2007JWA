// Arrays are just a linear collection of objects
// arrays can store anything in JS

let obj = {prop:"I am a property"}
let ray = [10, true, false, "Hello", obj, null, undefined];
console.log(ray.length); // arrays have many functions and properties themselves
console.log(ray);

// can iterate through an array with the of syntax
for (let element of ray) {
    console.log(element);
}

// to add to an array
ray.push("Adam"); // adds element to the end
ray.shift(); // removes first element
ray.pop(); // remove last element
ray.unshift(76); // adds an eleemtn to the beginning
console.log(ray);
ray[3] = "Tim";
console.log(ray[3]);

// Arrays lend themselves to callback functions
let print = (something) => {
    console.log(something);
}

print(obj);
ray.forEach(print);

let nullFilter = (something) => {
    if (something === null) {
        return true;
    } else {
        return false;
    }
}

let nullRay = ray.filter(nullFilter);
// Objects are very simple in JS
// Objects are just collections of key-value pairs

// you do not need a class to create an object

let adam = {
    name: "Adam Ranieri",
    age: 25,
    profession: "Senior Trainer",
    describeSelf: function() { // this property is a function
        console.log("hello my name is " + this.name); // can use the this keyword to use name of property in this object
    }
}

console.log(adam);
console.log(adam.name);
console.log(adam.age);
console.log(adam.profession);
console.log(JSON.stringify(adam));
adam.describeSelf();

// because objects are not bound to classes, you can edit them at any time
adam.name = "Adam Christopher Ranieri";
// you can add properties to objects at any time as well
// there are no limits on the types you can add ot an object
adam.location = "West Virginia";

let richard = {
    name: "Richard Orr",
    age: 28,
    profession: "Lead Trainer"
}

let wvu = {};
wvu.trainer1 = adam;
wvu.trainter2 = richard;
console.log(adam);
console.log(wvu);

// objects can have functions as properties

// ways to access properties
// dot operator, adam.name
// array like, adam["name"]

// object literals can be iterted over using a for in loop
for (let prop in adam) {
    console.log(prop);
}
for (let prop in adam) {
    console.log(adam[prop]);
}

// don't worry about this
adam.describeSelf.apply(richard);
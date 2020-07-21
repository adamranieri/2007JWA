// Objects are very simple in JS
// objects are just collections of key value pairs (essentially maps)

// you do not need a class to create an object

// object literal.  created an object with three properties
let adam = {name:"Adam Ranieri",
age: 25,
profession: "Senior Trainer",

describeSelf: function(){     /// this property is a function
    console.log("hello my name is " + this.name) // this keyword means use the name variable in this object
}
};
adam.describeSelf();
console.log(adam);
console.log(adam.name);

// because objects are not bound to classes you can edit them at any time 
adam.name = "Adam Christopher Ranieri";
// you can add properties to your objects whenever you want
// there is no limits on the types you can add to an object
adam.location = "West Virginia";

let richard = {
    name:"Richard Orr",
    age: 28,
    profession: "Lead Trainer" 
}
let wvu ={}
wvu.trainer1 = adam;
wvu.trainer2 = richard;

console.log(wvu);

// objects can have functions

// ways to access properties
// dot operator adam.name
// use the property name in an array like syntax
console.log(adam["name"])

// object literals can be iterated through using a for in loop
for(let prop in adam){
    console.log(adam[prop]);
}

// dont worry about this
adam.describeSelf.apply(richard);

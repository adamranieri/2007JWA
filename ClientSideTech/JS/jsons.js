// JSON JavaScript Object Notation
// String format for representing a JS object
// The (near) universal format for sending information on the internet.
// Regardless of your backend api. The front end always uses JavaScript

// this is a json. JSON IS A STRING
let kristinaJSON = '{"stuId":3,"name":"Kristina","sId":3}'
let kristina = JSON.parse(kristinaJSON) // the parse method will turn the JSON string into an object
console.log(kristina.sId)

let tebow = {
name:'Tim Tebow',
 weight:225, 
 team:"Gators",
 describeMyself: function(){
    console.log("I won the heisman trophy and then did mediocre in the NFL")
 }
};
// functions on an object do not get turned into the JSON

let tebowJSON = JSON.stringify(tebow); // stringify turns an object into a string
console.log(tebowJSON)
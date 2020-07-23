// JSON JavaScript Object Notation
// string format representing a JS object
// universal formt for sending in formation on the interet
// regardless of your backend api, the front end alwasy uses JavaScript

// this is JSON, it's just a string
let kristinaJSON = '{"stuId":3,"name":"Kristina","sId":3}'
let kristina = JSON.parse(kristinaJSON); // parse turns a stirng into an object
console.log(kristina.stuId);

let tebow = {
    name:'Tim Tebow',
    weight:225,
    team:'Gators',
    describeMyself: function() {
        console.log("I won the heisman trophy then did meiocre in the NFL.");
    }
};

// functions on an object do not get turned into the JSON because it's a behavior not a state
let tebowJSON = JSON.stringify(tebow); // stringify turns an object into a string
console.log(tebowJSON);
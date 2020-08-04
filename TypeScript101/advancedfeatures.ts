// TS being built on top of JS means that it has A LOT of flexibity and ability to do things
// Java  cannot

// You can define return types of specific values

function yesNo(value:boolean):"Yes"| "No"{
    if(value){
        return "Yes";
    }else{
        return "No";
    }
}

let outcome:string = yesNo(true);

// you can also have parameter guards
function greeter(prefix: "Mr. "| "Mrs." |"Ms.",name:string, ){
    console.log(prefix + name);
}

greeter("Mr. ", "Ranieri");

// TS also has generics

let numray:Array<number> = [4,2,12,56]
//Equivalent syntax
let stringray:string[] = ["James","Computer"];
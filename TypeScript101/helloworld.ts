
// anything you write in JS is also valid TypeScript
// you should not though
function hello(name){
    console.log("Hello" + name);
}

hello("Adam");

// You have the ability to add types to your variables and methods
function hola(name:string) : void{
    const greeting:string = "Hola "
    console.log(greeting + name)
}

hola("James");

function add(num1:number,num2:number) :number{
    return num1 + num2;
}

let result:number = add(80,45);

// TypeScript Transpiles into JS es3 
// const did not exist yet

// return types
// void
// primitives
// objects
// classes
// never a return type for a method that should never return anything
// for a never to transpiles correctly it must never finish executing succesfully
function infinitePrint(thing:string): never{

    // // infinite loop this method never returns
    // while(true){
    //     console.log("Hello")
    // }
    throw new Error("This method never returns because it always throws an error")
}

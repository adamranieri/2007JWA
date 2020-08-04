import { Component } from '@angular/core';

// creating our first component
// Every component has three parts
//1. HTML
//2. TS class (logic)
//3. decorator (information about our component)

// @ symbol in JS is called a decorator
// A decortor is actually function
@Component({
    selector: "app-intro", 
    template: `
    
    <h1>Welcome Everyone </h1>
    <p>Welcome to the WVU office. We are located in Morgantown WV. We are proud partners with
        WVU. Let's go mountaineers!!!!
    </p>
    
    `
})
export class IntroComponent {
 

}
//import {browser, element, by} from protractor; // this will give us intellisense while working with
//protractor. You have to comment it out before you actually run your code because this is not regular JS

const homePage = require('./mon-homepage'); // this will give us that homepage object


browser.ignoreSynchronization = true;
// this line tells protractor to not throw an error if you try running this code on a 
// non-angular application

// protractor tests are written using Jasmine syntax
// Jasmine is essentially JUnit but for JavaScript

// first parameter is the name of the test suite
// second parameter is a callback function with your tests

// specs automatically run in order

describe("Mon County e2e create player tests", ()=>{


    // this is a single test
    // Jasmine uses the terminology spec which is identical to test
    it("Should go to the home page", ()=>{

        browser.get('http://localhost:7000/monschoolhome.html'); // this will go to the mon school home page
        browser.sleep(2000); // how to make your code wait in milliseconds
        const title = browser.getTitle();

        //assertions in Jasmine
        // expect(somevalue).toBe(someValue)
        expect(title).toBe('Mon School');
        
    });

    // our second spec
    it("Should create a school", ()=>{
        // methods that return an element

        homePage.nameInput.sendKeys('Adam School of Automation');
        homePage.capacityInput.sendKeys('150');
        browser.sleep(2000);

        homePage.createSchoolButton.click();
        browser.sleep(2000);
  
        expect(homePage.sIdField.getText()).not.toBe('0'); // not syntax
        expect(homePage.nameField.getText()).toBe('Adam School of Automation');
        expect(homePage.capacityField.getText()).toBe('150');

    });







});
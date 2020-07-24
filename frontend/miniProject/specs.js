const homePage = require('./miniapi-homepage'); 

browser.ignoreSynchronization = true;


describe("Incredible animal page tests", ()=>{


    it("Should go to the home page", ()=>{

        browser.get('http://localhost:7000/index.html');
        browser.sleep(2000); 
        const title = browser.getTitle();

        
        expect(title).toBe('AnimalDex');
        
    });
});
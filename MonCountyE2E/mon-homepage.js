// naming convention for js files is something-something-something
// Kebab case because its like you speared the words

// Page Object Model Design pattern
// all the important interactable elements on the page are variables of a class
// reduces the verbosity and redundancy of your test. Makes them easier to read;
class MonHomePage{

    nameInput;
    capacityInput;
    createSchoolButton;
    sIdField;
    nameField;
    capacityField;

    constructor(){
        this.nameInput = element(by.id("nameInput"));
        this.capacityInput = element(by.id('capacityInput'));
        this.createSchoolButton = element(by.id('schoolCreateBtn'));
        this.sIdField = element(by.id('sIdField'));
        this.nameField = element(by.id('nameField'));
        this.capacityField = element(by.id('capacityField'));
    }

}
const homePage = new MonHomePage();
module.exports = homePage;
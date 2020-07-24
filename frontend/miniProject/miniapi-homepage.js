class AnimalHomePage{

    distanceInput;
    findAnimalsButton;
    ifAnimalCheck;
    ifPlantCheck;

    constructor(){
        this.nameInput = element(by.id("distanceInput"));
        this.findAnimalsButton = element(by.id('find-animals-btn'));
        this.ifAnimalCheck = element(by.id('if-animals'));
        this.ifPlantCheck = element(by.id('if-plants'));
    }

}
const homePage = new AnimalHomePage();
module.exports = homePage;
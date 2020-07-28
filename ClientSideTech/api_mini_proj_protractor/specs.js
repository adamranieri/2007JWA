//import {browser, element, by} from protractor;

const homePage = require('./dnd-homepage')
browser.ignoreSynchronization = true;

describe("DnD Creator Make Player Tests", () => {

    it("Should go to the home page", () => {
        browser.get('file:///C:/Users/Justin/Desktop/2007JWA/ClientSideTech/api_mini_proj/index.html');
        const title = browser.getTitle();

        expect(title).toBe('D&D Character Creator')
    });

    it("Should create a character", () => {
        browser.sleep(2000);
        homePage.raceBtn.click();
        homePage.subRaceBtn.click();
        homePage.classBtn.click();
        homePage.subClassBtn.click();
        homePage.statPickerBtn.click();
        homePage.profPickerBtn.click();

        expect(homePage.speed.getText()).toBe('30ft.');
        expect(homePage.strength.getText()).toBe('12');
        expect(homePage.dexterity.getText()).toBe('10');
        expect(homePage.constitution.getText()).toBe('10');
        expect(homePage.intelligence.getText()).toBe('10');
        expect(homePage.wisdom.getText()).toBe('10');
        expect(homePage.charisma.getText()).toBe('11');

        expect(homePage.race.getText()).toBe('Dragonborn');
        expect(homePage.charClass.getText()).toBe('Barbarian');
        expect(homePage.subClass.getText()).toBe('Berserker');
        expect(homePage.hitDie.getText()).toBe('d12');
    });


});
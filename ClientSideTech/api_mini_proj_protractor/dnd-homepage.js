const { element } = require("protractor");

class DnDHomePage {
    speed;

    strength;
    dexterity;
    constitution;
    intelligence;
    wisdom;
    charisma;

    race;
    subRace;
    charClass;
    subClass;
    hitDie;

    subRaceBtn;
    subClassBtn;
    classBtn;
    raceBtn;
    statPickerBtn;
    profPickerBtn;

    constructor() {
        this.speed = element(by.id("speed"));
        this.strength = element(by.id("str"));
        this.dexterity = element(by.id("dex"));
        this.constitution = element(by.id("con"));
        this.intelligence = element(by.id("int"));
        this.wisdom = element(by.id("wis"));
        this.charisma = element(by.id("cha"));
        this.race = element(by.id("raceTitle"));
        this.subRace = element(by.id("subRace"));
        this.charClass = element(by.id("classTitle"));
        this.subClass = element(by.id("subClass"));
        this.hitDie = element(by.id("hitDie"));
        this.raceBtn = element(by.id("raceBtn"));
        this.classBtn = element(by.id("classBtn"));
        this.subClassBtn = element(by.id("subclassBtn"));
        this.subRaceBtn = element(by.id("subraceBtn"));
        this.statPickerBtn = element(by.id("statPickerBtn"));
        this.profPickerBtn = element(by.id("profPickerBtn"));
    }
}

const homePage = new DnDHomePage();
module.exports = homePage;
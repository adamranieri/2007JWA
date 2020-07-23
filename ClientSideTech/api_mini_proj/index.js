let finalRace = "";
let finalSubrace  = "";
let finalClass = "";
let finalSubclass = "";
let stats = {};
let proficiencies = {};

function resetPage() {
    location.reload();
}
/**
 * 
 * 
 *  FUNCTIONS FOR DISPLAYING THE FINAL PAGE
 * 
 */
function displayRace(raceObj) {
    document.getElementById("raceTitle").innerHTML = raceObj.name;
    document.getElementById("speed").innerHTML = raceObj.speed + 'ft.';
    let bonuses = raceObj.ability_bonuses;
    for (let bonus of bonuses) {
        stats[bonus.name] += Number.parseInt(bonus.bonus);
    }
    document.getElementById("str").innerHTML = stats.STR;
    document.getElementById("dex").innerHTML = stats.DEX;
    document.getElementById("con").innerHTML = stats.CON;
    document.getElementById("int").innerHTML = stats.INT;
    document.getElementById("wis").innerHTML = stats.WIS;
    document.getElementById("cha").innerHTML = stats.CHA;

}

function displaySubRace(subraceObj) {
    if (subraceObj === {}) {
        document.getElementById("subRace").innerHTML = subraceObj.name;
    }
}

function displayClass(classObj) {
    document.getElementById("classTitle").innerHTML = classObj.name;
    document.getElementById("hitDie").innerHTML = 'd' + classObj.hit_die;
}

function displaySubClass(subclassObj) {
    document.getElementById("subClass").innerHTML = subclassObj.name;
}

async function displayCharacter() {
    finalSubclass = document.getElementById("subclasses").value;

    document.getElementById("optionPicker").style.display = "none";
    document.getElementById("statPicker").style.display = "none";
    document.getElementById("profPicker").style.display = "none";
    document.getElementById("characterDisplay").removeAttribute("hidden");

    let response = await fetch(`https://www.dnd5eapi.co/api/races/${finalRace}`);
    let responseObj = await response.json();
    displayRace(responseObj);
    
    if (finalSubrace != "") {
        finalSubrace = finalSubrace.toLowerCase();
        response = await fetch(`https://www.dnd5eapi.co/api/subraces/${finalSubrace}`);
        responseObj = await response.json();
        displaySubRace(responseObj);
    } else {
        displaySubRace({});
    }

    response = await fetch(`https://www.dnd5eapi.co/api/classes/${finalClass}`);
    responseObj = await response.json();
    displayClass(responseObj);

    finalSubclass = finalSubclass.toLowerCase();
    response = await fetch(`https://www.dnd5eapi.co/api/subclasses/${finalSubclass}`);
    responseObj = await response.json();
    displaySubClass(responseObj);


    /** BUILD PROFICIENCY TABLE HERE */

}


/**
 * 
 * FUNCTIONS FOR CHOOSING OPTIONS ON A CHARACTER
 * 
 * 
 */
async function pickProf() {
    const str = Number.parseInt(document.getElementById("STR").value);
    const dex = Number.parseInt(document.getElementById("DEX").value);
    const con = Number.parseInt(document.getElementById("CON").value);
    const int = Number.parseInt(document.getElementById("INT").value);
    const wis = Number.parseInt(document.getElementById("WIS").value);
    const cha = Number.parseInt(document.getElementById("CHA").value);
   
    stats = {
        STR: str,
        DEX: dex,
        CON: con,
        INT: int,
        WIS: wis,
        CHA: cha
    };

    document.getElementById("profPicker").removeAttribute("hidden");

    /** BUILD PROFICIENCY TABLE HERE */
    // response = await fetch(`https://www.dnd5eapi.co/api/classes/${finalClass}`);
    // responseObj = await response.json();
    // const prof = responseObj.proficiency_choices;

    // let availProf = responseObj.from;
    // let profHTML = `<label for="profs">Choose your proficiency:</label> <select name="prof" id="prof">`;
    // for (let prof of availProf) {
    //     let info = `<option value=${race.index}> ${race.index}</option>`;
    //     racesHTML += info;
    //}
    // racesHTML += `</select>`;

    // document.getElementById("races").innerHTML = racesHTML;
    // document.getElementById("races").innerHTML += `<button id="raceBtn" onClick="getSubRaces()">Choose</button>`;
    
    // for (let i = 0; i < prof.choose; ++i) {

    // }

}

function pickStats() {
    document.getElementById("subclasses").disabled = true;
    document.getElementById("subclassBtn").style.display = "none";
    document.getElementById("statPicker").removeAttribute("hidden");
}

// Functions used to display the character choices to the user and input it to the js
async function getSubRaces() {
    document.getElementById("raceBtn").style.display = "none";
    document.getElementById("race").disabled = true;

    finalRace = document.getElementById("race").value;
    let response = await fetch(`https://www.dnd5eapi.co/api/races/${finalRace}`);
    let subraces = await response.json();

    let subracesHTML = `<label for="subraces">Choose your subrace:</label> <select name="subrace" id="subrace">`;
    for (let subrace of subraces.subraces) {
        let info = `<option value=${subrace.name}> ${subrace.name}</option>`;
        subracesHTML += info;
    }
    subracesHTML += `</select>`;

    document.getElementById("subraces").innerHTML = subracesHTML;
    document.getElementById("subraces").innerHTML += `<button id="subraceBtn" onClick="getClasses()">Choose</button>`;
}

async function getSubClasses() {
    document.getElementById("classBtn").style.display = "none";
    document.getElementById("class").disabled = true;

    finalClass = document.getElementById("class").value;
    let response = await fetch(`https://www.dnd5eapi.co/api/classes/${finalClass}`);
    let subclasses = await response.json();

    let subclassesHTML = `<label for="subclasses">Choose your subclass:</label> <select name="subclass" id="subclasses">`;
    for (let subclass of subclasses.subclasses) {
        let info = `<option value=${subclass.name}> ${subclass.name}</option>`;
        subclassesHTML += info;
    }
    subclassesHTML += `</select>`;

    document.getElementById("subclass").innerHTML = subclassesHTML;
    document.getElementById("subclass").innerHTML += `<button id="subclassBtn" onClick="pickStats()">Choose</button>`;
}

async function getClasses() {
    document.getElementById("subraceBtn").style.display = "none";
    document.getElementById("subrace").disabled = true;

    finalSubrace = document.getElementById("subrace").value;
    let response = await fetch("https://www.dnd5eapi.co/api/classes/");
    let classes = await response.json();

    let classesHTML = `<label for="classes">Choose your class:</label> <select name="class" id="class">`;
    for (let clas of classes.results) {
        let info = `<option value=${clas.index}> ${clas.index}</option>`;
        classesHTML += info;
    }
    classesHTML += `</select>`;

    document.getElementById("classes").innerHTML = classesHTML;
    document.getElementById("classes").innerHTML += `<button id="classBtn" onClick="getSubClasses()">Choose</button>`;
}

async function getRaces() {
    let response = await fetch("https://www.dnd5eapi.co/api/races/");
    let races = await response.json();
    let racesHTML = `<label for="races">Choose your race:</label> <select name="race" id="race">`;
    for (let race of races.results) {
        let info = `<option value=${race.index}> ${race.index}</option>`;
        racesHTML += info;
    }
    racesHTML += `</select>`;

    document.getElementById("races").innerHTML = racesHTML;
    document.getElementById("races").innerHTML += `<button id="raceBtn" onClick="getSubRaces()">Choose</button>`;
}
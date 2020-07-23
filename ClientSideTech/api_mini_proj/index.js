let finalRace = "";
let finalSubrace  = "";
let finalClass = "";
let finalSubclass = "";

function testFunc() {
    finalSubclass = document.getElementById("subClass").value;
    console.log(finalRace);
    console.log(finalClass);
    console.log(finalSubclass);
    console.log(finalSubrace);
}

async function getSubRaces() {
    document.getElementById("raceBtn").style.display = "none";
    document.getElementById("race").disabled = true;

    finalRace = document.getElementById("race").value;
    let response = await fetch(`https://www.dnd5eapi.co/api/races/${finalRace}`);
    let subraces = await response.json();

    let subracesHTML = `<label for="subraces">Choose your subrace:</label> <select name="subrace" id="subrace">`;
    // for (let subclass of subclasses.subclasses) {
    //     let info = `<option value=${subclass.name}> ${subclass.name}</option>`;
    //     subclassesHTML += info;
    // }
    // subclassesHTML += `</select>`;

    // document.getElementById("subclass").innerHTML = subclassesHTML;
    // document.getElementById("subclass").innerHTML += `<button id="subclassBtn" onClick="testFunc()">Choose</button>`;
}

async function getSubClasses() {
    document.getElementById("classBtn").style.display = "none";
    document.getElementById("class").disabled = true;

    finalClass = document.getElementById("class").value;
    let response = await fetch(`https://www.dnd5eapi.co/api/classes/${finalClass}`);
    let subclasses = await response.json();

    let subclassesHTML = `<label for="subclasses">Choose your subclass:</label> <select name="subclass" id="subclass">`;
    for (let subclass of subclasses.subclasses) {
        let info = `<option value=${subclass.name}> ${subclass.name}</option>`;
        subclassesHTML += info;
    }
    subclassesHTML += `</select>`;

    document.getElementById("subclass").innerHTML = subclassesHTML;
    document.getElementById("subclass").innerHTML += `<button id="subclassBtn" onClick="testFunc()">Choose</button>`;
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
let starter = '';
let num1 = starter;
let num2 = null;
let operator = null;

let add = (n1, n2) => {
    return n1 + n2;
};

let subtract = (n1, n2) => {
    return n1 - n2;
};

let multiply = (n1, n2) => {
    return n1 * n2;
};

let divide = (n1, n2) => {
    return n1 / n2;
};

function displayNumber(number) {
    if (num1 === starter) {
        document.querySelector('#resultsBar').style.visibility = 'visible';
        document.querySelector('#resultsBar').innerHTML = starter;
    }
    if (num2 === null) {
        num1 += number;
    } else {
        num2 += number;
    }
    document.getElementById("resultsBar").innerHTML += number;
}

function chooseFunction(operation) {
    if (operation === '+') {
        operator = add;
        document.getElementById("resultsBar").innerHTML += ' + ';
    } else if (operation === '-') {
        operator = subtract;
        document.getElementById("resultsBar").innerHTML += ' - ';
    } else if (operation === '*') {
        operator = multiply;
        document.getElementById("resultsBar").innerHTML += ' x ';
    } else if (operation === '/') {
        operator = divide;
        document.getElementById("resultsBar").innerHTML += ' / ';
    }
    num2 = starter;
    document.querySelector('#operations').style.display = 'none';
    document.querySelector('#submitBtn').style.display = 'unset';
}

function reset(result) {
    document.getElementById("resultsBar").innerHTML = result;
    num1 = result;
    num2 = null;
    operator = null;
    if (result === ".") {
        document.querySelector('#resultsBar').style.visibility = 'hidden';
        num1 = starter;
    }

    document.querySelector('#submitBtn').style.display = 'none';
    document.querySelector('#operations').style.display = 'unset';
}

function resetBtn() {
    reset(".");
}

function submit() {
    let result = operator(parseFloat(num1), parseFloat(num2));
    reset(result);
}
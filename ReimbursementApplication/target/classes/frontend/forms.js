let reimbursement = {};
let user = {};

function loadPage() {
    reimbursement = JSON.parse(localStorage.getItem("reimbursement"));
    user = JSON.parse(localStorage.getItem("user"));

    document.getElementById("logged").innerHTML += " " + user.username;
    if (reimbursement.type === "create") {
        createPage();
    } else if (reimbursement.type === "decide") {
        decisionPage();
    }

}

function createPage() {
    let page = document.getElementById("page");
    page.innerHTML += `<div id="createForm">`;
    page.innerHTML += `<p class="createField"><b>Reason: </b><input id="reasonField" type="text"/></p>`;
    page.innerHTML += `<p class="createField"><b>Amount: </b><input id="amountField" type="number"/></p>`;
    page.innerHTML += `<button id="createBtnTwo" class="btn btn-primary" onclick=sendReim()>Request Reimbursement</button>`;
    page.innerHTML += `</div>`
}

async function decisionPage() {
    let page = document.getElementById("page");
    page.innerHTML += `<table id="decisionTable" class="table">`;
    page.innerHTML += `<tr><td><b>Reason: </b>${reimbursement.message}</td></tr><br/>`;
    page.innerHTML += `<tr><td><b>Amount: </b>$${reimbursement.amount}</td></tr><br/>`;
    page.innerHTML += `<tr><td><b>Employee: </b>${reimbursement.employee.lastName},${reimbursement.employee.firstName}</td></tr>`;
    page.innerHTML += `</table>`;

    page.innerHTML +=  `<div id="decisionForm>`;
    page.innerHTML += `<p class="decideField"><b>Message: </b><input id="messageField" type="text"/></p>`;
    page.innerHTML += `<button id="approveBtn" class="btn btn-success" onclick=sendDecision("Approve")>Approve</button>`;
    page.innerHTML += `<button id="denyBtn" class="btn btn-danger" onclick=sendDecision("Deny")>Deny</button>`;
    page.innerHTML += `</div>`;
}

async function sendReim() {
    let reim = {};
    reim.rid = 0;
    reim.status = "Pending";
    //reim.date = "7/3/2003";
    reim.amount = document.getElementById("amountField").value;
    reim.message = document.getElementById("reasonField").value;
    reim.acceptMessage = "";

    let response = await fetch(`http://localhost:7000/employees/${user.eid}/reimbursements`, {
        method: 'post',
        body: JSON.stringify(reim)
    });

    if (response.status === 201) {
        window.location.href = "home.html";
    }
}

async function sendDecision(result) {
    let reim = {};
    reim.rid = reimbursement.rid;
    if (result === "Approve") {
        reim.status = "Approved";
    } else {
        reim.status = "Denied";
    }
    reim.amount = reimbursement.amount;
    reim.message = reimbursement.message;
    reim.acceptMessage = document.getElementById("messageField").value;
    reim.employee = reimbursement.employee;

    let response = await fetch(`http://localhost:7000/reimbursements/`, {
        method: 'put',
        body: JSON.stringify(reim)
    });
    let responseObj = response.json();
    console.log(responseObj);

    if (response.status !== 404) {
        window.location.href = "home.html";
    }

}
let user = {username: "Sullivan", password: "B3stSc4r3r", isManager: false, eid: 1};


async function loadPage() {
    // Work on getting username and password from login page\
    document.getElementById("createBtn").style.visibility = 'visible';
    let response;
    let responseObj;
    // Make the API calls to retreive all reimbursements we want to show
    if (!user.isManager) {
        response = await fetch(`http://localhost:7000/employees/${user.eid}/reimbursements`);
        responseObj = await response.json();
        
    } else {
        response = await fetch(`http://localhost:7000/reimbursements`);
        responseObj = await response.json();
    }
   
    // Build table using our reimbursement object
    let tableHtml = html`<table class="table">`;
    for (let reimbursement of responseObj) {
        tableHtml += html`<tr scope="row"><td>${reimbursement.message}</td><td>$${reimbursement.amount}</td>`

        // customize the table between employees and managers
        if (user.isManager) {
            tableHtml += html`<td>${reimbursement.employee.lastName}, ${reimbursement.employee.firstName}</td>`
        } else {
            tableHtml += html`<td><button class="reviewBtn" onclick="reviewReimbursement(${reimbursement.rid})></button></td>`
        }

        if (reimbursement.status === "Pending") {
            tableHtml += html`<button type="button" class="btn btn-warning" onclick="reviewReimbursement()">Pending</button>`
        } else if (reimbursement.status === "Accepted") {
            tableHtml += html`<button type="button" class="btn btn-success" onclick="reviewReimbursement()">Accepted</button>`
        } else {
            tableHtml += html`<button type="button" class="btn btn-danger" onclick="reviewReimbursement()">Denied</button>`
        }
        tableHtml += html`</tr>`;
            
    }

    let table = document.getElementById("reimTable");
    table.innerHTML += (tableHtml + html`</table>`);
}

async function createReimbursement() {

}

async function reviewReimbursement() {
    console.log("hello world")
}
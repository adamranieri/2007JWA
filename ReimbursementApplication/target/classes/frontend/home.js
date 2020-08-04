let user = {username: "Sullivan", password: "B3stSc4r3r", isManager: false, eid: 1};


async function loadPage() {
    // Work on getting username and password from login page\
    /**
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
    let tableHtml = ``;
    for (let reimbursement of responseObj) {
        tableHtml += `<tr><td>${reimbursement.message}</td><td>${reimbursement.amount}</td>`

        // customize the table between employees and managers
        if (user.isManager) {
            tableHtml += `<td>${reimbursement.employee.lastName}, ${reimbursement.employee.firstName}</td>`
        } else {
            tableHtml += `<td><button class="reviewBtn" onclick="reviewReimbursement(${reimbursement.rid})></button></td>`
        }

        if (reimbursement.status === "Pending") {
            tableHtml += `<button class="pendBtn" onclick="reviewReimbursement(${reimbursement.rid})">Pending</button>`
        } else if (reimbursement.status === "Accepted") {
            tableHtml += `<button class="acceptBtn" onclick="reviewReimbursement(${reimbursement.rid})">Accepted</button>`
        } else {
            tableHtml += `<button class="denyBtn" onclick="reviewReimbursement(${reimbursement.rid})">Denied</button>`
        }
        tableHtml += `</tr>`;
            
    }
    console.log(tableHtml);
    */
    document.getElementById("reimTable").innerHtml = 'Hello world';//`<table id="table"><tr colspan="5">Reimbursements</tr>`  + tableHtml + `</table>`;

}

async function createReimbursement() {

}

async function reviewReimbursement() {

}
let user = {};


async function loadPage() {
    user = JSON.parse(localStorage.getItem("user"));
    //user.isManager = false;
    document.getElementById("logged").innerHTML += " " + user.username;


    // Work on getting username and password from login page\
    let response;
    let responseObj;
    // Make the API calls to retreive all reimbursements we want to show
    if (!user.isManager) {
        document.getElementById("createDiv").innerHTML += `<button id="createBtn" onclick="createReimbursement()" class="btn btn-primary">Request a Reimbursement</button>`;
        response = await fetch(`http://localhost:7000/employees/${user.eid}/reimbursements`);
        responseObj = await response.json();
    } else {
        response = await fetch(`http://localhost:7000/reimbursements`);
        responseObj = await response.json();
    }
   
    // Build table using our reimbursement object
    let tableHtml = `<table id="reimbursementTable" class="table">`;
    for (let reimbursement of responseObj) {
        tableHtml += `<tr scope="row"><td class="reimMessage>${reimbursement.message}</td><td class="reimAmount">$${reimbursement.amount}</td>`

        // customize the table between employees and managers
        if (user.isManager) {
            tableHtml += `<td>${reimbursement.employee.lastName}, ${reimbursement.employee.firstName}</td>`
            if (reimbursement.status === "Pending") {
                tableHtml += `<td><button class="decideBtn btn" onclick="decideReimbursement(` + reimbursement.rid + `)">Approve/Deny</button></td>`
            }
        }
        if (reimbursement.status !== "Pending") {
            tableHtml += `<td>${reimbursement.acceptMessage}</td>`;
        } else if (!user.isManager){
            tableHtml += `<td> </td>`;
        }

        if (reimbursement.status === "Pending") {
            tableHtml += `<td style="color:yellow">Pending</td>`
        } else if (reimbursement.status === "Approved") {
            tableHtml += `<td style="color:#7CFC00">Accepted</td>`
        } else {
            tableHtml += `<td style="color:red">Denied</td>`
        }
        tableHtml += `</tr>`;
            
    }

    let table = document.getElementById("reimTable");
    table.innerHTML += (tableHtml + `</table>`);
}

function createReimbursement() {
    localStorage.setItem("reimbursement", JSON.stringify({"type": "create"}))
    window.location.href = "forms.html";
}

async function decideReimbursement(rid) {
    let response = await fetch(`http://localhost:7000/reimbursements/${rid}`);
    let reimbursement = await response.json();
    reimbursement.type = "decide";
    localStorage.setItem("reimbursement", JSON.stringify(reimbursement));
    window.location.href = "forms.html";
}
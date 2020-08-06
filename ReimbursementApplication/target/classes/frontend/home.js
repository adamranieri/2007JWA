let user = {username: "Waternoose", password: "B3stSc4r3r", isManager: true, id: 1};


async function loadPage() {
    // let response1 = await fetch("http://localhost:7000/userinfo");
    // let response1Obj = await response1.json();
    console.log(document.cookie);



    // Work on getting username and password from login page\
    document.getElementById("createBtn").style.visibility = 'visible';
    let response;
    let responseObj;
    // Make the API calls to retreive all reimbursements we want to show
    if (!user.isManager) {
        response = await fetch(`http://localhost:7000/employees/${user.id}/reimbursements`);
        responseObj = await response.json();
        
    } else {
        response = await fetch(`http://localhost:7000/reimbursements`);
        responseObj = await response.json();
    }
   
    // Build table using our reimbursement object
    let tableHtml = `<table class="table">`;
    for (let reimbursement of responseObj) {
        tableHtml += `<tr scope="row"><td>${reimbursement.message}</td><td>$${reimbursement.amount}</td>`

        // customize the table between employees and managers
        if (user.isManager) {
            tableHtml += `<td>${reimbursement.employee.lastName}, ${reimbursement.employee.firstName}</td>`
        }

        if (reimbursement.status === "Pending") {
            tableHtml += `<td><button type="button" class="btn btn-warning" onclick="reviewReimbursement()">Pending</button></td>`
        } else if (reimbursement.status === "Accepted") {
            tableHtml += `<td><button type="button" class="btn btn-success" onclick="reviewReimbursement()">Accepted</button></td>`
        } else {
            tableHtml += `<td><button type="button" class="btn btn-danger" onclick="reviewReimbursement()">Denied</button></td>`
        }
        tableHtml += `</tr>`;
            
    }

    let table = document.getElementById("reimTable");
    table.innerHTML += (tableHtml + `</table>`);
}

async function createReimbursement() {

}

async function reviewReimbursement() {
    console.log("hello world")
}
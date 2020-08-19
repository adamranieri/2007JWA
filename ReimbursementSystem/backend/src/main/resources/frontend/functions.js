let table = document.getElementById("reim_table");
let itemAscending, statusAscending, categoryAscending, amountAscending, employeeAscending, noteAscending, dateAscending;

async function submitReimbursement() {
    let item = document.getElementById("item_field");
    let category = document.getElementById("category_field");
    let status = document.getElementById("status_field");
    let amount = document.getElementById("amount_field");
    let employee_name = document.getElementById("employee_name_field");
    let e_id = document.getElementById("employee_id_field");
    let note = document.getElementById("note_field");
    let date_submitted = document.getElementById("date_field");

    // sessionStorage.setItem("e_id",e_id.value);


    let reimbursement = {
        "r_id": 0,
        "status": status.value,
        "category": category.value,
        "date_submitted": date_submitted.value,
        "item": item.value,
        "note": note.value,
        "amount": amount.value
    }

    const config = {
        method: "POST",
        headers: { "Content-Type": "application/json" }, //can use the headers property to set headers
        body: JSON.stringify(reimbursement)
    };

    const httpResponse = await fetch(`http://localhost:7000/reimbursements/${e_id.value}`, config);

    sortDate();

    let modal = document.getElementById("submit_modal");
    modal.style.display = "none";
    item.value = "";
    category.value = "";
    status.value = "";
    amount.value = "";
    employee_name.value = "";
    e_id.value = "";
    note.value = "";
    date_submitted.value = "";

}

function openReimModal() {
    let modal = document.getElementById("submit_modal");
    modal.style.display = "block";


}
function closeReimModal() {
    let modal = document.getElementById("submit_modal");
    modal.style.display = "none";
}
window.onclick = function (event) {
    let modal = document.getElementById("submit_modal");
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

async function attemptLogin() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    let loginfo = {
        username:username,
        password:password
    };

    let config = {
        method: 'POST',
        credentials: 'include',
        headers: { 'Content-Type':'application/json'},
        body: JSON.stringify(config)
    };

    const httpResponse = await fetch("http://localhost:7000/login", config);

    let config = {
        method: 'POST',
        credentials: 'include',
        headers: { 'Content-Type':'application/json'}
    };

    const getUser = await fetch("http://localhost:7000/login", config);
    const logUser = getUser.json();

        
    let permission=sessionStorage.getItem(logUser);

    // if (permission === "manager" || permission =="employee") {
    //     window.location.replace("reimbursement_page.html");
    // } else {
    //     alert("Invalid username or password");
    // }
}

async function getAllReimbursements() {
    // let httpResponse;
    // if(sessionStorage ==="manager"){
    const httpResponse = await fetch("http://localhost:7000/reimbursements");
    // }else{
    //     let e_id = await sessionStorage.getItem("e_id");
    //     httpResponse = await fetch(`http://localhost:7000/reimbursements/${e_id}`);
    // }
    const reimbursements = await httpResponse.json();


    let html = ``;
    for (let reimbursement of reimbursements) {
        html += `<tr class="reimbursement_row">`;
        html += `<td id="itemField">${reimbursement.item}</td>`;
        html += `<td id="statusField"><button class="statusBtn" onclick="statusChange(this.value)" value= "${reimbursement.status}">${reimbursement.status}</button></td>`;
        html += `<td id="categoryField">${reimbursement.category}</td>`;
        html += `<td ="amountField">${reimbursement.amount}</td>`;
        html += `<td id="employeeField">${reimbursement.employee.firstName} ${reimbursement.employee.lastName}</td>`;
        html += `<td id="noteField">${reimbursement.note}</td>`;
        html += `<td id="dateField">${reimbursement.dateSubmitted}</td>`;
        html += `</tr>`;
    }

    table.innerHTML += html;
}


function search() {
    let input, filter, tr, tds, txtValue;
    input = document.getElementById("my_input");
    filter = input.value.toUpperCase();
    tr = table.getElementsByClassName("reimbursement_row");
    for (let i = 0; i < tr.length; i++) {
        tds = tr[i].getElementsByTagName("td");
        let hasFilter = false;
        for (let td of tds) {
            txtValue = td.textContent;

            if (txtValue.toUpperCase().includes(filter) == true) {
                hasFilter = true;
                break;
            }
        }
        if (hasFilter) {
            tr[i].style.display = "";
        } else {
            tr[i].style.display = "none";
        }
    }
}

async function sortItem() {
    if (itemAscending === undefined) {
        itemAscending = true;
    }
    const httpResponse = await fetch(`http://localhost:7000/reimbursements/ascendingItem?ascending=${itemAscending}`);
    const reimbursements = await httpResponse.json();
    let html = `<tr>
                    <th id="item_head"><button id="item_btn" onclick="sortItem()"></button><b>Item</b></th>
                    <th id="status_head"><button id="item_btn" onclick="sortStatus()"></button><b>Status</b></th>
                    <th id="category_head"><button id="item_btn" onclick="sortCategory()"></button><b>Category</b></th>
                    <th id="amount_head"><button id="item_btn" onclick="sortAmount()"></button><b>Amount</b></th>
                    <th id="employee_head"><button id="item_btn" onclick="sortEmployee()"></button><b>Employee</b></th>
                    <th id="note_head"><button id="item_btn" onclick="sortNote()"></button><b>Note</b></th>
                    <th id="date_head"><button id="item_btn" onclick="sortDate()"></button><b>Date Submitted</b></th>
                </tr>`;

    for (let reimbursement of reimbursements) {
        html += `<tr class="reimbursement_row">`;
        html += `<td id="itemField">${reimbursement.item}</td>`;
        html += `<td id="statusField"><button class="statusBtn" onclick="statusChange(this.value)" value= "${reimbursement.status}">${reimbursement.status}</button></td>`;
        html += `<td id="categoryField">${reimbursement.category}</td>`;
        html += `<td ="amountField">${reimbursement.amount}</td>`;
        html += `<td id="employeeField">${reimbursement.employee.firstName} ${reimbursement.employee.lastName}</td>`;
        html += `<td id="noteField">${reimbursement.note}</td>`;
        html += `<td id="dateField">${reimbursement.dateSubmitted}</td>`;
        html += `</tr>`;
    }

    table.innerHTML = html;
    if (itemAscending) {
        itemAscending = false;
    } else {
        itemAscending = true;
    }

}

async function sortStatus() {
    if (statusAscending === undefined) {
        statusAscending = true;
    }
    const httpResponse = await fetch(`http://localhost:7000/reimbursements/ascendingStatus?ascending=${statusAscending}`);
    const reimbursements = await httpResponse.json();
    let html = `<tr>
                    <th id="item_head"><button id="item_btn" onclick="sortItem()"></button><b>Item</b></th>
                    <th id="status_head"><button id="item_btn" onclick="sortStatus()"></button><b>Status</b></th>
                    <th id="category_head"><button id="item_btn" onclick="sortCategory()"></button><b>Category</b></th>
                    <th id="amount_head"><button id="item_btn" onclick="sortAmount()"></button><b>Amount</b></th>
                    <th id="employee_head"><button id="item_btn" onclick="sortEmployee()"></button><b>Employee</b></th>
                    <th id="note_head"><button id="item_btn" onclick="sortNote()"></button><b>Note</b></th>
                    <th id="date_head"><button id="item_btn" onclick="sortDate()"></button><b>Date Submitted</b></th>
                </tr>`;

    for (let reimbursement of reimbursements) {
        html += `<tr class="reimbursement_row">`;
        html += `<td id="itemField">${reimbursement.item}</td>`;
        html += `<td id="statusField"><button class="statusBtn" onclick="statusChange(this.value)" value= "${reimbursement.status}">${reimbursement.status}</button></td>`;
        html += `<td id="categoryField">${reimbursement.category}</td>`;
        html += `<td ="amountField">${reimbursement.amount}</td>`;
        html += `<td id="employeeField">${reimbursement.employee.firstName} ${reimbursement.employee.lastName}</td>`;
        html += `<td id="noteField">${reimbursement.note}</td>`;
        html += `<td id="dateField">${reimbursement.dateSubmitted}</td>`;
        html += `</tr>`;
    }

    table.innerHTML = html;
    if (statusAscending) {
        statusAscending = false;
    } else {
        statusAscending = true;
    }

}

async function sortCategory() {
    if (categoryAscending === undefined) {
        categoryAscending = true;
    }
    const httpResponse = await fetch(`http://localhost:7000/reimbursements/ascendingCategory?ascending=${categoryAscending}`);
    const reimbursements = await httpResponse.json();
    let html = `<tr>
                    <th id="item_head"><button id="item_btn" onclick="sortItem()"></button><b>Item</b></th>
                    <th id="status_head"><button id="item_btn" onclick="sortStatus()"></button><b>Status</b></th>
                    <th id="category_head"><button id="item_btn" onclick="sortCategory()"></button><b>Category</b></th>
                    <th id="amount_head"><button id="item_btn" onclick="sortAmount()"></button><b>Amount</b></th>
                    <th id="employee_head"><button id="item_btn" onclick="sortEmployee()"></button><b>Employee</b></th>
                    <th id="note_head"><button id="item_btn" onclick="sortNote()"></button><b>Note</b></th>
                    <th id="date_head"><button id="item_btn" onclick="sortDate()"></button><b>Date Submitted</b></th>
                </tr>`;

    for (let reimbursement of reimbursements) {
        html += `<tr class="reimbursement_row">`;
        html += `<td id="itemField">${reimbursement.item}</td>`;
        html += `<td id="statusField"><button class="statusBtn" onclick="statusChange(this.value)" value= "${reimbursement.status}">${reimbursement.status}</button></td>`;
        html += `<td id="categoryField">${reimbursement.category}</td>`;
        html += `<td ="amountField">${reimbursement.amount}</td>`;
        html += `<td id="employeeField">${reimbursement.employee.firstName} ${reimbursement.employee.lastName}</td>`;
        html += `<td id="noteField">${reimbursement.note}</td>`;
        html += `<td id="dateField">${reimbursement.dateSubmitted}</td>`;
        html += `</tr>`;
    }

    table.innerHTML = html;
    if (categoryAscending) {
        categoryAscending = false;
    } else {
        categoryAscending = true;
    }

}

async function sortAmount() {
    if (amountAscending === undefined) {
        amountAscending = false;
    }
    const httpResponse = await fetch(`http://localhost:7000/reimbursements/ascendingAmount?ascending=${amountAscending}`);
    const reimbursements = await httpResponse.json();
    let html = `<tr>
                    <th id="item_head"><button id="item_btn" onclick="sortItem()"></button><b>Item</b></th>
                    <th id="status_head"><button id="item_btn" onclick="sortStatus()"></button><b>Status</b></th>
                    <th id="category_head"><button id="item_btn" onclick="sortCategory()"></button><b>Category</b></th>
                    <th id="amount_head"><button id="item_btn" onclick="sortAmount()"></button><b>Amount</b></th>
                    <th id="employee_head"><button id="item_btn" onclick="sortEmployee()"></button><b>Employee</b></th>
                    <th id="note_head"><button id="item_btn" onclick="sortNote()"></button><b>Note</b></th>
                    <th id="date_head"><button id="item_btn" onclick="sortDate()"></button><b>Date Submitted</b></th>
                </tr>`;

    for (let reimbursement of reimbursements) {
        html += `<tr class="reimbursement_row">`;
        html += `<td id="itemField">${reimbursement.item}</td>`;
        html += `<td id="statusField"><button class="statusBtn" onclick="statusChange(this.value)" value= "${reimbursement.status}">${reimbursement.status}</button></td>`;
        html += `<td id="categoryField">${reimbursement.category}</td>`;
        html += `<td ="amountField">${reimbursement.amount}</td>`;
        html += `<td id="employeeField">${reimbursement.employee.firstName} ${reimbursement.employee.lastName}</td>`;
        html += `<td id="noteField">${reimbursement.note}</td>`;
        html += `<td id="dateField">${reimbursement.dateSubmitted}</td>`;
        html += `</tr>`;
    }

    table.innerHTML = html;
    if (amountAscending) {
        amountAscending = false;
    } else {
        amountAscending = true;
    }

}

async function sortEmployee() {
    if (employeeAscending === undefined) {
        employeeAscending = false;
    }
    const httpResponse = await fetch(`http://localhost:7000/reimbursements/ascendingEmployee?ascending=${employeeAscending}`);
    const reimbursements = await httpResponse.json();
    let html = `<tr>
                    <th id="item_head"><button id="item_btn" onclick="sortItem()"></button><b>Item</b></th>
                    <th id="status_head"><button id="item_btn" onclick="sortStatus()"></button><b>Status</b></th>
                    <th id="category_head"><button id="item_btn" onclick="sortCategory()"></button><b>Category</b></th>
                    <th id="amount_head"><button id="item_btn" onclick="sortAmount()"></button><b>Amount</b></th>
                    <th id="employee_head"><button id="item_btn" onclick="sortEmployee()"></button><b>Employee</b></th>
                    <th id="note_head"><button id="item_btn" onclick="sortNote()"></button><b>Note</b></th>
                    <th id="date_head"><button id="item_btn" onclick="sortDate()"></button><b>Date Submitted</b></th>
                </tr>`;

    for (let reimbursement of reimbursements) {
        html += `<tr class="reimbursement_row">`;
        html += `<td id="itemField">${reimbursement.item}</td>`;
        html += `<td id="statusField"><button class="statusBtn" onclick="statusChange(this.value)" value= "${reimbursement.status}">${reimbursement.status}</button></td>`;
        html += `<td id="categoryField">${reimbursement.category}</td>`;
        html += `<td ="amountField">${reimbursement.amount}</td>`;
        html += `<td id="employeeField">${reimbursement.employee.firstName} ${reimbursement.employee.lastName}</td>`;
        html += `<td id="noteField">${reimbursement.note}</td>`;
        html += `<td id="dateField">${reimbursement.dateSubmitted}</td>`;
        html += `</tr>`;
    }

    table.innerHTML = html;
    if (employeeAscending) {
        employeeAscending = false;
    } else {
        employeeAscending = true;
    }

}

async function sortNote() {
    if (noteAscending === undefined) {
        noteAscending = true;
    }
    const httpResponse = await fetch(`http://localhost:7000/reimbursements/ascendingNote?ascending=${noteAscending}`);
    const reimbursements = await httpResponse.json();
    let html = `<tr>
                    <th id="item_head"><button id="item_btn" onclick="sortItem()"></button><b>Item</b></th>
                    <th id="status_head"><button id="item_btn" onclick="sortStatus()"></button><b>Status</b></th>
                    <th id="category_head"><button id="item_btn" onclick="sortCategory()"></button><b>Category</b></th>
                    <th id="amount_head"><button id="item_btn" onclick="sortAmount()"></button><b>Amount</b></th>
                    <th id="employee_head"><button id="item_btn" onclick="sortEmployee()"></button><b>Employee</b></th>
                    <th id="note_head"><button id="item_btn" onclick="sortNote()"></button><b>Note</b></th>
                    <th id="date_head"><button id="item_btn" onclick="sortDate()"></button><b>Date Submitted</b></th>
                </tr>`;

    for (let reimbursement of reimbursements) {
        html += `<tr class="reimbursement_row">`;
        html += `<td id="itemField">${reimbursement.item}</td>`;
        html += `<td id="statusField"><button class="statusBtn" onclick="statusChange(this.value)" value= "${reimbursement.status}">${reimbursement.status}</button></td>`;
        html += `<td id="categoryField">${reimbursement.category}</td>`;
        html += `<td ="amountField">${reimbursement.amount}</td>`;
        html += `<td id="employeeField">${reimbursement.employee.firstName} ${reimbursement.employee.lastName}</td>`;
        html += `<td id="noteField">${reimbursement.note}</td>`;
        html += `<td id="dateField">${reimbursement.dateSubmitted}</td>`;
        html += `</tr>`;
    }

    table.innerHTML = html;
    if (noteAscending) {
        noteAscending = false;
    } else {
        noteAscending = true;
    }

}

async function sortDate() {
    if (dateAscending === undefined) {
        dateAscending = false;
    }
    const httpResponse = await fetch(`http://localhost:7000/reimbursements/ascendingDate?ascending=${dateAscending}`);
    const reimbursements = await httpResponse.json();
    let html = `<tr>
                    <th id="item_head"><button id="item_btn" onclick="sortItem()"></button><b>Item</b></th>
                    <th id="status_head"><button id="item_btn" onclick="sortStatus()"></button><b>Status</b></th>
                    <th id="category_head"><button id="item_btn" onclick="sortCategory()"></button><b>Category</b></th>
                    <th id="amount_head"><button id="item_btn" onclick="sortAmount()"></button><b>Amount</b></th>
                    <th id="employee_head"><button id="item_btn" onclick="sortEmployee()"></button><b>Employee</b></th>
                    <th id="note_head"><button id="item_btn" onclick="sortNote()"></button><b>Note</b></th>
                    <th id="date_head"><button id="item_btn" onclick="sortDate()"></button><b>Date Submitted</b></th>
                </tr>`;

    for (let reimbursement of reimbursements) {
        html += `<tr class="reimbursement_row">`;
        html += `<td id="itemField">${reimbursement.item}</td>`;
        html += `<td id="statusField"><button class="statusBtn" onclick="statusChange(this.value)" value= "${reimbursement.status}">${reimbursement.status}</button></td>`;
        html += `<td id="categoryField">${reimbursement.category}</td>`;
        html += `<td ="amountField">${reimbursement.amount}</td>`;
        html += `<td id="employeeField">${reimbursement.employee.firstName} ${reimbursement.employee.lastName}</td>`;
        html += `<td id="noteField">${reimbursement.note}</td>`;
        html += `<td id="dateField">${reimbursement.dateSubmitted}</td>`;
        html += `</tr>`;
    }

    table.innerHTML = html;
    if (dateAscending) {
        dateAscending = false;
    } else {
        dateAscending = true;
    }

}

let table = document.getElementById("reim_table");

sessionStorage.setItem("reimbursement_being_changed", 0)
sessionStorage.setItem("itemAscending",true);
sessionStorage.setItem("statusAscending",true);
sessionStorage.setItem("categoryAscending",true);
sessionStorage.setItem("amountAscending",true);
sessionStorage.setItem("employeeAscending",true);
sessionStorage.setItem("noteAscending",true);
sessionStorage.setItem("dateAscending",true);

async function submitReimbursement() {
    let item = document.getElementById("item_field");
    let category = document.getElementById("category_field");
    let status = document.getElementById("status_field");
    let amount = document.getElementById("amount_field");
    let employee_name = document.getElementById("employee_name_field");
    let e_id = sessionStorage.getItem("eId");
    let note = document.getElementById("note_field");
    let date_submitted = document.getElementById("date_field");


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
        credentials: "include",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(reimbursement)
    };

    const httpResponse = await fetch(`http://localhost:7000/reimbursements/${e_id}`, config);

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
    let permission = sessionStorage.getItem("permission");

    if (permission === "employee") {
        let modal = document.getElementById("submit_modal");
        modal.style.visibility = "visible";
    } else {
        alert("You should not be creating a reimbursement.");
    }


}
function closeReimModal() {
    let sub_modal = document.getElementById("submit_modal");
    let up_modal = document.getElementById("update_modal");
    up_modal.style.visibility = "hidden";
    sub_modal.style.visibility = "hidden";
}
window.onclick = function (event) {
    let modal = document.getElementById("submit_modal");
    let up_modal = document.getElementById("update_modal");
    if (event.target == modal) {
        modal.style.visibility = "hidden";
    }
    if (event.target == up_modal) {
        modal.style.visibility = "hidden";
    }
}

function updateReim(rId) {
    sessionStorage.setItem("reimbursement_being_changed", rId)
    let permission = sessionStorage.getItem("permission");
    if (permission === "manager") {
        let modal = document.getElementById("update_modal");
        modal.style.visibility = "visible";
    } else {
        alert("You do not have authorty to change this reimbursement.");
    }
}
async function changeStatus(newStatus) {
    let rId = sessionStorage.getItem("reimbursement_being_changed");

    let config = {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' }
    }

    const httpResponse = await fetch(`http://localhost:7000/reimbursements/${rId}?status=${newStatus}`, config);
    closeReimModal();
    window.location.reload();
}

async function changeNote() {
    let rId = sessionStorage.getItem("reimbursement_being_changed");
    let newNote = document.getElementById("new_note").value;

    let config = {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' }
    }

    const httpResponse = await fetch(`http://localhost:7000/reimbursements/${rId}?note=${newNote}`, config);

    closeReimModal();
    window.location.reload();
}

async function attemptLogin() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    let loginfo = {
        username: username,
        password: password
    };

    let config = {
        method: 'POST',
        credentials: 'include',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(loginfo)
    };


    const httpResponse = await fetch("http://localhost:7000/login", config);
    if (httpResponse.status === 404) {
        alert("Invalid username or password");
    }

    config = {
        method: 'GET',
        credentials: 'include',
        headers: { 'Content-Type': 'application/json' }
    };

    const getUser = await fetch("http://localhost:7000/login", config);
    const logUser = await getUser.json();


    let permission = logUser.permission;

    if (!permission) {
        sessionStorage.setItem("eId", logUser.id);
        sessionStorage.setItem("permission", "employee");
    } else {
        sessionStorage.setItem("permission", "manager");
    }
    console.log(sessionStorage.getItem("eId"));
    window.location.href = "reimbursement_page.html";
}

async function getAllReimbursements() {
    let httpResponse;
    if (sessionStorage.getItem("permission") === "employee") {
        let e_id = sessionStorage.getItem("eId");
        httpResponse = await fetch(`http://localhost:7000/reimbursements/${e_id}`);
    }else {
        httpResponse = await fetch("http://localhost:7000/reimbursements");
    }  
    const reimbursements = await httpResponse.json();

    let html = ``;
    for (let reimbursement of reimbursements) {
        html += `<tr class="reimbursement_row">`;
        html += `<td id="itemField">${reimbursement.item}</td>`;
        html += `<td id="statusField">${reimbursement.status}</td>`;
        html += `<td id="categoryField">${reimbursement.category}</td>`;
        html += `<td id="amountField">${reimbursement.amount}</td>`;
        html += `<td id="employeeField">${reimbursement.employee.firstName} ${reimbursement.employee.lastName}</td>`;
        html += `<td id="noteField">${reimbursement.note}</td>`;
        html += `<td id="dateField">${reimbursement.dateSubmitted}</td>`;
        html += `<td><button id="update_reim_btn" onclick = "updateReim(this.value)" value=${reimbursement.rId}>Update Reimbursement</button></td>`
        html += `</tr>`;
    }

    table.innerHTML += html;
}

async function getAllRids() {
    let httpResponse1;
    if (sessionStorage.getItem("permission") === "manager") {
        httpResponse1 = await fetch("http://localhost:7000/reimbursements");
    } else if (sessionStorage.getItem("permission") === "employee") {
        let e_id = sessionStorage.getItem("eId");
        httpResponse1 = await fetch(`http://localhost:7000/reimbursements/${e_id}`);
    }
    const allReimbursements = await httpResponse1.json();
    let allRids = [];
    for (let i = 0; i < allReimbursements.length; i++) {
        allRids.push(allReimbursements[i].rId);
    }
    return allRids;
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
            tr[i].style.visibility = "visible";
            tr[i].style.display = "";
        } else {
            tr[i].style.visibility = "hidden";
            tr[i].style.display = "none";
        }
    }
}



async function sortItem() {
    let itemAscending = (sessionStorage.getItem("itemAscending") =='true');
    let rids = (await getAllRids()).slice(0);

    const httpResponse = await fetch(`http://localhost:7000/reimbursements/ascendingItem?ascending=${itemAscending}`);
    const reimbursements = await httpResponse.json();
    let html = `<tr>
                <th id="item_head"><button id="item_btn" onclick="sortItem()"></button><b>Item</b></th>
                <th id="status_head"><button id="status_btn" onclick="sortStatus()"></button><b>Status</b></th>
                <th id="category_head"><button id="category_btn" onclick="sortCategory()"></button><b>Category</b></th>
                <th id="amount_head"><button id="amount_btn" onclick="sortAmount()"></button><b>Amount</b></th>
                <th id="employee_head"><button id="employee_btn" onclick="sortEmployee()"></button><b>Employee</b></th>
                <th id="note_head"><button id="note_btn" onclick="sortNote()"></button><b>Note</b></th>
                <th id="date_head"><button id="date_btn" onclick="sortDate()"></button><b>Date Submitted</b></th>
                <th><span id="update_reim_btn"></span></td>
                </tr>`;

    for (let reimbursement of reimbursements) {

        if (rids.includes(reimbursement.rId)) {
            html += `<tr class="reimbursement_row">`;
            html += `<td id="itemField">${reimbursement.item}</td>`;
            html += `<td id="statusField">${reimbursement.status}</td>`;
            html += `<td id="categoryField">${reimbursement.category}</td>`;
            html += `<td id="amountField">${reimbursement.amount}</td>`;
            html += `<td id="employeeField">${reimbursement.employee.firstName} ${reimbursement.employee.lastName}</td>`;
            html += `<td id="noteField">${reimbursement.note}</td>`;
            html += `<td id="dateField">${reimbursement.dateSubmitted}</td>`;
            html += `<td><button id="update_reim_btn" onclick = "updateReim(this.value)" value=${reimbursement.rId}>Update Reimbursement</button></td>`
            html += `</tr>`;
        }
    }

    table.innerHTML = html;
    if (itemAscending) {
        sessionStorage.setItem("itemAscending",false);
    } else {
        sessionStorage.setItem("itemAscending",true);
    }

}

async function sortStatus() {
    let statusAscending = (sessionStorage.getItem("statusAscending")=='true');
    let rids = (await getAllRids()).slice(0);

    const httpResponse = await fetch(`http://localhost:7000/reimbursements/ascendingStatus?ascending=${statusAscending}`);
    const reimbursements = await httpResponse.json();
    let html = `<tr>
                <th id="item_head"><button id="item_btn" onclick="sortItem()"></button><b>Item</b></th>
                <th id="status_head"><button id="status_btn" onclick="sortStatus()"></button><b>Status</b></th>
                <th id="category_head"><button id="category_btn" onclick="sortCategory()"></button><b>Category</b></th>
                <th id="amount_head"><button id="amount_btn" onclick="sortAmount()"></button><b>Amount</b></th>
                <th id="employee_head"><button id="employee_btn" onclick="sortEmployee()"></button><b>Employee</b></th>
                <th id="note_head"><button id="note_btn" onclick="sortNote()"></button><b>Note</b></th>
                <th id="date_head"><button id="date_btn" onclick="sortDate()"></button><b>Date Submitted</b></th>
                <th><span id="update_reim_btn"></span></td>
                </tr>`;

    for (let reimbursement of reimbursements) {

        if (rids.includes(reimbursement.rId)) {
            html += `<tr class="reimbursement_row">`;
            html += `<td id="itemField">${reimbursement.item}</td>`;
            html += `<td id="statusField">${reimbursement.status}</td>`;
            html += `<td id="categoryField">${reimbursement.category}</td>`;
            html += `<td id="amountField">${reimbursement.amount}</td>`;
            html += `<td id="employeeField">${reimbursement.employee.firstName} ${reimbursement.employee.lastName}</td>`;
            html += `<td id="noteField">${reimbursement.note}</td>`;
            html += `<td id="dateField">${reimbursement.dateSubmitted}</td>`;
            html += `<td><button id="update_reim_btn" onclick = "updateReim(this.value)" value=${reimbursement.rId}>Update Reimbursement</button></td>`

        }
    }

    table.innerHTML = html;
    if (statusAscending) {
        sessionStorage.setItem("statusAscending",false)
    } else {
        sessionStorage.setItem("statusAscending",true)
    }

}

async function sortCategory() {
    let categoryAscending = (sessionStorage.getItem("categoryAscending")=='true');
    let rids = (await getAllRids()).slice(0);
    const httpResponse = await fetch(`http://localhost:7000/reimbursements/ascendingCategory?ascending=${categoryAscending}`);
    const reimbursements = await httpResponse.json();
    let html = `<tr>
                    <th id="item_head"><button id="item_btn" onclick="sortItem()"></button><b>Item</b></th>
                <th id="status_head"><button id="status_btn" onclick="sortStatus()"></button><b>Status</b></th>
                <th id="category_head"><button id="category_btn" onclick="sortCategory()"></button><b>Category</b></th>
                <th id="amount_head"><button id="amount_btn" onclick="sortAmount()"></button><b>Amount</b></th>
                <th id="employee_head"><button id="employee_btn" onclick="sortEmployee()"></button><b>Employee</b></th>
                <th id="note_head"><button id="note_btn" onclick="sortNote()"></button><b>Note</b></th>
                <th id="date_head"><button id="date_btn" onclick="sortDate()"></button><b>Date Submitted</b></th>
                <th><span id="update_reim_btn"></span></td>
                </tr>`;

    for (let reimbursement of reimbursements) {

        if (rids.includes(reimbursement.rId)) {
            html += `<tr class="reimbursement_row">`;
            html += `<td id="itemField">${reimbursement.item}</td>`;
            html += `<td id="statusField">${reimbursement.status}</td>`;
            html += `<td id="categoryField">${reimbursement.category}</td>`;
            html += `<td id="amountField">${reimbursement.amount}</td>`;
            html += `<td id="employeeField">${reimbursement.employee.firstName} ${reimbursement.employee.lastName}</td>`;
            html += `<td id="noteField">${reimbursement.note}</td>`;
            html += `<td id="dateField">${reimbursement.dateSubmitted}</td>`;
            html += `<td><button id="update_reim_btn" onclick = "updateReim(this.value)" value=${reimbursement.rId}>Update Reimbursement</button></td>`

        }
    }


    table.innerHTML = html;
    if (categoryAscending) {
        sessionStorage.setItem("categoryAscending",false)
    } else {
        sessionStorage.setItem("categoryAscending",true)
    }

}

async function sortAmount() {
    let amountAscending = (sessionStorage.getItem("amountAscending")=='true');
    let rids = (await getAllRids()).slice(0);
    const httpResponse = await fetch(`http://localhost:7000/reimbursements/ascendingAmount?ascending=${amountAscending}`);
    const reimbursements = await httpResponse.json();
    let html = `<tr>
                    <th id="item_head"><button id="item_btn" onclick="sortItem()"></button><b>Item</b></th>
                <th id="status_head"><button id="status_btn" onclick="sortStatus()"></button><b>Status</b></th>
                <th id="category_head"><button id="category_btn" onclick="sortCategory()"></button><b>Category</b></th>
                <th id="amount_head"><button id="amount_btn" onclick="sortAmount()"></button><b>Amount</b></th>
                <th id="employee_head"><button id="employee_btn" onclick="sortEmployee()"></button><b>Employee</b></th>
                <th id="note_head"><button id="note_btn" onclick="sortNote()"></button><b>Note</b></th>
                <th id="date_head"><button id="date_btn" onclick="sortDate()"></button><b>Date Submitted</b></th>
                <th><span id="update_reim_btn"></span></td>
                </tr>`;

    for (let reimbursement of reimbursements) {

        if (rids.includes(reimbursement.rId)) {
            html += `<tr class="reimbursement_row">`;
            html += `<td id="itemField">${reimbursement.item}</td>`;
            html += `<td id="statusField">${reimbursement.status}</td>`;
            html += `<td id="categoryField">${reimbursement.category}</td>`;
            html += `<td id="amountField">${reimbursement.amount}</td>`;
            html += `<td id="employeeField">${reimbursement.employee.firstName} ${reimbursement.employee.lastName}</td>`;
            html += `<td id="noteField">${reimbursement.note}</td>`;
            html += `<td id="dateField">${reimbursement.dateSubmitted}</td>`;
            html += `<td><button id="update_reim_btn" onclick = "updateReim(this.value)" value=${reimbursement.rId}>Update Reimbursement</button></td>`

        }
    }


    table.innerHTML = html;
    if (amountAscending) {
        sessionStorage.setItem("amountAscending",false);
    } else {
        sessionStorage.setItem("amountAscending",true);
    }

}

async function sortEmployee() {
    let employeeAscending = (sessionStorage.getItem("employeeAscending")=='true');
    let rids = (await getAllRids()).slice(0);
    const httpResponse = await fetch(`http://localhost:7000/reimbursements/ascendingEmployee?ascending=${employeeAscending}`);
    const reimbursements = await httpResponse.json();
    let html = `<tr>
                    <th id="item_head"><button id="item_btn" onclick="sortItem()"></button><b>Item</b></th>
                <th id="status_head"><button id="status_btn" onclick="sortStatus()"></button><b>Status</b></th>
                <th id="category_head"><button id="category_btn" onclick="sortCategory()"></button><b>Category</b></th>
                <th id="amount_head"><button id="amount_btn" onclick="sortAmount()"></button><b>Amount</b></th>
                <th id="employee_head"><button id="employee_btn" onclick="sortEmployee()"></button><b>Employee</b></th>
                <th id="note_head"><button id="note_btn" onclick="sortNote()"></button><b>Note</b></th>
                <th id="date_head"><button id="date_btn" onclick="sortDate()"></button><b>Date Submitted</b></th>
                <th><span id="update_reim_btn"></span></td>
                </tr>`;

    for (let reimbursement of reimbursements) {
        if (rids.includes(reimbursement.rId)) {
            html += `<tr class="reimbursement_row">`;
            html += `<td id="itemField">${reimbursement.item}</td>`;
            html += `<td id="statusField">${reimbursement.status}</td>`;
            html += `<td id="categoryField">${reimbursement.category}</td>`;
            html += `<td id="amountField">${reimbursement.amount}</td>`;
            html += `<td id="employeeField">${reimbursement.employee.firstName} ${reimbursement.employee.lastName}</td>`;
            html += `<td id="noteField">${reimbursement.note}</td>`;
            html += `<td id="dateField">${reimbursement.dateSubmitted}</td>`;
            html += `<td><button id="update_reim_btn" onclick = "updateReim(this.value)" value=${reimbursement.rId}>Update Reimbursement</button></td>`
            html += `</tr>`;
        }
    }


    table.innerHTML = html;
    if (employeeAscending) {
        sessionStorage.setItem("employeeAscending",false);
    } else {
        sessionStorage.setItem("employeeAscending",true);
    }

}

async function sortNote() {
    let noteAscending = (sessionStorage.getItem("noteAscending")=='true');
    let rids = (await getAllRids()).slice(0);
    const httpResponse = await fetch(`http://localhost:7000/reimbursements/ascendingNote?ascending=${noteAscending}`);
    const reimbursements = await httpResponse.json();
    let html = `<tr>
                <th id="item_head"><button id="item_btn" onclick="sortItem()"></button><b>Item</b></th>
                <th id="status_head"><button id="status_btn" onclick="sortStatus()"></button><b>Status</b></th>
                <th id="category_head"><button id="category_btn" onclick="sortCategory()"></button><b>Category</b></th>
                <th id="amount_head"><button id="amount_btn" onclick="sortAmount()"></button><b>Amount</b></th>
                <th id="employee_head"><button id="employee_btn" onclick="sortEmployee()"></button><b>Employee</b></th>
                <th id="note_head"><button id="note_btn" onclick="sortNote()"></button><b>Note</b></th>
                <th id="date_head"><button id="date_btn" onclick="sortDate()"></button><b>Date Submitted</b></th>
                <th><span id="update_reim_btn"></span></td>
                </tr>`;

    for (let reimbursement of reimbursements) {

        if (rids.includes(reimbursement.rId)) {
            html += `<tr class="reimbursement_row">`;
            html += `<td id="itemField">${reimbursement.item}</td>`;
            html += `<td id="statusField">${reimbursement.status}</td>`;
            html += `<td id="categoryField">${reimbursement.category}</td>`;
            html += `<td id="amountField">${reimbursement.amount}</td>`;
            html += `<td id="employeeField">${reimbursement.employee.firstName} ${reimbursement.employee.lastName}</td>`;
            html += `<td id="noteField">${reimbursement.note}</td>`;
            html += `<td id="dateField">${reimbursement.dateSubmitted}</td>`;
            html += `<td><button id="update_reim_btn" onclick = "updateReim(this.value)" value=${reimbursement.rId}>Update Reimbursement</button></td>`

        }
    }

    table.innerHTML = html;
    if (noteAscending) {
        sessionStorage.setItem("noteAscending",false);
    } else {
        sessionStorage.setItem("noteAscending",true);
    }

}

async function sortDate() {
    let dateAscending = (sessionStorage.getItem("dateAscending")=='true');
    let rids = (await getAllRids()).slice(0);
    const httpResponse = await fetch(`http://localhost:7000/reimbursements/ascendingDate?ascending=${dateAscending}`);
    const reimbursements = await httpResponse.json();
    let html = `<tr>
                    <th id="item_head"><button id="item_btn" onclick="sortItem()"></button><b>Item</b></th>
                <th id="status_head"><button id="status_btn" onclick="sortStatus()"></button><b>Status</b></th>
                <th id="category_head"><button id="category_btn" onclick="sortCategory()"></button><b>Category</b></th>
                <th id="amount_head"><button id="amount_btn" onclick="sortAmount()"></button><b>Amount</b></th>
                <th id="employee_head"><button id="employee_btn" onclick="sortEmployee()"></button><b>Employee</b></th>
                <th id="note_head"><button id="note_btn" onclick="sortNote()"></button><b>Note</b></th>
                <th id="date_head"><button id="date_btn" onclick="sortDate()"></button><b>Date Submitted</b></th>
                <th><span id="update_reim_btn"></span></td>
                </tr>`;

    for (let reimbursement of reimbursements) {

        if (rids.includes(reimbursement.rId)) {
            html += `<tr class="reimbursement_row">`;
            html += `<td id="itemField">${reimbursement.item}</td>`;
            html += `<td id="statusField">${reimbursement.status}</td>`;
            html += `<td id="categoryField">${reimbursement.category}</td>`;
            html += `<td id="amountField">${reimbursement.amount}</td>`;
            html += `<td id="employeeField">${reimbursement.employee.firstName} ${reimbursement.employee.lastName}</td>`;
            html += `<td id="noteField">${reimbursement.note}</td>`;
            html += `<td id="dateField">${reimbursement.dateSubmitted}</td>`;
            html += `<td><button id="update_reim_btn" onclick = "updateReim(this.value)" value=${reimbursement.rId}>Update Reimbursement</button></td>`

        }
    }

    table.innerHTML = html;
    if (dateAscending) {
        sessionStorage.setItem("dateAscending",false);
    } else {
        sessionStorage.setItem("dateAscending",true);
    }

}

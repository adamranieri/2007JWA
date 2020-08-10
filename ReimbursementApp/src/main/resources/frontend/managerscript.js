
async function managerGetReimbursementsByStatus(status){
    const employee = await getCurrentEmployee();

    const config = {
        method:"GET",
        headers: {'Content-Type':'application/json'}
    }

    let http = ``;
    if(status===''){
        http = `http://localhost:7000/managers/${employee.eId}/reimbursements`;
    }
    else{
        http = `http://localhost:7000/managers/${employee.eId}/reimbursements/?status=${status}`;
    }
    let httpResponse = await fetch(http, config);
    let reimbursements = await httpResponse.json();

    populateManagerTable(reimbursements);

}

function populateManagerTable(reimbursements){
    let bodyRef = document.getElementById('tableElement')
    bodyRef.innerHTML = '';    
    
    let table = document.getElementById("tableElement");
    let row = table.insertRow(0);
    row.insertCell(0).innerHTML = `Reimbursement ID`;
    row.insertCell(1).innerHTML = `Employee ID`;
    row.insertCell(2).innerHTML = `Employee Name`;
    row.insertCell(3).innerHTML = `Status`;
    row.insertCell(4).innerHTML = `Date`;
    row.insertCell(5).innerHTML = `Amount`;
    row.insertCell(6).innerHTML = `Description`;
    row.insertCell(7).innerHTML = `Manager Note`;

    for(let i = 0; i < reimbursements.length; i++){
            table = document.getElementById("tableElement");
            row = table.insertRow(1);
            let idCell = row.insertCell(0);
            let empIdCell = row.insertCell(1);
            let empNameCell = row.insertCell(2);
            let statusCell = row.insertCell(3);
            let dateCell = row.insertCell(4);
            let amountCell = row.insertCell(5);
            let descriptionCell = row.insertCell(6);
            let managerNoteCell = row.insertCell(7);

            idCell.innerHTML = `${reimbursements[i].rId}`;
            empIdCell.innerHTML = `${reimbursements[i].employee.eId}`;
            empNameCell.innerHTML = `${reimbursements[i].employee.name}`
            statusCell.innerHTML = `${reimbursements[i].status}`;
            dateCell.innerHTML = `${reimbursements[i].submitted_date}`;
            amountCell.innerHTML = `${reimbursements[i].amount}`;
            descriptionCell.innerHTML = `${reimbursements[i].description}`;
            managerNoteCell.innerHTML = `${reimbursements[i].manager_note}`;
            
        }

}


let button = document.getElementById("managerBtn");

let close = document.getElementsByClassName("close")[0];

button.onclick = function(){
    modal.style.display = "block";
}

close.onclick = function(){
    modal.style.display = "none";
}

window.onclick = function(event){
    if(event.target == modal){
        modal.style.display = "none";
    }
}

async function managerSubmit(){
    
   let rId = document.getElementById("rId").value;
   let status = document.getElementById("status").value;
   let note = document.getElementById("note").value;


   console.log(rId);
   console.log(status)
   console.log(note)
   const employee = await getCurrentEmployee();

   const config = {
    method:"PUT",
    body:JSON.stringify({rId:rId, status:status, manager_note:note}),
    headers: {'Content-Type':'application/json'}
   }

   let httpResponse = await fetch(`http://localhost:7000/managers/${employee.id}/reimbursements/${rId}`, config);
   let reimbursement = await httpResponse.json();

   alert("Reimbursement Updated!");
   await managerGetReimbursementsByStatus('');

}



    async function logout(){
        window.location.href = "http://localhost:7000/login.html";
    }

    async function getCurrentEmployee(){
        let http = await fetch("http://localhost:7000/userinfo");
        let employee = await http.json();
        return employee;
    }

    async function getReimbursementsByStatus(status){
        const employee = await getCurrentEmployee();        

        const config = {
            method:"GET",
            headers: {'Content-Type':'application/json'}
        }

        let http = ``;
        if(status===''){
            http = `http://localhost:7000/employees/${employee.eId}/reimbursements`;
        }
        else{
            http = `http://localhost:7000/employees/${employee.eId}/reimbursements/?status=${status}`;
        }
        let httpResponse = await fetch(http, config);
        let reimbursements = await httpResponse.json();

        populateTable(reimbursements);
    }

    function populateTable(reimbursements){

        let bodyRef = document.getElementById('tableElement')
        bodyRef.innerHTML = '';    
        
        let table = document.getElementById("tableElement");
        let row = table.insertRow(0);
        row.insertCell(0).innerHTML = `Reimbursement ID`;
        row.insertCell(1).innerHTML = `Status`;
        row.insertCell(2).innerHTML = `Date`;
        row.insertCell(3).innerHTML = `Amount`;
        row.insertCell(4).innerHTML = `Description`;
        row.insertCell(5).innerHTML = `Manager Note`;

        console.log(reimbursements);

        for(let i = 0; i < reimbursements.length; i++){
                table = document.getElementById("tableElement");
                row = table.insertRow(1);
                let idCell = row.insertCell(0);
                let statusCell = row.insertCell(1);
                let dateCell = row.insertCell(2);
                let amountCell = row.insertCell(3);
                let descriptionCell = row.insertCell(4);
                let managerNoteCell = row.insertCell(5);

                idCell.innerHTML = `${reimbursements[i].rId}`;
                statusCell.innerHTML = `${reimbursements[i].status}`;
                dateCell.innerHTML = `${reimbursements[i].submitted_date}`;
                amountCell.innerHTML = `${reimbursements[i].amount}`;
                descriptionCell.innerHTML = `${reimbursements[i].description}`;
                managerNoteCell.innerHTML = `${reimbursements[i].manager_note}`;
            }
    }

    async function submit(){
        let desc = document.getElementById("descInput").value;
        let amt = document.getElementById("amountInput").value;

        const employee = await getCurrentEmployee();

        const config = {
            method:"POST",
            body:JSON.stringify({description:desc, amount:amt}),
            headers: {'Content-Type':'application/json'}
        }

        let httpResponse = await fetch(`http://localhost:7000/employees/${employee.eId}/reimbursements`, config);
        
        let reimbursement = await httpResponse.json();

        await getReimbursementsByStatus('');

        alert("Successfully Created Reimbursement!");
    }

let modal = document.getElementById("myModal");

let btn = document.getElementById("myBtn");

let span = document.getElementsByClassName("close")[0];

btn.onclick = function() {
  modal.style.display = "block";
}

span.onclick = function() {
  modal.style.display = "none";
}

window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }


}


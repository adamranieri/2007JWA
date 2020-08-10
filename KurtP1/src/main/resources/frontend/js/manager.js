let employees=[];
let mId;
let rId;
let eId;
const selectEmployee = document.querySelector('.custom-select');

selectEmployee.addEventListener('change', (event) => {
    eId=event.target.value;
    getReimbursements();
});


async function getUserInfo() {
    const userhttpResponse = await fetch("http://localhost:7000/login")
    const user = await userhttpResponse.json();
    mId=user.managerId;
    document.getElementById('name-field').innerHTML = 'Overseer ' + user.manlName;
    getEmployees();
}

async function getEmployees(){
    const emphttpResponse = await fetch(`http://localhost:7000/managers/${mId}/employees`)
    employees = await emphttpResponse.json();
    populateEmployees();
}

async function getReimbursements(){
    const reihttpResponse = await fetch(`http://localhost:7000/employees/${eId}/reimbursements`)
    reimbursements = await reihttpResponse.json();

    populateReimbursements();
}

async function populateReimbursements(){
    const container = document.getElementById('reimbursements');
    container.innerHTML='';
	reimbursements=reimbursements.reverse()
    reimbursements.forEach((rei, idx) => {
		let submitDate=rei.submitDate.split(' ');
		let statusDate;
		let statusBlock;
		if(rei.statusDate == undefined){
			statusBlock = '';
		}else{
			statusDate= rei.statusDate.split(' ');
			statusBlock=`
						<div class="date-block">
							<p class="card-text">
								<small class="text-muted">Last Updated: </small>
								<small>${statusDate[0]}</small>
							</p> 
							<p class="card-text">
								<small class="text-muted"></small>
								<small>${statusDate[2]} ${statusDate[3]}</small>
							</p>
						</div>`
							;
			}
		let notes;
		if(rei.notes == undefined){
			notes = '';
		}else{
			notes=`<h4 class="card-title text-left">
                                <small class="text-muted">Notes: </small>
                        </h4>
                        <p class="card-text text-left">
                            ${rei.notes}
                        </p>`;
		}
		
        const content = `            
        <div class="row">
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">${rei.reimbursementName}</h5>
						<div>
							<button id="${rei.reimbursementId}" onclick="setCurrentReimbursementId(this.id)" class="rei-edit-btn btn btn-secondary" type="button" data-toggle="modal" data-target="#editModal">
					  			Edit
							</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <div class="card-body">
						<h5 class="card-title">
							<small class="text-muted">Submitted By: </small>
							<small>${rei.employee.empfName} ${rei.employee.emplName}</small>
						</h5>
						<div class="date-block">
	                        <p class="card-text"> 
								<small class="text-muted">Submit Date: </small>
								<small>${submitDate[0]}</small>
							</p>
							<p class="card-text"> 
								<small class="text-muted"></small>
								<small>${submitDate[2]} ${submitDate[3]}</small>
							</p>
						</div>
						${statusBlock}
						
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title"> 
								<small class="text-muted">Amount: </small> 
							<span>${rei.amount}</span> </h4>
                        <h4 class="card-title">
                                <small class="text-muted">Status: </small> 
                            <span id="status-badge-${rei.reimbursementId}" value="${rei.status}" class="badge rei-status-${rei.status}">${rei.status}</span>
                        </h4>
                        ${notes}
                    </div>
                </div>
            </div>
    </div>
                `;
        container.innerHTML += content;
    })
}

async function updateReimbursement(){
    let reiStatusUpdate=document.getElementById('rei-edit-status').value;
    let isAddingNote =document.getElementById('rei-edit-note-choice').value;
    let reiNoteUpdate;
    if(isAddingNote=='Yes'){
        reiNoteUpdate = document.getElementById('rei-edit-note').value;
    } 

    const reihttpCurrentResponse = await fetch(`http://localhost:7000/reimbursements/${rId}`)
    const reihttp = await reihttpCurrentResponse.json();

    let reimbursementUpdate = {
        reimbursementId:reihttp.reimbursementId,
        reimbursementName:reihttp.reimbursementName,
        amount:reihttp.amount,
        status:reiStatusUpdate,
        notes:reiNoteUpdate,
        submitDate:reihttp.submitDate,
        statusDate:reihttp.statusDate
    }

    const reihttpResponse = await fetch(`http://localhost:7000/employees/${eId}/reimbursements`,
        {
            method: 'PUT',
            headers : {'Content-Type': 'application/json'},
            body : JSON.stringify(reimbursementUpdate)
        })
        .then(response => {
            getReimbursements(eId);
        })

}


async function populateEmployees(){
    const container = document.getElementById('choose-emp');
    employees.forEach((emp, idx) => {
        const content=`<option id="choose-emp-${emp.employeeId}" value="${emp.employeeId}">${emp.emplName}, ${emp.empfName} </option>`
        container.innerHTML += content;
    })
}

function setCurrentReimbursementId(id){
    rId = id;
}

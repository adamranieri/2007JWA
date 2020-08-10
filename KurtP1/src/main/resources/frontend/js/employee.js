let reimbursements= [];
let eId;

async function getUserInfo() {
    const userhttpResponse = await fetch("http://localhost:7000/login")
    const user = await userhttpResponse.json();
    eId=user.employeeId;
	document.getElementById('name-field').innerHTML = user.empfName + ' ' + user.emplName;
	getReimbursements();
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
                            <span id="status-badge-${rei.reimbursementId}" class="badge rei-status-${rei.status}">${rei.status}</span>
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

async function createReimbursement(){
    let amountText = document.getElementById("new-rei-amount").value;
    let name = document.getElementById("new-rei-title").value;
	let amount = parseFloat(amountText);
	
    let rei= {
        reimbursementId:0,
        reimbursementName:name,
        amount:amount,
    };

    const reihttpResponse = await fetch(`http://localhost:7000/employees/${eId}/reimbursements`,
        {
            method: 'POST',
            headers : {'Content-Type': 'application/json'},
            body : JSON.stringify(rei)
        })
        .then(response => {
            getReimbursements();
        })

}
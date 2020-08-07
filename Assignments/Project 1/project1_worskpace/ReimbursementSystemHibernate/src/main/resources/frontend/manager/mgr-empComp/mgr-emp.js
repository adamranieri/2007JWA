function init() {
    emp = JSON.parse(window.localStorage.getItem("emp"));
    console.log(emp);

    document.getElementById("emp-name").innerHTML = `
    ${emp.firstName} &nbsp ${emp.lastName}`;


    // HERE I am breaking the reqs between pending and not pending.
    //scoped
    let judgedReqs = [];
    
    //not scoped because I may need it for the update function.
    pendingReqs = [];

    for (let x = 0; x < emp.reqs.length; x++) {
        if (emp.reqs[x].reimbursementStatus === "PENDING")
            pendingReqs.push(emp.reqs[x]);
        else
            judgedReqs.push(emp.reqs[x]);
    }

    console.log(pendingReqs);
    console.log(judgedReqs);


    //Here I print if there is non 0 array of reqs

    let tbHeader = `<tr>
    <th> Request ID   </th>
    <th> The Request  </th>
    <th> Status       </th>
    <th> Reason       </th>
    <th>              </th>
    </tr>`;

/**
 * ${pendingReqs[i].reimbursementStatus} 
 * instead of the line above, I have put a drop down list,
 * I already know what the value is 
 */

    if (pendingReqs.length != 0) {

        let tb= tbHeader;
        for (let i = 0; i < pendingReqs.length; i++) {
            tb += `
        <tr>
            <td>    ${pendingReqs[i].rrId} </td>
            <td>    ${pendingReqs[i].reimbursementRequest}</td>
            
            <td>   

            <select id="drop-down" name="dropdown" >
                <option value= "PENDING" selected>PENDING</option>
                <option value= "APPROVED">APPROVED</option>
                <option value= "REJECTED">REJECTED</option>
            </select>

            </td>

            <td>${pendingReqs[i].reimbursementStatus}</td>
            <td>    ${pendingReqs[i].reason} </td>
            <td><button onclick="updateRequest(${i})" >  update  </button></td>
        </tr>
        `

        }


        document.getElementById("pendingTB").innerHTML = tb;
    }

    if (judgedReqs.length != 0) {

        let tb=tbHeader;

        for (let i = 0; i < judgedReqs.length; i++) {
            tb += `
        <tr>
            <td>    ${judgedReqs[i].rrId} </td>
            <td>    ${judgedReqs[i].reimbursementRequest}</td>
            <td>    ${judgedReqs[i].reimbursementStatus} </td>
            <td>    ${judgedReqs[i].reason} </td>
        </tr>
        `

        }

        document.getElementById("judgedTB").innerHTML = tb;
    }
}
function updateRequest(i) {

    //console.log(document.getElementById("drop-down").value);
    pendingReqs[i].reimbursementStatus=document.getElementById("drop-down").value;

    //console.log(pendingReqs[i].reimbursementStatus);

    if(pendingReqs[i].reimbursementStatus!=='PENDING'){
        console.log(pendingReqs[i]);
        //put
        //get 
        //update values
        //call init to update the values 

    }
}
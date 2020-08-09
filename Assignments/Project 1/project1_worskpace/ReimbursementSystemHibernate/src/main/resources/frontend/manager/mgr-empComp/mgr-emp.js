

async function init() {
    emp = await JSON.parse(window.localStorage.getItem("emp"));
    console.log(emp);

    document.getElementById("emp-name").innerHTML = `
    ${emp.firstName} &nbsp ${emp.lastName}`;

    //global because I need them in other functions
    judgedReqs = [];
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



    if (pendingReqs.length != 0) {

        let tb= tbHeader;
        for (let i = 0; i < pendingReqs.length; i++) {
            tb += `
        <tr id = "pendingRow">
            <td id = "requestId_${pendingReqs[i].rrId}">    ${pendingReqs[i].rrId} </td>
            <td>    ${pendingReqs[i].reimbursementRequest}</td>
            
            <td>   

            <select id="dropDown_${pendingReqs[i].rrId}" name="dropdown" >
                <option value= "PENDING" selected>PENDING</option>
                <option value= "APPROVED">APPROVED</option>
                <option value= "DENIED">DENIED</option>
            </select>

            </td>

            <td>    
                    <textarea name="req" id="theReason_${pendingReqs[i].rrId}" cols="50" rows="5"></textarea>
            </td>
            
            <td >
                    <button id ="updateRequestBtn_${pendingReqs[i].rrId}" 
                        onclick="updateRequest(${i})" >  update  </button>
            </td>
        </tr>
        `

        }


        document.getElementById("pendingTB").innerHTML = tb;
    }

    if (judgedReqs.length != 0) {

        let tb=tbHeader;

        for (let i = 0; i < judgedReqs.length; i++) {
            tb += `
        <tr id="judgedRow">
            <td requestId_{judgedReqs[i].rrId}>    ${judgedReqs[i].rrId} </td>
            <td>    ${judgedReqs[i].reimbursementRequest}</td>
            <td>    ${judgedReqs[i].reimbursementStatus} </td>
            <td>    ${judgedReqs[i].reason} </td>
        </tr>
        `

        }

        document.getElementById("judgedTB").innerHTML = tb;
    }
}
async function updateRequest(i) {

    //console.log(document.getElementById("drop-down").value);
    document.getElementById(`updateRequestBtn_${pendingReqs[i].rrId}`).style.backgroundColor= "lightblue";
    
    pendingReqs[i].reimbursementStatus=document.getElementById(`dropDown_${pendingReqs[i].rrId}`).value;
    pendingReqs[i].reason=document.getElementById(`theReason_${pendingReqs[i].rrId}`).value;

    //console.log(pendingReqs[i].reimbursementStatus);

    if(pendingReqs[i].reimbursementStatus!=='PENDING'){
        console.log(pendingReqs[i]);
        //put
        //get 
        //update values
        //call init to update the values 

        const theRequest= pendingReqs[i];

        const configRequest = {
            method: "PUT",
            headers: { 'Content-Type': 'application/json' }, // you can use the headers properties to set headers
            body: JSON.stringify(theRequest)
        };
    
    
        const httpResponse = await fetch("http://localhost:7000/manager/judgeRequest", configRequest);
        let empReturned = await httpResponse.json();
        empReturned.mgrId=emp.mgrId;
        window.localStorage.setItem("emp", JSON.stringify(empReturned));

        

        let theId={
            mgrId : emp.mgrId};

        const configMgr = {
            method: "POST",
            headers: { 'Content-Type': 'application/json' }, // you can use the headers properties to set headers
            body: JSON.stringify(theId)
        };

        const updatedMgrResponse = await fetch("http://localhost:7000/manager/getUpdatedManager", configMgr);
        let mgrReturned = await updatedMgrResponse.json();

        window.localStorage.setItem("mgr", JSON.stringify(mgrReturned));

        window.location.reload();
    
    }


}
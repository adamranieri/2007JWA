



async function init(){
    emp = await JSON.parse(window.localStorage.getItem("emp"));
    console.log(emp); 

    document.getElementById("eName").innerHTML=`${emp.firstName} ${emp.lastName} `;
    document.getElementById("mName").innerHTML=`${emp.mgr.firstName} ${emp.mgr.lastName}`;
}

async function submitRequest(){

    
    const theRequest = {

        rrId:0,
        empId: emp.empId,
        mgrId: emp.mgr.mgrId,
        reimbursementRequest:document.getElementById("theRequest").value,
        reimbursementStatus: "PENDING",
        reason: ""
    };

    console.log(theRequest);
    
    
    // document.getElementById("test").innerHTML=JSON.stringify(theRequest);
    // document.getElementById("theRequest").value="";

    const configRequest = {
        method: "POST",
        headers: { 'Content-Type': 'application/json' }, // you can use the headers properties to set headers
        body: JSON.stringify(theRequest)
    };


    const httpResponse = await fetch("http://localhost:7000/employee/submitRequest", configRequest);
    let empReturned = await httpResponse.json();



    window.localStorage.setItem("emp", JSON.stringify(empReturned));
    window.location.replace("../empComp/emp.html");



    
}

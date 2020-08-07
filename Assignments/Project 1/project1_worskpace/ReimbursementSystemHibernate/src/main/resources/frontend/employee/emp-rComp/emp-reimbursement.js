



function init(){
    emp = JSON.parse(window.localStorage.getItem("emp"));
    console.log(emp); 

    document.getElementById("eName").innerHTML=`${emp.firstName} ${emp.lastName} `;
    document.getElementById("mName").innerHTML=`${emp.mgr.firstName} ${emp.mgr.lastName}`;
}

function submitRequest(){

    
    theRequest = {

        rrId:0,
        empId: emp.empId,
        mgrId: emp.mgr.mgrId,
        reimbursementRequest:document.getElementById("theRequest").value,
        reimbursementStatus: "PENDING",
        reason: ""
    };

    console.log(theRequest);
    
    
    document.getElementById("test").innerHTML=JSON.stringify(theRequest);
    document.getElementById("theRequest").value="";


    //window.localStorage.setItem("emp", JSON.stringify(empReturned));
   // window.location.replace("../empComp/emp.html");



    // const config = {
    //     method: "POST",
    //     headers: { 'Content-Type': 'application/json' }, // you can use the headers properties to set headers
    //     body: JSON.stringify(credentials)
    // };


    // const httpResponse = await fetch("http://localhost:7000/reimbursement-request", config);
    // let emp = await httpResponse.json();

}

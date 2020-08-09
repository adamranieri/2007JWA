async function init() {

    mgr = await JSON.parse(window.localStorage.getItem("mgr"));
    console.log(mgr.firstName);
    console.log(mgr.emps[0]);

    let tb = "";
    for (let i = 0; i < mgr.emps.length; i++) {
        tb +=
            `
                <tr  onclick = redirectToEmp(${i}) >
                    <td >
                            ${mgr.emps[i].firstName} &nbsp ${mgr.emps[i].lastName} 
                    </td>    
                </tr>

                `
    }

    document.getElementById("tb").innerHTML = tb;
}


async function redirectToEmp(i) {

    mgr.emps[i].mgrId = mgr.mgrId;
    window.localStorage.setItem("emp", JSON.stringify(mgr.emps[i]));
    let jj = window.localStorage.getItem("emp");
    console.log(jj);
    window.location.replace("../mgr-empComp/mgr-emp.html");
}
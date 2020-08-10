async function init() {

    mgr = await JSON.parse(window.localStorage.getItem("mgr"));

    let tb = "";
    for (let i = 0; i < mgr.emps.length; i++) {
        tb +=
            `
                <tr id=${mgr.emps[i].lastName}   onclick = redirectToEmp(${i}) >
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
    window.location.replace("../mgr-empComp/mgr-emp.html");
}
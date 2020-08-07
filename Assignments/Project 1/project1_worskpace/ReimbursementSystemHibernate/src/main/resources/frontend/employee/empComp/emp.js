
        function init() {
            //global var
            
            emp = JSON.parse(window.localStorage.getItem("emp"));
            console.log(emp);

            populateTable();
        };


        function populateTable(){

            console.log(emp.reqs);

            let tb= `<th>The Request</th>
                    <th>Status</th>
                    <th>Reason</th>
                    `;
            for (let i=0; i<emp.reqs.length; i++){
                tb+=
                `<tr> 
                <td>${emp.reqs[i].reimbursementRequest} </td>
                <td>${emp.reqs[i].reimbursementStatus} </td>
                <td>${emp.reqs[i].reason}              <td>           
                </tr>`
            }

            document.getElementById("tb").innerHTML = tb;
        }


        function moveToReimbursementPage() {
            
            window.localStorage.setItem("emp", JSON.stringify(emp));
            window.location.replace("../emp-rComp/emp-reimbursement.html");

        }
        
async function getEmployeeByLogin(){
    const username = document.getElementById("username-input").value;
    const password = document.getElementById("password-input").value;
	console.log(username);
	console.log(password);


    let loginCreds ={
        username:username,
        password:password
    };

    const userhttpResponse = await fetch("http://localhost:7000/login", 
    { 
        method: 'POST',
        headers : {'Content-Type': 'application/json'},
        body : JSON.stringify(loginCreds)
     })
    .then(response => {
        if (response.redirected) {
            window.location.href = response.url;
        }
        if(response.status = 500){
            document.getElementById('warning-msg').innerHTML = "";
            
            let content = `
            <div class="bg-gif">
            <p class="text-left" style="font-family: monospace; color: #199515;">
        
            RobcOS v.85 <br>
            (C)2076 RobCo <br>
            ======================== <br>
            | User Log: <br>
            | &gt;&gt; Administrator (RobCoID 2398-H) <br>
            | &gt;&gt; New_Admin: INTRUDER <br>
            | Welcome new user, INTRUDER <br>
            | &gt;&gt; New_Targeting_Param: <br>
            | &gt;&gt;&gt; INTRUDER_userGroup <br>
            ======================== <br></p>
            <p class="text-left" style="font-family: monospace; color: red;">
            -RobCo Trespasser Management System- <br>
            ==================================== <br>
            &lt;!&gt; YOU HAVE INCORRECTLY ENETERED YOUR CREDENTIALS  <br>
            &lt;!&gt; YOU WILL NOW BE DESTROYED <br>
            &lt;!&gt; LOCKOUT SYSTEM ENGAGED  <br>
            .... <br>
            Authentication FAILED <br>
            TARGETING DATA: INTRUDER_LOCATION <br>
            Warhead Salvo One... DEPLETED <br>
            Warhead Salvo Two... DEPLETED <br>
            Warhead Salvo Three... DEPLETED <br>
            Warhead Salvo Four... DEPLETED <br>
            Warhead Salvo Five... READY <br>
            Warhead Salvo Six... READY <br>
            Warhead Salvo Seven... READY <br>
            Warhead Salvo Eight... READY <br>
            .... <br>
            
            ==================================== <br>
            +TARGET ACQUIRED: INTRUDER_LOCATION+ <br>
            +WARNING: Safety OFF/System ARMED+ <br>
            +WARNING: Orbital Strike Inbound+ <br>
            +THANK YOU FOR CHOOSING ROBCO+ <br>
            ==================================== <br>

            
            </p>
			</div>
        `;
        document.getElementById('warning-msg').innerHTML+=content;
        }
    })
}

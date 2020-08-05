async function getEmployeeByLogin(){
    const username = document.getElementById("username-input").value;
    const password = document.getElementById("password-input").value;

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
    })
    .catch(function(err) {
        console.info(err + " url: " + url);
    });

}
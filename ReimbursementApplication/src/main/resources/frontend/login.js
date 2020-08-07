async function logIn() {
    let user = document.getElementById('userField').value;
    let pass = document.getElementById('passField').value;

    let response = await fetch('http://localhost:7000/login', {
        method: 'post',
        body: JSON.stringify({
            username: user,
            password: pass
        })
    });
    
    
    //document.cookie
    let responseObj = await response.json();
    
    
    if (response.status === 404) {
        alert("Login Failed");
    } else {
        if (responseObj.mid) {
            responseObj.isManager = true;
        } else {
            responseObj.isManager = false;
        }
        localStorage.setItem("user", JSON.stringify(responseObj));
        window.location.replace("home.html");
    }
    
}
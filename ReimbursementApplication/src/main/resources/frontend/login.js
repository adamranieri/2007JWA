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
        localStorage.setItem("username", responseObj.username);
        
        console.log(localStorage.getItem("username"));
        //console.log(document.cookie);
        //window.location.replace("home.html");
        // let response1 = await fetch("http://localhost:7000/userinfo");
        // let response1Obj = await response1.json();
        // console.log(response1Obj);
    }
    
}
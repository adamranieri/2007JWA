async function getEmployeeByLogin(){
    const username = document.getElementById("username-input").value;
    const password = document.getElementById("password-input").value;

    let loginCreds ={
        username:username,
        password:password
    }

    const config = {
        method: "POST",
        headers : {'Content-Type': 'application/json'}, // you can use the headers properties to set headers
        body : JSON.stringify(school)
    };

    const httpResponse = await fetch("http://localhost:7000/login",config);
    const user = await httpResponse.json();
    console.log(user);

}
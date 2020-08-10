class LoginPage{

    usernameInput; 
    passwordInput; 
    loginButton; 

    constructor(){
        this.usernameInput = element(by.id("username-input"));
        this.passwordInput = element(by.id("password-input"));
        this.loginButton = element(by.id("employee-login-btn"));
    }
}
const loginPage = new LoginPage();
module.exports = loginPage;
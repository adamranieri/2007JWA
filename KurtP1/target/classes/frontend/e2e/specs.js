// import {browser, element, by} from protractor



const homePage = require('./login-page'); 
const employeePage = require('./employee-portal-page');
const managerPage = require('./manager-portal-page');

browser.ignoreSynchronization = true;

describe("Employee login test", ()=>{


    it("Should go to login page", ()=>{

        browser.get('http://localhost:7000/home.html')
        browser.sleep(1000);
        const title = browser.getTitle();

        expect(title).toBe('Reimbursement Portal Login')
        
    });

    it("Should log in as an employee",()=>{
        homePage.usernameInput.sendKeys('bdeloria@vault-tec.com');
        homePage.passwordInput.sendKeys('tunnelsnakesrule');

        homePage.loginButton.click();
        browser.sleep(1000);

        expect(browser.getCurrentUrl()).toEqual("http://localhost:7000/empportal.html")

    });

    

})

describe("Employee submits reimbursement test", ()=>{
    
    it("Should approve Prestons NCR reimbursement",()=>{
        browser.get('http://localhost:7000/empportal.html')
        browser.sleep(2000);

        
        employeePage.newReiButton.click()
        browser.sleep(500);


        employeePage.newReiTitle.sendKeys("Radroach exterminators fee");
        employeePage.newReiAmount.sendKeys("216");
        employeePage.submitNewReiButton.click();
        browser.sleep(500);

        employeePage.closeModalButton.click();

        browser.sleep(2000);

        
        employeePage.newReiStatus = element(by.id('status-badge-8'));

        let updatedStatus = employeePage.newReiStatus.getText();
        
        expect(updatedStatus).toEqual('Pending');
    });
})


describe("Manager login test", ()=>{


    it("Should go to login page", ()=>{

        browser.get('http://localhost:7000/home.html')
        browser.sleep(1000);
        const title = browser.getTitle();

        expect(title).toBe('Reimbursement Portal Login')
        
    });

    it("Should log in as a manager",()=>{
        homePage.usernameInput.sendKeys('sdawiec@vault-tec.com');
        homePage.passwordInput.sendKeys('realhuman000');

        homePage.loginButton.click();
        browser.sleep(1000);

        expect(browser.getCurrentUrl()).toEqual("http://localhost:7000/managerportal.html")

    });

    

})



browser.ignoreSynchronization = true;

describe("Manager approves reimbursement test", ()=>{
    
    it("Should approve Prestons NCR reimbursement",()=>{
        browser.get('http://localhost:7000/managerportal.html')
        browser.sleep(1000);

        

        managerPage.chooseEmployeeSelector.click();
        browser.sleep(1000);
        managerPage.chooseEmployeeSelectorValue=element(by.id("choose-emp-3"));
        managerPage.chooseEmployeeSelectorValue.click();
        browser.sleep(1000);

        managerPage.editReimbursementButton=element(by.id("8"));
        managerPage.editReimbursementButton.click();
        browser.sleep(1000);

        managerPage.chooseReiStatusSelector.click();
        managerPage.chooseReiStatusSelectorValue.click();
        
        managerPage.chooseNoteSelector.click();
        managerPage.chooseNoteSelectorValue.click();

        managerPage.noteInput.sendKeys("Thanks for taking care of that. -S");
        managerPage.submitStatusButton.click();
        browser.sleep(2000);


        managerPage.closeModalButton.click();
        browser.sleep(1000);
        managerPage.reimbursementStatus = element(by.id('status-badge-8'));

        let updatedStatus = managerPage.reimbursementStatus.getText();
        
        expect(updatedStatus).toEqual('Approved');
    });
})

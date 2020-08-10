class EmployeePortalPage{

    newReiButton;
    newReiTitle;
    newReiAmount;
    submitNewReiButton;
    closeModalButton;
    newReiStatus;

    constructor(){
        this.newReiButton = element(by.id('rei-modal-launch'));
        this.newReiTitle = element(by.id('new-rei-title'));
        this.newReiAmount = element(by.id('new-rei-amount'));
        this.submitNewReiButton = element(by.id('new-rei-submit-btn'));
        this.closeModalButton = element(by.id("close-modal-btn"));
    }


}
const employeePage = new EmployeePortalPage();
module.exports = employeePage;
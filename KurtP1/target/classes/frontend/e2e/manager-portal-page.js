class ManagerPortalPage{

    chooseEmployeeSelector;
    chooseEmployeeSelectorValue;
    editReimbursementButton;
    chooseReiStatusSelector;
    chooseReiStatusSelectorValue;
    chooseNoteSelector;
    chooseNoteSelectorValue;
    noteInput;
    submitStatusButton;
    closeModalButton;
    reimbursementStatus;

    

    constructor(){
        this.chooseEmployeeSelector = element(by.id("choose-emp"));
        this.chooseReiStatusSelector = element(by.id("rei-edit-status"));
        this.chooseReiStatusSelectorValue = element(by.id("rei-edit-status-approved"));
        this.chooseNoteSelector = element(by.id("rei-edit-note-choice"));
        this.chooseNoteSelectorValue = element(by.id("rei-edit-note-choice-yes"))
        this.noteInput = element(by.id("rei-edit-note"));
        this.submitStatusButton = element(by.id("submit-rei-edit-btn"));
        this.closeModalButton = element(by.id("close-modal-btn"));
    }


}
const managerPage = new ManagerPortalPage();
module.exports = managerPage;
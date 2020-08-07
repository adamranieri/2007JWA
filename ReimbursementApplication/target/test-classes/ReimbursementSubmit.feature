Feature: Submit a Reimbursement

	Background: The user is logged in
		Given the Employee is on the login page
		When the Employee types "Sullivan" into the username field
		When the Employee types "B3stSc4r3r" into the password field
		When the Employee clicks the submit button
		Then the title should be "MPortal"
		
	Scenario: Create
		When the Employee clicks the create reimbursement button
		Then the title should be "MPortal Reimbursement"
		When the Employee types "Costs to go to the UScare Conference" into the reason field
		When the Employee types "126.65" into the amount field
		When the Employee clicks the send button
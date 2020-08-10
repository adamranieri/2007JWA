Feature: Employee creates a new reimbursement

	Scenario Outline: Reimbursement is created
		Given The user is on the employee page
		When The user clicks Create New Reimbursement
		When The user types "<description>" into description
		When The user types "<amount>" into amount
		When The user clicks submit
		Then A new reimbursement was created
		
		Examples:
		|description|amount|
		|food|50|

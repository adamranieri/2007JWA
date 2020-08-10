Feature: Manager updates an existing reimbursement

	Scenario Outline: Reimbursement is updated
		Given The user is on the manager page
		When The user clicks Update Reimbursement
		When The user types "<id>" into id
		When The user types "<note>" into note
		When The user submits items
		Then A  reimbursement was updated
		
		Examples:
		|id|note|
		|4|you're welcome|
Feature: Approve a reimbursement
		
		Background: The user is logged in
			Given the Employee is on the login page
			When the Employee types "Waternoose" into the username field
			When the Employee types "TotallyNotEvilAtAll" into the password field
			When the Employee clicks the submit button
			Then the title should be "MPortal"
		
		Scenario: Approve Reimbursement
			When the Manager clicks the approve/deny button
			Then the title should be "MPortal Reimbursement"
			When the Manager types "Approved, hope the conference went well" into the message field
			When the Manager clicks the approve button
			Then the last reimbursement should be "Approved"
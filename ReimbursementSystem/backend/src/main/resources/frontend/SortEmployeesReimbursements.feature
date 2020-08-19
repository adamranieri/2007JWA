Feature: Sort employees reimbursements

	Scenario:
		Given the employee is on the pawnee reimbursement employee page
		When the employee clicks the sortItem button the first time 
		Then the reimbursements should sort alphabetically by item
	Scenario:
		Given the employee is on the pawnee reimbursement employee page
		When the employee clicks the sortItem button the second time 
		Then the reimbursements should sort unalphabetically by item
		
	Scenario:
		Given the employee is on the pawnee reimbursement employee page
		When the employee clicks the sortStatus button the first time 
		Then the reimbursements should sort alphabetically by status
	Scenario:
		Given the employee is on the pawnee reimbursement employee page
		When the employee clicks the sortStatus button the second time 
		Then the reimbursements should sort unalphabetically by status
		
	Scenario:
		Given the employee is on the pawnee reimbursement employee page
		When the employee clicks the sortCategory button the first time 
		Then the reimbursements should sort alphabetically by category
	Scenario:
		Given the employee is on the pawnee reimbursement employee page
		When the employee clicks the sortCategory button the second time 
		Then the reimbursements should sort unalphabetically  by category
		
	Scenario:
		Given the employee is on the pawnee reimbursement employee page
		When the employee clicks the sortAmount button the first time 
		Then the reimbursements should sort in ascending order by amount
	Scenario:
		Given the employee is on the pawnee reimbursement employee page
		When the employee clicks the sortAmount button the second time 
		Then the reimbursements should sort in descending order by amount
		
	Scenario:
		Given the employee is on the pawnee reimbursement employee page
		When the employee clicks the sortEmployee button the first time 
		Then the reimbursements should sort alphabetically by employee first name
	Scenario:
		Given the employee is on the pawnee reimbursement employee page
		When the employee clicks the sortEmployee button the second time 
		Then the reimbursements should sort unalphabetically by employee first name
		
	Scenario:
		Given the employee is on the pawnee reimbursement employee page
		When the employee clicks the sortNote button the first time 
		Then the reimbursements should sort alphabetically by note
	Scenario:
		Given the employee is on the pawnee reimbursement employee page
		When the employee clicks the sortNote button the second time 
		Then the reimbursements should sort unalphabetically by note
		
	Scenario:
		Given the employee is on the pawnee reimbursement employee page
		When the employee clicks the sortDate button the first time 
		Then the reimbursements should sort by earliest date
	Scenario:
		Given the employee is on the pawnee reimbursement employee page
		When the employee clicks the sortDate button the second time 
		Then the reimbursements should sort by latest date
		
	
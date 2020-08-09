Feature: Employee Submits a request

	Background: Login
		
		Given Employee is on the login page
		When  Employee enters his credentials and hits login
		Then  Employee gets redirected to his employee web page
		


	Scenario: Employee Submits a request

		When  Employee clicks submit new request 
		Then  Employee gets redirected to the Reimbursement Request Form
		
		When  Employee fills the form and clicks submit 
		Then  Employee gets redirected to the employee web page.
		Then  The New request is visible
		
		
		

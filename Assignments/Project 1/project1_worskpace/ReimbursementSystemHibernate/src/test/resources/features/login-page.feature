Feature: login page

	Scenario: Employee wants to login
	
		Given Employee is on the login page
		When  Employee enters his credentials and hits login
		Then  Employee gets directed to his employee web page
		
	Scenario: Manager wants to login
	
		Given Manager is on the login page
		When  Manager enters his credentials and hits login
		Then  Manager gets directed to his employee web page
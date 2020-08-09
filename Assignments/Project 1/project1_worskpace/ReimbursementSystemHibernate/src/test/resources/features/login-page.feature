Feature: login page

	Scenario: Employee wants to login
	
		Given Employee is on the login page
		When  Employee enters his credentials and hits login
		Then  Employee gets redirected to his employee web page
		
	Scenario: Manager wants to login
	
		Given Manager is on the login page
		When  Manager enters his credentials and hits login
		Then  Manager gets redirected to his manager web page
		
		
	Scenario: Employee enters wrong username or password or both
	
		Given Employee is on the login page
		When  Employee enters wrong credentials and click login 
		Then  Employee gets an alert telling him that credentials inputted are wrong.
		
		
		
	Scenario: Manager enters wrong username or password or both
	
		Given Manager is on the login page
		When  Manager enters wrong credentials and click login
		Then  Manager gets an alert telling him that credentials inputted are wrong.
		
		
		
		
		
		
		
		
		
		
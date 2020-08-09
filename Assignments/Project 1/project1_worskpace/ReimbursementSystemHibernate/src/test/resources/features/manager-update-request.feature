Feature: Manager updates a request



	Background: Manager Logs in
	
		Given Manager is on the login page
		When  Manager enters his credentials and hits login
		Then  Manager gets redirected to his manager web page
		
	
	Scenario: Manager Approves Request
	
		When Manager clicks on the name of an employee
		Then Manager gets redirected to the employee requests page
		
		When Manager fills a reason, chooses Approve, and clicks update on a request
		Then the request moves from pending requests to past requests of the employee
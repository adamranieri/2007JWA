Feature: Login an employee or manager

	Background: On the login page
		Given The user is on the login page
		
	Scenario Outline: Login employee
		When The user types "<username>" into username field
		When The user types "<password>" into password field
		When The user submits credentials
		Then The user should be on employee page
		
		Examples:
		|username|password|
		|BigSteve32|stevespassword|
		
	Scenario Outline: Login manager
		When The user types "<username>" into username field
		When The user types "<password>" into password field
		When The user submits credentials
		Then The user should be on manager page
		
		Examples:
		|username|password|
		|bertha444|BERTHASPASSWORD|
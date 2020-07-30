Feature: Wikipedia Languages # Often this is connected to an epic that you write#
	
	# each scenario will connect to a user story#
	Scenario: English Wikipedia 
		Given The Guest is on the Wikipedia home page
		When The Guest clicks English 
		Then The Guest should be on the English home page

	Scenario: Spanish Wikipedia
		Given The Guest is on the Wikipedia home page
		When The Guest clicks Espanol
		Then The Guest should be on the Spanish home page
		
	Scenario: French Wikipedia
		Given The Guest is on the Wikipedia home page
		When The Guest clicks on Francais 
		Then The Guest should be on the French home page
		
	Scenario: Bunch of stuff
		Given The Guest is on the Wikipedia home page
		When The Guest clicks on Francais 
		Given The Guest is on the Wikipedia home page
		When The Guest clicks on Francais
		Given The Guest is on the Wikipedia home page
		When The Guest clicks Espanol
		Given The Guest is on the Wikipedia home page
		When The Guest clicks English 
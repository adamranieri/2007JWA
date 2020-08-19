Feature: 

	Background:
		Given the manager is on the pawnee reimbursement manager page

	Scenario Outline:
		When the manager types into the search field "<query>"
		Then the first item should be "<item>"
		
		Examples:
		|query|item|
		|Gas|Gas|
		|Lunch|Lunch|
		|Painting|Painting|

	Scenario Outline:
		When the manager types into the search field "<query>"
		Then the first status should be "<status>"
		
		Examples:
		|query|status|
		|submitted|submitted|
		|approved|approved|
		|denied|denied|

	Scenario Outline:
		When the manager types into the search field "<query>"
		Then the first category should be "<category>"
		
		Examples:
		|query|category|
		|event|Event|
		|travel|Travel|
		|swag|Swag|
		
	Scenario Outline:
		When the manager types into the search field "<query>"
		Then the first amount should be "<amount>"
		
		Examples:
		|query|amount|
		|30|30|
		|2000|2000|
		|250|250|

	Scenario Outline:
		When the manager types into the search field "<query>"
		Then the first employee should be "<employee>"
		
		Examples:
		|query|employee|
		|apri|April Ludgate|
		|jer|Jerry Gergich|
		|les|Leslie Knope|

	Scenario Outline:
		When the manager types into the search field "<query>"
		Then the first note should be "<note>"
		
		Examples:
		|query|note|
		|food|Food is good|
		|washi|Got lost, ended up in Washington|
		|non|none|

	Scenario Outline:
		When the manager types into the search field "<query>"
		Then the first date should be "<date>"
		
		Examples:
		|query|date|
		|24|Aug 3, 2020, 12:39:24 PM|
		|10:|Mar 13, 2020, 10:35:34 AM|
		|jun|Jun 13, 2020, 7:05:17 AM|

		
		
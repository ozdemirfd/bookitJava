Feature: Login 

As auser,when I go to the login page,
I should be able to login
 
 Background:
 Given the user is on the login page
 
	
 	@login
    Scenario: login as teacher
 	When the user logs in as a teacher
 	Then the user should be logged in

 	
 	
 	@login
    Scenario: login as a team lead
 	When the user logs in as a team lead
 	Then the user should be logged in
Feature: Validate POST Request for User API

Background: User has valid authorization and endpoint: url/Users
 
  @NegativeScenario
  Scenario: POST request for invalid JSON Request and valid authorization
  	Given  User is on POST method for JSON Request with invalid details of user
    When Sends POST request for invalid JSON Request of user
    Then Status message will be displayed as "Bad Request" with the status code 400 
    
    
   @PositiveScenario
  Scenario: POST request for valid JSON request and valid authorization
 	  Given User is on POST method for JSON Request with valid details of user
  	When User sends POST request by inputing user_id,name,ph no,location,time_zone,linkedin_url in JSON body
    Then Status  message will be displayed "Successfully Created" with the status code 201 created
    
  

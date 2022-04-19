Feature: Validate POST Request for Skills API

  Background: User has valid authorization and endpoint: url/Skills

  @PositiveScenario
  Scenario: POST request for valid JSON request and valid authorization
 	  Given User is on POST method for creating new skill with valid JSON Request
  	When User sends POST request with JSON request body by inputing valid skill_name
    Then Status message will be displayed "Successfully Created" with the status code 201 Created
    
  @NegativeScenario
  Scenario: POST request for invalid JSON Request and valid authorization
  	Given  User is on POST method for creating new skill with invalid JSON Request
    When User sends POST request for creating new skill with invalid JSON Request
    Then Status code 400 Bad Request will be displayed
    
    
  
    


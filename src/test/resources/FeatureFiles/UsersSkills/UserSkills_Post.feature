Feature: Validate POST Request for UserSkills API

  Background: User has valid authorization and endpoint: url/UserSkills

  @NegativeScenario
  Scenario: POST request for invalid JSON Request and valid authorization
  	Given  User is on POST method with invalid JSON Request
    When User sends POST request for invalid JSON Request 
    Then The Status message will be displayed as "Not Found" with the status code 404 
    
    
   @PositiveScenario
  Scenario: POST request for valid JSON request and valid authorization
 	  Given User is on POST method for JSON Request and with valid authorization
  	When User sends POST request with JSON request body by inputing user_id, Skill_id, months_of_exp
    Then Status message will be displayed "Successfully Created" with the status code 201 created
    
 
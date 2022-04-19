Feature: Validate PUT Request for User API

  Background: User has valid Authorization and endpoint: url/Users/user_id
  
  @NegativeScenario
  Scenario: PUT request for JSON request with invalid user_id 
   Given User is on PUT method with invalid user_id 
    When User sends PUT request with invalid user_id for JSON request body
    Then The Response message will be displayed as "Not Found" with status code 404

  @PositiveScenario
  Scenario: PUT request for JSON request with valid user_id 
   Given User is on PUT method with valid user_id 
    When User sends PUT request with valid user_id for JSON request body 
    Then The Response message will be displayed with status code 201 Created 
    

Feature: Validate PUT Request for Skills API

  Background: User has valid Authorization and endpoint: url/Skills/skill_id
  
  @PositiveScenario
  Scenario: PUT request for JSON request with valid skill_id 
   Given User is on PUT method with valid skill_id 
    When User sends PUT request with valid skill_id for JSON request body 
    Then Status code 201 Created will be displayed
  
  @NegativeScenario
  Scenario: PUT request for JSON request with invalid skill_id 
    Given User is on PUT method with invalid skill_id 
    When User sends PUT request with invalid skill_id for JSON request body
    Then Status code 404 Not found will be displayed 


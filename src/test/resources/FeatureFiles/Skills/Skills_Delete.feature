Feature: DELETE Request for Skills API

  Background: User has valid authorization and endpoint: url/Skills/skill_id
 
 	@PositiveScenario
  Scenario: DELETE valid skill_id with valid authorization
    Given  User is on DELETE method with valid skill_id
    When User sends DELETE request for valid skill_id
    Then The Response message will be displayed as "The record has been deleted !!" with the status code 200											
    
  @NegativeScenario
  Scenario: DELETE invalid skill_id with valid authorization
    Given User is on DELETE method with invalid skill_id
    When User sends DELETE request for invalid skill_id
    Then Status code 404 Not Found will be displayed
    



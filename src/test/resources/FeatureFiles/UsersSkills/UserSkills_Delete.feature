Feature: DELETE Request for UserSkills API

  Background: User has valid authorization and endpoint: url/UserSkills/id
   @NegativeScenario
  Scenario: DELETE invalid user_skill_id with valid authorization
    Given User is on DELETE method with invalid user_skill_id
    When User sends DELETE request for invalid user_skill_id
    Then The Response message will be displayed as "Not Found" with the status code 404 Not Found

	@PositiveScenario
  Scenario: DELETE valid user_skill_id with valid authorization
    Given  User is on DELETE method with valid user_skill_id
    When User sends DELETE request for valid user_skill_id
    Then The Response message will be displayed as "The record has been deleted !!" with the status code 200 Ok

Feature: Validate Get Request for UserSkillsMap API

  Background: User has valid authorization and endpoint: {url}/UserSkillsMap/user_id
  
@PositiveScenario
  Scenario: GET request for single UsersSkillsMap with valid user_id
    Given User is on Get method for single UsersSkillsMap  with valid user_id
    When User sends GET request for single UsersSkillsMap with valid user_id
    Then Status code 200 Ok will be displayed with Json body.

  @NegativeScenario
  Scenario: GET request for single UsersSkillsMap with invalid user_id
    Given User is on Get method for single UsersSkillsMap with invalid user_id
    When User sends GET request for single UsersSkillsMap with invalid user_id
    Then The Response message will be displayed as "No records found for this id" with the status code 404 not found
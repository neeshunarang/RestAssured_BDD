Feature: Validate Get Request for UsersSkillsMap API

  Background: User has valid authorization and endpoint: {url}/UsersSkillsMap/skill_id
  
@PositiveScenario
  Scenario: GET request for single UsersSkillsMap with valid skill_id
    Given User is on Get method for single UsersSkillsMap  with valid skill_id
    When User sends GET request for single UsersSkillsMap with valid skill_id
    Then Status code 200 Ok will be displayed with Json body

  @NegativeScenario
  Scenario: GET request for single UsersSkillsMap with invalid skill_id
    Given User is on Get method for single UsersSkillsMap with invalid skill_id
    When User sends GET request for single UsersSkillsMap with invalid skill_id
    Then Response message will be displayed as "No records found for this id" with the status code 404 not found
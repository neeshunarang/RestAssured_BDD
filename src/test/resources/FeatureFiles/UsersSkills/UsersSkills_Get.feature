Feature: Validate Get Request for UsersSkills API

  Background: endpoint: {url}/UserSkills
  
  @PositiveScenario
  Scenario: GET request for all Users and their skills with valid authorization
    Given User is on GET Method with valid authorization
    When User enters Username: "APIPROCESSING" and Password: "2xx@Success" and sends GET request
    Then Status code 200 Ok will be displayed

  @NegativeScenario
  Scenario Outline: GET request for all Users and their skills with invalid authorization
    Given User is on GET Method with invalid authorization
    When User enters Username: "<username>" and Password: "<password>" and sends GET request
    Then Status Code 401 unauthorized will be displayed

    Examples: 
      | username | password  |
      | User3451 | india@567 |

  @PositiveScenario
  Scenario: GET request for single User and skills with valid user_skill_id
    Given User is on Get method for single user skill request
    When User sends GET request with valid user_skill_id
    Then The Json response user_skill_id, user_id, Skill_Id and months_of_exp will be displayed with status code 200 Ok.

  @NegativeScenario
  Scenario: GET request for single User skills with invalid user_skill_id
    Given User is on Get method for single user skill request
    When User sends GET request with invalid user_skill_id
    Then The error message will be displayed as "No records found for this id" with the status code 404 not found

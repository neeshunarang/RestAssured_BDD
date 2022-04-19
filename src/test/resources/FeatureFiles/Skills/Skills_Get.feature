Feature: Validate Get Request for Skills API

  Background: endpoint: {url}/Skills
  
  @PositiveScenario
  Scenario: GET request for all skills with valid authorization
    Given User is on GET Request for all skills with valid authorization
    When User enters Username:"APIPROCESSING" and Password "2xx@Success" and sends GET request
    Then Status code 200 will be displayed

  @NegativeScenario
  Scenario Outline: GET request for all skills with invalid authorization
    Given UUser is on GET Request for all skills with invalid authorization
    When User enters Username:"<username>" and Password "<password>" and sends GET request
    Then Status Code 401 Unauthorized will be displayed

    Examples: 
      | username | password  |
      | User3451 | india@567 |

  @PositiveScenario
  Scenario: GET request for single skill with valid skill_id
    Given User is on Get method for single skill request
    When User sends GET request with valid skill_id
    Then The Json response skill_id, and skill_name will be displayed with status code 200 Ok.

  @NegativeScenario
  Scenario: GET request for single skill with invalid skill_id
    Given User is on Get method for single skill request
    When User sends GET request with invalid skill_id
    Then The error message will be displayed as "No records found for this id"with the status code 404 not found
    


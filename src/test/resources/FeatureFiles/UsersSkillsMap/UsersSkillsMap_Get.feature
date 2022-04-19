Feature: Validate Get Request for UserSkillsMap API

  Background: endpoint: {url}/UserSkillsMap
  
  @PositiveScenario
  Scenario: GET request for UsersSkillsMap with valid authorization
    Given User is on GET Method for UsersSkillsMap with valid authorization
    When User enters Username: "APIPROCESSING" and Password: "2xx@Success" and sends request
    Then Status code 200 Ok will be displayed.

  @NegativeScenario
  Scenario Outline: GET request for UsersSkillsMap with invalid authorization 
    Given User is on GET Method for UsersSkillsMap with invalid authorization
    When User enters Username: "<username>" and Password: "<password>" and sends request
    Then The Status Code 401 unauthorized will be displayed.

    Examples: 
      | username | password  |
      | User3451 | india@567 |

  

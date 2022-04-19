
Feature: Validate Get Request for Users API

  Background: endpoint: {url}/Users
	
	@PositiveScenario
  Scenario: GET request for all Users with valid authorization
    Given User is on GET Method 
    When User enters Username:"APIPROCESSING" and Password:"2xx@Success" and sends GET request
    Then Status code 200 ok will be displayed

  @NegativeScenario
  Scenario Outline: GET request for all Users with invalid authorization
    Given User is on GET Method 
    When User enters Username:"<username>" and Password:"<password>" and sends GET request
    Then Status Code 401 unauthorized will be displayed.

    Examples: 
      | username | password  |
      | User3451 | india@567 |
      
  
  @PositiveScenario
  Scenario: GET request for single User with valid user_id
    Given User is on Get method for single user request
    When User sends GET request with valid user_id
    Then The Json response user id,first_name,lastname,phone number,location,time_zone,linkedin url will be displayed with status code 200 Ok.

  @NegativeScenario
  Scenario: GET request for single User with invalid user_id
    Given User is on Get method for single user request
    When User sends GET request with invalid user_id
    Then Status code 404 not found will be displayed
    
  

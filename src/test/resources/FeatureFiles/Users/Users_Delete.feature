Feature: DELETE Request for User API

  Background: User has valid authorization and endpoint: url/Users/user_id
 
  Scenario: DELETE invalid user_id with valid authorization
    Given User is on DELETE method with invalid user_id
    When User sends DELETE request for invalid user_id
    Then The Response message will be displayed  as "Not Found" with the status code 404 Not Found

  Scenario: DELETE valid user_id with valid authorization
    Given  User is on DELETE method with valid user_id
    When User sends DELETE request for valid user_id
    Then The Response message will be displayed  as "The record has been deleted !!" with the status code 200 Ok
    

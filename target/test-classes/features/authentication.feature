@api
Feature: Authentication API
  Test Superset authentication endpoints

  Scenario: Login with valid credentials
    Given Superset API client is initialized
    When I login with username "admin" and password "admin"
    Then the login should be successful
    And the response should contain access token

  Scenario: Login with invalid credentials
    Given Superset API client is initialized
    When I login with username "invalid" and password "invalid"
    Then the authentication response status should be 401

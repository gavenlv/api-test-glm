@api
Feature: User API
  Test Superset user and role endpoints

  Scenario: Get all users
    Given I have admin access
    When I request all users
    Then the response status should be 200
    And the response should contain user list

  Scenario: Get current user info
    Given I have admin access
    When I request current user info
    Then the response status should be 200
    And the response should contain user info

  Scenario: Get roles
    Given I have admin access
    When I request roles
    Then the response status should be 200

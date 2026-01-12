@api
Feature: Log API
  Test Superset log endpoints

  Scenario: Get all logs
    Given I am logged in
    When I request all logs
    Then the response status should be 200
    And the response should contain log list

  Scenario: Get recent activity
    Given I am logged in
    When I request recent activity
    Then the response status should be 200

  Scenario: Get specific log
    Given I am logged in
    When I request specific log
    Then the response status should be 200 or 404

@api
Feature: Database API
  Test Superset database endpoints

  Scenario: Get all databases
    Given I have admin privileges
    When I request all databases
    Then the response status should be 200
    And the response should contain database list

  Scenario: Get specific database
    Given I have admin privileges
    When I request database with id 1
    Then the response status should be 200

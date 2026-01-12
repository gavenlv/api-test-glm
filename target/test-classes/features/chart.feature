@api
Feature: Chart API
  Test Superset chart endpoints

  Scenario: Get all charts
    Given I am authenticated
    When I request all charts
    Then the response status should be 200
    And the response should contain chart data

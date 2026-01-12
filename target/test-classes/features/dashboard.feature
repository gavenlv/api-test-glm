@api
Feature: Dashboard API
  Test Superset dashboard endpoints

  Scenario: Get all dashboards
    Given I am logged in as admin
    When I request all dashboards
    Then the response status should be 200
    And the response should contain dashboard list

  Scenario: Get specific dashboard
    Given I am logged in as admin
    When I request dashboard with id 1
    Then the response status should be 200

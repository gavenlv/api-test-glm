@api
Feature: Menu API
  Test Superset menu endpoints

  Scenario: Get menu items
    Given I am logged in
    When I request menu items
    Then the response status should be 200
    And the response should contain menu items

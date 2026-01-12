@api
Feature: Advanced Data Type API
  Test Superset advanced data type endpoints

  Scenario: Convert advanced data type
    Given I am logged in
    When I convert advanced data type
    Then the response status should be 200

  Scenario: Get advanced data type types
    Given I am logged in
    When I request advanced data type types
    Then the response status should be 200

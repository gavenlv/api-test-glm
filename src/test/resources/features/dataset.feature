@api
Feature: Dataset API
  Test Superset dataset endpoints

  Scenario: Get all datasets
    Given I am logged in
    When I request all datasets
    Then the response status should be 200
    And the response should contain dataset list

  Scenario: Get specific dataset
    Given I am logged in
    When I request dataset with id 1
    Then the response status should be 200

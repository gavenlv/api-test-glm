@api
Feature: Row Level Security API
  Test Superset row level security endpoints

  Scenario: Get all row level security rules
    Given I am logged in
    When I request all row level security rules
    Then the response status should be 200
    And the response should contain row level security list

  Scenario: Get row level security info
    Given I am logged in
    When I request row level security info
    Then the response status should be 200

  Scenario: Create row level security rule
    Given I am logged in
    When I create a row level security rule
    Then the response status should be 201 or 200

  Scenario: Update row level security rule
    Given I am logged in
    When I update a row level security rule
    Then the response status should be 200

  Scenario: Delete row level security rule
    Given I am logged in
    When I delete a row level security rule
    Then the response status should be 200 or 202

  Scenario: Get specific row level security rule
    Given I am logged in
    When I request specific row level security rule
    Then the response status should be 200 or 404

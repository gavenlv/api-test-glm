@api
Feature: CSS Template API
  Test Superset CSS template endpoints

  Scenario: Get all CSS templates
    Given I am logged in
    When I request all CSS templates
    Then the response status should be 200
    And the response should contain CSS template list

  Scenario: Get CSS template info
    Given I am logged in
    When I request CSS template info
    Then the response status should be 200

  Scenario: Create CSS template
    Given I am logged in
    When I create a CSS template
    Then the response status should be 201 or 200

  Scenario: Update CSS template
    Given I am logged in
    When I create a CSS template
    And I update a CSS template
    Then the response status should be 200

  Scenario: Delete CSS template
    Given I am logged in
    When I create a CSS template
    And I delete a CSS template
    Then the response status should be 200 or 202

  Scenario: Get specific CSS template
    Given I am logged in
    When I create a CSS template
    And I request specific CSS template
    Then the response status should be 200 or 404

  Scenario: Get related column values
    Given I am logged in
    When I request related column values for CSS template
    Then the response status should be 200

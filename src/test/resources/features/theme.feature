@api
Feature: Theme API
  Test Superset theme endpoints

  Scenario: Get all themes
    Given I am logged in
    When I request all themes
    Then the response status should be 200
    And the response should contain theme list

  Scenario: Get theme info
    Given I am logged in
    When I request theme info
    Then the response status should be 200

  Scenario: Create theme
    Given I am logged in
    When I create a theme
    Then the response status should be 201 or 200

  Scenario: Update theme
    Given I am logged in
    When I create a theme
    And I update a theme
    Then the response status should be 200

  Scenario: Delete theme
    Given I am logged in
    When I create a theme
    And I delete a theme
    Then the response status should be 200 or 202

  Scenario: Get specific theme
    Given I am logged in
    When I create a theme
    And I request specific theme
    Then the response status should be 200 or 404

  Scenario: Set theme as system dark
    Given I am logged in
    When I create a theme
    And I set theme as system dark
    Then the response status should be 200

  Scenario: Set theme as system default
    Given I am logged in
    When I create a theme
    And I set theme as system default
    Then the response status should be 200

  Scenario: Unset system dark theme
    Given I am logged in
    When I unset system dark theme
    Then the response status should be 200

  Scenario: Unset system default theme
    Given I am logged in
    When I unset system default theme
    Then the response status should be 200

  Scenario: Export theme
    Given I am logged in
    When I create a theme
    And I export theme
    Then the response status should be 200

  Scenario: Import theme
    Given I am logged in
    When I import theme
    Then the response status should be 200

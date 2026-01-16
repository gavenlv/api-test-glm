@api
Feature: Report API
  Test Superset report endpoints

  Scenario: Get all reports
    Given I am logged in
    When I request all reports
    Then the response status should be 200
    And the response should contain report list

  Scenario: Get report info
    Given I am logged in
    When I request report info
    Then the response status should be 200

  Scenario: Get report slack channels
    Given I am logged in
    When I request report slack channels
    Then the response status should be 200

  Scenario: Create report
    Given I am logged in
    When I create a report
    Then the response status should be 201 or 200

  Scenario: Update report
    Given I am logged in
    When I create a report
    And I update a report
    Then the response status should be 200

  Scenario: Delete report
    Given I am logged in
    When I create a report
    And I delete a report
    Then the response status should be 200 or 202

  Scenario: Get report logs
    Given I am logged in
    When I create a report
    And I request report logs
    Then the response status should be 200

  Scenario: Get specific report log
    Given I am logged in
    When I create a report
    And I request specific report log
    Then the response status should be 200

@api
Feature: Saved Query API
  Test Superset saved query endpoints

  Scenario: Get all saved queries
    Given I am logged in
    When I request all saved queries
    Then the response status should be 200
    And the response should contain saved query list

  Scenario: Get saved query info
    Given I am logged in
    When I request saved query info
    Then the response status should be 200

  Scenario: Get distinct values for column
    Given I am logged in
    When I request distinct values for saved query column
    Then the response status should be 200

  Scenario: Create saved query
    Given I am logged in
    When I create a saved query
    Then the response status should be 201 or 200

  Scenario: Update saved query
    Given I am logged in
    When I update a saved query
    Then the response status should be 200

  Scenario: Delete saved query
    Given I am logged in
    When I delete a saved query
    Then the response status should be 200 or 202

  Scenario: Get specific saved query
    Given I am logged in
    When I request specific saved query
    Then the response status should be 200 or 404

  Scenario: Export saved query
    Given I am logged in
    When I export saved queries
    Then the response status should be 200

  Scenario: Import saved query
    Given I am logged in
    When I import saved queries
    Then the response status should be 200

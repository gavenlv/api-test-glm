@api
Feature: SQL Lab API
  Test Superset SQL Lab endpoints

  Scenario: Get SQL Lab queries
    Given I am logged in
    When I request SQL Lab queries
    Then the response status should be 200
    And the response should contain query list

  Scenario: Get distinct values for column
    Given I am logged in
    When I request distinct values for SQL Lab column
    Then the response status should be 200

  Scenario: Estimate query
    Given I am logged in
    When I estimate query
    Then the response status should be 200

  Scenario: Execute query
    Given I am logged in
    When I execute query
    Then the response status should be 200

  Scenario: Stop query
    Given I am logged in
    When I stop query
    Then the response status should be 200

  Scenario: Get updated queries
    Given I am logged in
    When I request updated queries
    Then the response status should be 200

  Scenario: Get specific query
    Given I am logged in
    When I request specific query
    Then the response status should be 200 or 404

  Scenario: Export query results
    Given I am logged in
    When I export query results
    Then the response status should be 200

  Scenario: Format SQL
    Given I am logged in
    When I format SQL
    Then the response status should be 200

  Scenario: Get SQL Lab permalink
    Given I am logged in
    When I request SQL Lab permalink
    Then the response status should be 200

  Scenario: Get specific SQL Lab permalink
    Given I am logged in
    When I request specific SQL Lab permalink
    Then the response status should be 200 or 404

  Scenario: Get query results
    Given I am logged in
    When I request query results
    Then the response status should be 200

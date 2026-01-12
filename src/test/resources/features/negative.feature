@api
Feature: Negative Test Cases
  Test Superset API with negative scenarios

  Scenario: Login with invalid credentials
    Given I attempt to login with invalid credentials
    Then the response status should be 401

  Scenario: Access API without authentication
    Given I access API without authentication
    Then the response status should be 401

  Scenario: Access non-existent resource
    Given I am logged in
    When I request non-existent resource
    Then the response status should be 404

  Scenario: Create resource with invalid data
    Given I am logged in
    When I create resource with invalid data
    Then the response status should be 400

  Scenario: Update non-existent resource
    Given I am logged in
    When I update non-existent resource
    Then the response status should be 404

  Scenario: Delete non-existent resource
    Given I am logged in
    When I delete non-existent resource
    Then the response status should be 404

  Scenario: Access resource without permission
    Given I am logged in
    When I access resource without permission
    Then the response status should be 403

  Scenario: Create duplicate resource
    Given I am logged in
    When I create duplicate resource
    Then the response status should be 400 or 409

  Scenario: Request with invalid parameters
    Given I am logged in
    When I request with invalid parameters
    Then the response status should be 400

  Scenario: Request with missing required fields
    Given I am logged in
    When I request with missing required fields
    Then the response status should be 400

  Scenario: Execute invalid SQL query
    Given I am logged in
    When I execute invalid SQL query
    Then the response status should be 400 or 500

  Scenario: Create dashboard with invalid data
    Given I am logged in
    When I create dashboard with invalid data
    Then the response status should be 400

  Scenario: Create chart with invalid data
    Given I am logged in
    When I create chart with invalid data
    Then the response status should be 400

  Scenario: Create dataset with invalid data
    Given I am logged in
    When I create dataset with invalid data
    Then the response status should be 400

  Scenario: Create database with invalid connection
    Given I am logged in
    When I create database with invalid connection
    Then the response status should be 400 or 500

  Scenario: Request with invalid token
    Given I request with invalid token
    Then the response status should be 401

  Scenario: Request with expired token
    Given I request with expired token
    Then the response status should be 401

  Scenario: Bulk operation with invalid data
    Given I am logged in
    When I perform bulk operation with invalid data
    Then the response status should be 400

  Scenario: Import with invalid format
    Given I am logged in
    When I import with invalid format
    Then the response status should be 400

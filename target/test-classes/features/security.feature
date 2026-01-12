@api
Feature: Security API
  Test Superset security endpoints

  Scenario: Get all groups
    Given I am logged in
    When I request all groups
    Then the response status should be 200
    And the response should contain group list

  Scenario: Get group info
    Given I am logged in
    When I request group info
    Then the response status should be 200

  Scenario: Get specific group
    Given I am logged in
    When I request specific group
    Then the response status should be 200 or 404

  Scenario: Get all permissions
    Given I am logged in
    When I request all permissions
    Then the response status should be 200
    And the response should contain permission list

  Scenario: Get permission info
    Given I am logged in
    When I request permission info
    Then the response status should be 200

  Scenario: Get specific permission
    Given I am logged in
    When I request specific permission
    Then the response status should be 200 or 404

  Scenario: Get all resources
    Given I am logged in
    When I request all resources
    Then the response status should be 200
    And the response should contain resource list

  Scenario: Get resource info
    Given I am logged in
    When I request resource info
    Then the response status should be 200

  Scenario: Get specific resource
    Given I am logged in
    When I request specific resource
    Then the response status should be 200 or 404

  Scenario: Get all roles
    Given I am logged in
    When I request all roles
    Then the response status should be 200
    And the response should contain role list

  Scenario: Get role info
    Given I am logged in
    When I request role info
    Then the response status should be 200

  Scenario: Search roles
    Given I am logged in
    When I search roles
    Then the response status should be 200

  Scenario: Get specific role
    Given I am logged in
    When I request specific role
    Then the response status should be 200 or 404

  Scenario: Get role groups
    Given I am logged in
    When I request role groups
    Then the response status should be 200

  Scenario: Get role permissions
    Given I am logged in
    When I request role permissions
    Then the response status should be 200

  Scenario: Get role users
    Given I am logged in
    When I request role users
    Then the response status should be 200

  Scenario: Get all users
    Given I am logged in
    When I request all users
    Then the response status should be 200
    And the response should contain user list

  Scenario: Get user info
    Given I am logged in
    When I request user info
    Then the response status should be 200

  Scenario: Get specific user
    Given I am logged in
    When I request specific user
    Then the response status should be 200 or 404

  Scenario: Get CSRF token
    Given I am logged in
    When I request CSRF token
    Then the response status should be 200

  Scenario: Refresh token
    Given I am logged in
    When I refresh token
    Then the response status should be 200

  Scenario: Get guest token
    Given I am logged in
    When I request guest token
    Then the response status should be 200 or 400

  Scenario: Get available domains
    Given I am logged in
    When I request available domains
    Then the response status should be 200

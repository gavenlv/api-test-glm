@api
Feature: Annotation Layer API
  Test Superset annotation layer endpoints

  Scenario: Get all annotation layers
    Given I am logged in
    When I request all annotation layers
    Then the response status should be 200
    And the response should contain annotation layer list

  Scenario: Get annotation layer info
    Given I am logged in
    When I request annotation layer info
    Then the response status should be 200

  Scenario: Create annotation layer
    Given I am logged in
    When I create an annotation layer
    Then the response status should be 201 or 200

  Scenario: Update annotation layer
    Given I am logged in
    When I create an annotation layer
    And I update an annotation layer
    Then the response status should be 200

  Scenario: Delete annotation layer
    Given I am logged in
    When I create an annotation layer
    And I delete an annotation layer
    Then the response status should be 200 or 202

  Scenario: Get specific annotation layer
    Given I am logged in
    When I create an annotation layer
    And I request specific annotation layer
    Then the response status should be 200 or 404

  Scenario: Get related column values
    Given I am logged in
    When I request related column values for annotation layer
    Then the response status should be 200

  Scenario: Get annotations for layer
    Given I am logged in
    When I create an annotation layer
    And I request annotations for layer
    Then the response status should be 200

  Scenario: Create annotation
    Given I am logged in
    When I create an annotation layer
    And I create an annotation
    Then the response status should be 201 or 200

  Scenario: Update annotation
    Given I am logged in
    When I create an annotation layer
    And I update an annotation
    Then the response status should be 200

  Scenario: Delete annotation
    Given I am logged in
    When I create an annotation layer
    And I delete an annotation
    Then the response status should be 200 or 202

  Scenario: Get specific annotation
    Given I am logged in
    When I create an annotation layer
    And I request specific annotation
    Then the response status should be 200 or 404

@api
Feature: Tag API
  Test Superset tag endpoints

  Scenario: Get all tags
    Given I am logged in
    When I request all tags
    Then the response status should be 200
    And the response should contain tag list

  Scenario: Get tag info
    Given I am logged in
    When I request tag info
    Then the response status should be 200

  Scenario: Create tag
    Given I am logged in
    When I create a tag
    Then the response status should be 201 or 200

  Scenario: Bulk create tags
    Given I am logged in
    When I bulk create tags
    Then the response status should be 201 or 200

  Scenario: Update tag
    Given I am logged in
    When I update a tag
    Then the response status should be 200

  Scenario: Delete tag
    Given I am logged in
    When I delete a tag
    Then the response status should be 200 or 202

  Scenario: Get specific tag
    Given I am logged in
    When I request specific tag
    Then the response status should be 200 or 404

  Scenario: Get tag favorite status
    Given I am logged in
    When I request tag favorite status
    Then the response status should be 200

  Scenario: Get tag favorites
    Given I am logged in
    When I request tag favorites
    Then the response status should be 200

  Scenario: Get objects with tag
    Given I am logged in
    When I request objects with tag
    Then the response status should be 200

  Scenario: Tag object
    Given I am logged in
    When I tag an object
    Then the response status should be 201 or 200

  Scenario: Untag object
    Given I am logged in
    When I untag an object
    Then the response status should be 200

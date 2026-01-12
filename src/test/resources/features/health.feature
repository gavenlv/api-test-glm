@api
Feature: Health Check API
  Test Superset health check endpoints

  Scenario: Check health status
    Given Superset API client is ready
    When I check health endpoint
    Then the health check should be successful

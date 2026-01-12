package com.superset.steps;

import com.superset.api.SupersetApiClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

public class HealthSteps {
    private final TestContext context;

    public HealthSteps(TestContext context) {
        this.context = context;
    }

    @Given("Superset API client is ready")
    public void supersetApiClientIsReady() {
        context.setClient(new SupersetApiClient());
    }

    @When("I check health endpoint")
    public void iCheckHealthEndpoint() {
        context.setResponse(context.getClient().getHealth());
    }

    @When("I request CSRF token")
    public void iRequestCsrfToken() {
        context.setResponse(context.getClient().getCsrfToken());
    }

    @Then("the health check should be successful")
    public void theHealthCheckShouldBeSuccessful() {
        Assertions.assertEquals(200, context.getResponse().statusCode());
    }

    @Then("the CSRF token should be returned")
    public void theCsrfTokenShouldBeReturned() {
        Assertions.assertEquals(200, context.getResponse().statusCode());
    }
}

package com.superset.steps;

import com.superset.api.SupersetApiClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

public class DashboardSteps {
    private final TestContext context;

    public DashboardSteps(TestContext context) {
        this.context = context;
    }

    @Given("I am logged in as admin")
    public void iAmLoggedInAsAdmin() {
        context.setClient(new SupersetApiClient());
        Response loginResponse = context.getClient().login("admin", "admin");
        Assertions.assertEquals(200, loginResponse.statusCode());
    }

    @When("I request all dashboards")
    public void iRequestAllDashboards() {
        context.setResponse(context.getClient().get("/api/v1/dashboard/"));
    }

    @When("I request dashboard with id {int}")
    public void iRequestDashboardWithId(int id) {
        context.setResponse(context.getClient().get("/api/v1/dashboard/" + id));
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int statusCode) {
        Assertions.assertEquals(statusCode, context.getResponse().statusCode());
    }

    @Then("the response should contain dashboard list")
    public void theResponseShouldContainDashboardList() {
        Assertions.assertNotNull(context.getResponse().jsonPath().getList("result"));
    }
}

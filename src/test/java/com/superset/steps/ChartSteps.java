package com.superset.steps;

import com.superset.api.SupersetApiClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

public class ChartSteps {
    private final TestContext context;

    public ChartSteps(TestContext context) {
        this.context = context;
    }

    @Given("I am authenticated")
    public void iAmAuthenticated() {
        context.setClient(new SupersetApiClient());
        Response loginResponse = context.getClient().login("admin", "admin");
        Assertions.assertEquals(200, loginResponse.statusCode());
    }

    @When("I request all charts")
    public void iRequestAllCharts() {
        context.setResponse(context.getClient().get("/api/v1/chart/data"));
    }

    @When("I request chart with id {int}")
    public void iRequestChartWithId(int id) {
        context.setResponse(context.getClient().get("/api/v1/chart/data?form_data=%7B%22slice_id%22%3A" + id + "%7D"));
    }

    @Then("the response should contain chart data")
    public void theResponseShouldContainChartData() {
        Assertions.assertNotNull(context.getResponse().jsonPath());
    }
}

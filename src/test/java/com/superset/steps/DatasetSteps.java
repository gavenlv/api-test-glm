package com.superset.steps;

import com.superset.api.SupersetApiClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

public class DatasetSteps {
    private final TestContext context;

    public DatasetSteps(TestContext context) {
        this.context = context;
    }

    @When("I request all datasets")
    public void iRequestAllDatasets() {
        context.setResponse(context.getClient().get("/api/v1/dataset/"));
    }

    @When("I request dataset with id {int}")
    public void iRequestDatasetWithId(int id) {
        context.setResponse(context.getClient().get("/api/v1/dataset/" + id));
    }

    @Then("the response should contain dataset list")
    public void theResponseShouldContainDatasetList() {
        Assertions.assertNotNull(context.getResponse().jsonPath().getList("result"));
    }
}

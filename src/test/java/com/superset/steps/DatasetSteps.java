package com.superset.steps;

import io.cucumber.java.en.When;

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
}

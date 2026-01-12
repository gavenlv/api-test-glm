package com.superset.steps;

import com.superset.api.SupersetApiClient;
import com.superset.steps.TestContext;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class MenuSteps {
    private final SupersetApiClient client;
    private final TestContext context;

    public MenuSteps(TestContext context) {
        this.context = context;
        this.client = context.getClient();
    }

    @When("I request menu items")
    public void iRequestMenuItems() {
        Response response = client.get("/api/v1/menu/");
        context.setResponse(response);
    }
}

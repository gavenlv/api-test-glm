package com.superset.steps;

import com.superset.api.SupersetApiClient;
import com.superset.steps.TestContext;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class LogSteps {
    private final SupersetApiClient client;
    private final TestContext context;

    public LogSteps(TestContext context) {
        this.context = context;
        this.client = context.getClient();
    }

    @When("I request all logs")
    public void iRequestAllLogs() {
        Response response = client.get("/api/v1/log/");
        context.setResponse(response);
    }

    @When("I request recent activity")
    public void iRequestRecentActivity() {
        Response response = client.get("/api/v1/log/recent_activity/");
        context.setResponse(response);
    }

    @When("I request specific log")
    public void iRequestSpecificLog() {
        Response response = client.get("/api/v1/log/1");
        context.setResponse(response);
    }
}

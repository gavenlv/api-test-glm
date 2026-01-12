package com.superset.steps;

import com.superset.api.SupersetApiClient;
import com.superset.steps.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class ReportSteps {
    private final SupersetApiClient client;
    private final TestContext context;

    public ReportSteps(TestContext context) {
        this.context = context;
        this.client = context.getClient();
    }

    @Given("I am logged in")
    public void iAmLoggedIn() {
        Response response = client.login("admin", "admin");
        context.setResponse(response);
    }

    @When("I request all reports")
    public void iRequestAllReports() {
        Response response = client.get("/api/v1/report/");
        context.setResponse(response);
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int statusCode) {
        Response response = context.getResponse();
        if (response.statusCode() != statusCode) {
            System.out.println("Expected status: " + statusCode + ", Actual: " + response.statusCode());
            System.out.println("Response body: " + response.getBody().asString());
        }
        assert response.statusCode() == statusCode : "Expected status " + statusCode + " but got " + response.statusCode();
    }

    @Then("the response status should be {int} or {int}")
    public void theResponseStatusShouldBeOr(int statusCode1, int statusCode2) {
        Response response = context.getResponse();
        int actualStatus = response.statusCode();
        assert actualStatus == statusCode1 || actualStatus == statusCode2 : 
            "Expected status " + statusCode1 + " or " + statusCode2 + " but got " + actualStatus;
    }

    @Then("the response should contain report list")
    public void theResponseShouldContainReportList() {
        Response response = context.getResponse();
        assert response.jsonPath().get("result") != null || response.jsonPath().get("count") != null;
    }

    @When("I request report info")
    public void iRequestReportInfo() {
        Response response = client.get("/api/v1/report/_info");
        context.setResponse(response);
    }

    @When("I request report slack channels")
    public void iRequestReportSlackChannels() {
        Response response = client.get("/api/v1/report/slack_channels/");
        context.setResponse(response);
    }

    @When("I create a report")
    public void iCreateAReport() {
        Map<String, Object> reportData = new HashMap<>();
        reportData.put("name", "Test Report");
        reportData.put("description", "Test report description");
        reportData.put("active", true);
        reportData.put("crontab", "0 0 * * *");
        reportData.put("recipients", new String[]{"admin@example.com"});
        
        Response response = client.post("/api/v1/report/", reportData);
        context.setResponse(response);
    }

    @When("I update a report")
    public void iUpdateAReport() {
        Map<String, Object> reportData = new HashMap<>();
        reportData.put("name", "Updated Test Report");
        reportData.put("description", "Updated test report description");
        
        Response response = client.put("/api/v1/report/1", reportData);
        context.setResponse(response);
    }

    @When("I delete a report")
    public void iDeleteAReport() {
        Response response = client.delete("/api/v1/report/1");
        context.setResponse(response);
    }

    @When("I request report logs")
    public void iRequestReportLogs() {
        Response response = client.get("/api/v1/report/1/log/");
        context.setResponse(response);
    }

    @When("I request specific report log")
    public void iRequestSpecificReportLog() {
        Response response = client.get("/api/v1/report/1/log/1");
        context.setResponse(response);
    }
}

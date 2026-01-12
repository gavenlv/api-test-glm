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

    @When("I request all reports")
    public void iRequestAllReports() {
        Response response = client.get("/api/v1/report/");
        context.setResponse(response);
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

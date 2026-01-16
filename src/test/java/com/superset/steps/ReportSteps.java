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
        Response response = context.getDataManager().createTestReport();
        context.setResponse(response);
    }

    @When("I update a report")
    public void iUpdateAReport() {
        Integer reportId = context.getDataManager().getCreatedId("report");
        if (reportId == null) {
            Response createResponse = context.getDataManager().createTestReport();
            context.setResponse(createResponse);
            if (createResponse.statusCode() == 200 || createResponse.statusCode() == 201) {
                try {
                    reportId = createResponse.jsonPath().getInt("id");
                    if (reportId != null) {
                        context.getDataManager().setCreatedId("report", reportId);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing report ID: " + e.getMessage());
                }
            }
            return;
        }
        
        Map<String, Object> reportData = new HashMap<>();
        reportData.put("name", "Updated Test Report");
        reportData.put("description", "Updated test report description");
        
        Response response = client.put("/api/v1/report/" + reportId, reportData);
        context.setResponse(response);
    }

    @When("I delete a report")
    public void iDeleteAReport() {
        Integer reportId = context.getDataManager().getCreatedId("report");
        if (reportId == null) {
            Response createResponse = context.getDataManager().createTestReport();
            context.setResponse(createResponse);
            if (createResponse.statusCode() == 200 || createResponse.statusCode() == 201) {
                try {
                    reportId = createResponse.jsonPath().getInt("id");
                    if (reportId != null) {
                        context.getDataManager().setCreatedId("report", reportId);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing report ID: " + e.getMessage());
                }
            }
            return;
        }
        
        Response response = client.delete("/api/v1/report/" + reportId);
        context.setResponse(response);
    }

    @When("I request report logs")
    public void iRequestReportLogs() {
        Integer reportId = context.getDataManager().getCreatedId("report");
        if (reportId == null) {
            Response createResponse = context.getDataManager().createTestReport();
            context.setResponse(createResponse);
            if (createResponse.statusCode() == 200 || createResponse.statusCode() == 201) {
                try {
                    reportId = createResponse.jsonPath().getInt("id");
                    if (reportId != null) {
                        context.getDataManager().setCreatedId("report", reportId);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing report ID: " + e.getMessage());
                }
            }
            return;
        }
        
        Response response = client.get("/api/v1/report/" + reportId + "/log/");
        context.setResponse(response);
    }

    @When("I request specific report log")
    public void iRequestSpecificReportLog() {
        Integer reportId = context.getDataManager().getCreatedId("report");
        if (reportId == null) {
            Response createResponse = context.getDataManager().createTestReport();
            context.setResponse(createResponse);
            if (createResponse.statusCode() == 200 || createResponse.statusCode() == 201) {
                try {
                    reportId = createResponse.jsonPath().getInt("id");
                    if (reportId != null) {
                        context.getDataManager().setCreatedId("report", reportId);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing report ID: " + e.getMessage());
                }
            }
            return;
        }
        
        Response response = client.get("/api/v1/report/" + reportId + "/log/1");
        context.setResponse(response);
    }
}

package com.superset.steps;

import com.superset.api.SupersetApiClient;
import com.superset.steps.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class NegativeSteps {
    private final SupersetApiClient client;
    private final TestContext context;

    public NegativeSteps(TestContext context) {
        this.context = context;
        this.client = context.getClient();
    }

    @Given("I attempt to login with invalid credentials")
    public void iAttemptToLoginWithInvalidCredentials() {
        if (client == null) {
            context.setClient(new SupersetApiClient());
        }
        Response response = context.getClient().login("invalid_user", "invalid_password");
        context.setResponse(response);
    }

    @Given("I access API without authentication")
    public void iAccessApiWithoutAuthentication() {
        if (client == null) {
            context.setClient(new SupersetApiClient());
        }
        Response response = context.getClient().getWithoutAuth("/api/v1/dashboard/");
        context.setResponse(response);
    }

    @When("I request non-existent resource")
    public void iRequestNonExistentResource() {
        Response response = client.get("/api/v1/dashboard/999999");
        context.setResponse(response);
    }

    @When("I create resource with invalid data")
    public void iCreateResourceWithInvalidData() {
        Map<String, Object> invalidData = new HashMap<>();
        invalidData.put("name", "");
        
        Response response = client.post("/api/v1/dashboard/", invalidData);
        context.setResponse(response);
    }

    @When("I update non-existent resource")
    public void iUpdateNonExistentResource() {
        Map<String, Object> updateData = new HashMap<>();
        updateData.put("name", "Updated Dashboard");
        
        Response response = client.put("/api/v1/dashboard/999999", updateData);
        context.setResponse(response);
    }

    @When("I delete non-existent resource")
    public void iDeleteNonExistentResource() {
        Response response = client.delete("/api/v1/dashboard/999999");
        context.setResponse(response);
    }

    @When("I access resource without permission")
    public void iAccessResourceWithoutPermission() {
        Response response = client.get("/api/v1/security/roles/1");
        context.setResponse(response);
    }

    @When("I create duplicate resource")
    public void iCreateDuplicateResource() {
        Map<String, Object> duplicateData = new HashMap<>();
        duplicateData.put("name", "Duplicate Dashboard");
        
        client.post("/api/v1/dashboard/", duplicateData);
        Response response = client.post("/api/v1/dashboard/", duplicateData);
        context.setResponse(response);
    }

    @When("I request with invalid parameters")
    public void iRequestWithInvalidParameters() {
        Response response = client.get("/api/v1/dashboard/?invalid_param=value");
        context.setResponse(response);
    }

    @When("I request with missing required fields")
    public void iRequestWithMissingRequiredFields() {
        Map<String, Object> incompleteData = new HashMap<>();
        
        Response response = client.post("/api/v1/dashboard/", incompleteData);
        context.setResponse(response);
    }

    @When("I execute invalid SQL query")
    public void iExecuteInvalidSqlQuery() {
        Map<String, Object> queryData = new HashMap<>();
        queryData.put("database_id", 1);
        queryData.put("sql", "INVALID SQL QUERY");
        queryData.put("schema", "main");
        queryData.put("runAsync", false);
        
        Response response = client.post("/api/v1/sqllab/execute/", queryData);
        context.setResponse(response);
    }

    @When("I create dashboard with invalid data")
    public void iCreateDashboardWithInvalidData() {
        Map<String, Object> invalidDashboard = new HashMap<>();
        invalidDashboard.put("name", null);
        
        Response response = client.post("/api/v1/dashboard/", invalidDashboard);
        context.setResponse(response);
    }

    @When("I create chart with invalid data")
    public void iCreateChartWithInvalidData() {
        Map<String, Object> invalidChart = new HashMap<>();
        invalidChart.put("datasource_id", null);
        
        Response response = client.post("/api/v1/chart/data", invalidChart);
        context.setResponse(response);
    }

    @When("I create dataset with invalid data")
    public void iCreateDatasetWithInvalidData() {
        Map<String, Object> invalidDataset = new HashMap<>();
        invalidDataset.put("database", null);
        
        Response response = client.post("/api/v1/dataset/", invalidDataset);
        context.setResponse(response);
    }

    @When("I create database with invalid connection")
    public void iCreateDatabaseWithInvalidConnection() {
        Map<String, Object> invalidDatabase = new HashMap<>();
        invalidDatabase.put("database_name", "Invalid Database");
        invalidDatabase.put("sqlalchemy_uri", "postgresql://invalid:invalid@invalid:5432/invalid");
        
        Response response = client.post("/api/v1/database/", invalidDatabase);
        context.setResponse(response);
    }

    @Given("I request with invalid token")
    public void iRequestWithInvalidToken() {
        if (client == null) {
            context.setClient(new SupersetApiClient());
        }
        context.getClient().setToken("invalid_token_12345");
        Response response = context.getClient().get("/api/v1/dashboard/");
        context.setResponse(response);
    }

    @Given("I request with expired token")
    public void iRequestWithExpiredToken() {
        if (client == null) {
            context.setClient(new SupersetApiClient());
        }
        context.getClient().setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.expired.token");
        Response response = context.getClient().get("/api/v1/dashboard/");
        context.setResponse(response);
    }

    @When("I perform bulk operation with invalid data")
    public void iPerformBulkOperationWithInvalidData() {
        Map<String, Object> invalidBulkData = new HashMap<>();
        invalidBulkData.put("names", new String[]{""});
        
        Response response = client.post("/api/v1/tag/bulk_create", invalidBulkData);
        context.setResponse(response);
    }

    @When("I import with invalid format")
    public void iImportWithInvalidFormat() {
        Map<String, Object> invalidImport = new HashMap<>();
        invalidImport.put("invalid_field", "value");
        
        Response response = client.post("/api/v1/saved_query/import/", invalidImport);
        context.setResponse(response);
    }
}

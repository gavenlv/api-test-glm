package com.superset.steps;

import com.superset.api.SupersetApiClient;
import com.superset.steps.TestContext;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class SavedQuerySteps {
    private final SupersetApiClient client;
    private final TestContext context;

    public SavedQuerySteps(TestContext context) {
        this.context = context;
        this.client = context.getClient();
    }

    @When("I request all saved queries")
    public void iRequestAllSavedQueries() {
        Response response = client.get("/api/v1/saved_query/");
        context.setResponse(response);
    }

    @When("I request saved query info")
    public void iRequestSavedQueryInfo() {
        Response response = client.get("/api/v1/saved_query/_info");
        context.setResponse(response);
    }

    @When("I request distinct values for saved query column")
    public void iRequestDistinctValuesForSavedQueryColumn() {
        Response response = client.get("/api/v1/saved_query/distinct/label");
        context.setResponse(response);
    }

    @When("I create a saved query")
    public void iCreateASavedQuery() {
        Map<String, Object> queryData = new HashMap<>();
        queryData.put("label", "Test Saved Query");
        queryData.put("description", "Test saved query description");
        queryData.put("schema", "public");
        queryData.put("catalog", "superset");
        queryData.put("sql", "SELECT * FROM test_users LIMIT 10");
        queryData.put("db_id", 1);
        
        Response response = client.post("/api/v1/saved_query/", queryData);
        context.setResponse(response);
    }

    @When("I update a saved query")
    public void iUpdateASavedQuery() {
        Map<String, Object> queryData = new HashMap<>();
        queryData.put("label", "Updated Saved Query");
        queryData.put("description", "Updated saved query description");
        
        Response response = client.put("/api/v1/saved_query/1", queryData);
        context.setResponse(response);
    }

    @When("I delete a saved query")
    public void iDeleteASavedQuery() {
        Response response = client.delete("/api/v1/saved_query/1");
        context.setResponse(response);
    }

    @When("I request specific saved query")
    public void iRequestSpecificSavedQuery() {
        Response response = client.get("/api/v1/saved_query/1");
        context.setResponse(response);
    }

    @When("I export saved queries")
    public void iExportSavedQueries() {
        Map<String, Object> params = new HashMap<>();
        params.put("q", new Integer[]{1});
        
        Response response = client.get("/api/v1/saved_query/export/", params);
        context.setResponse(response);
    }

    @When("I import saved queries")
    public void iImportSavedQueries() {
        Map<String, Object> importData = new HashMap<>();
        importData.put("passwords", new HashMap<>());
        
        Response response = client.post("/api/v1/saved_query/import/", importData);
        context.setResponse(response);
    }
}

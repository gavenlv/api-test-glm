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
        Map<String, Object> q = new HashMap<>();
        q.put("page", 1);
        q.put("page_size", 100);
        
        Map<String, Object> params = new HashMap<>();
        params.put("q", q);
        
        Response response = client.get("/api/v1/saved_query/distinct/label", params);
        context.setResponse(response);
    }

    @When("I create a saved query")
    public void iCreateASavedQuery() {
        Response response = context.getDataManager().createTestSavedQuery();
        context.setResponse(response);
    }

    @When("I update a saved query")
    public void iUpdateASavedQuery() {
        Integer queryId = context.getDataManager().getCreatedId("saved_query");
        if (queryId == null) {
            Response createResponse = context.getDataManager().createTestSavedQuery();
            context.setResponse(createResponse);
            if (createResponse.statusCode() == 200 || createResponse.statusCode() == 201) {
                try {
                    queryId = createResponse.jsonPath().getInt("id");
                    if (queryId != null) {
                        context.getDataManager().setCreatedId("saved_query", queryId);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing saved query ID: " + e.getMessage());
                }
            }
            return;
        }
        
        Map<String, Object> queryData = new HashMap<>();
        queryData.put("label", "Updated Saved Query");
        queryData.put("description", "Updated saved query description");
        
        Response response = client.put("/api/v1/saved_query/" + queryId, queryData);
        context.setResponse(response);
    }

    @When("I delete a saved query")
    public void iDeleteASavedQuery() {
        Integer queryId = context.getDataManager().getCreatedId("saved_query");
        if (queryId == null) {
            Response createResponse = context.getDataManager().createTestSavedQuery();
            context.setResponse(createResponse);
            if (createResponse.statusCode() == 200 || createResponse.statusCode() == 201) {
                try {
                    queryId = createResponse.jsonPath().getInt("id");
                    if (queryId != null) {
                        context.getDataManager().setCreatedId("saved_query", queryId);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing saved query ID: " + e.getMessage());
                }
            }
            return;
        }
        
        Response response = client.delete("/api/v1/saved_query/" + queryId);
        context.setResponse(response);
    }

    @When("I request specific saved query")
    public void iRequestSpecificSavedQuery() {
        Integer queryId = context.getDataManager().getCreatedId("saved_query");
        if (queryId == null) {
            Response createResponse = context.getDataManager().createTestSavedQuery();
            context.setResponse(createResponse);
            if (createResponse.statusCode() == 200 || createResponse.statusCode() == 201) {
                try {
                    queryId = createResponse.jsonPath().getInt("id");
                    if (queryId != null) {
                        context.getDataManager().setCreatedId("saved_query", queryId);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing saved query ID: " + e.getMessage());
                }
            }
            return;
        }
        
        Response response = client.get("/api/v1/saved_query/" + queryId);
        context.setResponse(response);
    }

    @When("I export saved queries")
    public void iExportSavedQueries() {
        Integer queryId = context.getDataManager().getCreatedId("saved_query");
        if (queryId == null) {
            Response createResponse = context.getDataManager().createTestSavedQuery();
            context.setResponse(createResponse);
            if (createResponse.statusCode() == 200 || createResponse.statusCode() == 201) {
                try {
                    queryId = createResponse.jsonPath().getInt("id");
                    if (queryId != null) {
                        context.getDataManager().setCreatedId("saved_query", queryId);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing saved query ID: " + e.getMessage());
                }
            }
            return;
        }
        
        Map<String, Object> params = new HashMap<>();
        params.put("q", new Integer[]{queryId});
        
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

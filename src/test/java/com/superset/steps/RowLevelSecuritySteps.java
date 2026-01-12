package com.superset.steps;

import com.superset.api.SupersetApiClient;
import com.superset.steps.TestContext;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class RowLevelSecuritySteps {
    private final SupersetApiClient client;
    private final TestContext context;

    public RowLevelSecuritySteps(TestContext context) {
        this.context = context;
        this.client = context.getClient();
    }

    @When("I request all row level security rules")
    public void iRequestAllRowLevelSecurityRules() {
        Response response = client.get("/api/v1/rowlevelsecurity/");
        context.setResponse(response);
    }

    @When("I request row level security info")
    public void iRequestRowLevelSecurityInfo() {
        Response response = client.get("/api/v1/rowlevelsecurity/_info");
        context.setResponse(response);
    }

    @When("I create a row level security rule")
    public void iCreateARowLevelSecurityRule() {
        Map<String, Object> rlsData = new HashMap<>();
        rlsData.put("name", "Test RLS Rule");
        rlsData.put("description", "Test row level security rule");
        rlsData.put("filter_type", "Regular");
        rlsData.put("group_key", "test_group");
        rlsData.put("clause", "test_column = 'test_value'");
        
        Response response = client.post("/api/v1/rowlevelsecurity/", rlsData);
        context.setResponse(response);
    }

    @When("I update a row level security rule")
    public void iUpdateARowLevelSecurityRule() {
        Map<String, Object> rlsData = new HashMap<>();
        rlsData.put("name", "Updated RLS Rule");
        rlsData.put("description", "Updated row level security rule");
        
        Response response = client.put("/api/v1/rowlevelsecurity/1", rlsData);
        context.setResponse(response);
    }

    @When("I delete a row level security rule")
    public void iDeleteARowLevelSecurityRule() {
        Response response = client.delete("/api/v1/rowlevelsecurity/1");
        context.setResponse(response);
    }

    @When("I request specific row level security rule")
    public void iRequestSpecificRowLevelSecurityRule() {
        Response response = client.get("/api/v1/rowlevelsecurity/1");
        context.setResponse(response);
    }
}

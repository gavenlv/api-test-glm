package com.superset.steps;

import com.superset.api.SupersetApiClient;
import com.superset.steps.TestContext;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class TagSteps {
    private final SupersetApiClient client;
    private final TestContext context;

    public TagSteps(TestContext context) {
        this.context = context;
        this.client = context.getClient();
    }

    @When("I request all tags")
    public void iRequestAllTags() {
        Response response = client.get("/api/v1/tag/");
        context.setResponse(response);
    }

    @When("I request tag info")
    public void iRequestTagInfo() {
        Response response = client.get("/api/v1/tag/_info");
        context.setResponse(response);
    }

    @When("I create a tag")
    public void iCreateATag() {
        Map<String, Object> tagData = new HashMap<>();
        tagData.put("name", "Test Tag");
        tagData.put("description", "Test tag description");
        
        Response response = client.post("/api/v1/tag/", tagData);
        context.setResponse(response);
    }

    @When("I bulk create tags")
    public void iBulkCreateTags() {
        Map<String, Object> tagData = new HashMap<>();
        tagData.put("names", new String[]{"Tag1", "Tag2", "Tag3"});
        
        Response response = client.post("/api/v1/tag/bulk_create", tagData);
        context.setResponse(response);
    }

    @When("I update a tag")
    public void iUpdateATag() {
        Map<String, Object> tagData = new HashMap<>();
        tagData.put("name", "Updated Tag");
        tagData.put("description", "Updated tag description");
        
        Response response = client.put("/api/v1/tag/1", tagData);
        context.setResponse(response);
    }

    @When("I delete a tag")
    public void iDeleteATag() {
        Response response = client.delete("/api/v1/tag/1");
        context.setResponse(response);
    }

    @When("I request specific tag")
    public void iRequestSpecificTag() {
        Response response = client.get("/api/v1/tag/1");
        context.setResponse(response);
    }

    @When("I request tag favorite status")
    public void iRequestTagFavoriteStatus() {
        Response response = client.get("/api/v1/tag/favorite_status/");
        context.setResponse(response);
    }

    @When("I request tag favorites")
    public void iRequestTagFavorites() {
        Response response = client.get("/api/v1/tag/1/favorites/");
        context.setResponse(response);
    }

    @When("I request objects with tag")
    public void iRequestObjectsWithTag() {
        Response response = client.get("/api/v1/tag/get_objects/");
        context.setResponse(response);
    }

    @When("I tag an object")
    public void iTagAnObject() {
        Response response = client.post("/api/v1/tag/dashboard/1/TestTag/", "{}");
        context.setResponse(response);
    }

    @When("I untag an object")
    public void iUntagAnObject() {
        Response response = client.delete("/api/v1/tag/dashboard/1/TestTag/");
        context.setResponse(response);
    }
}

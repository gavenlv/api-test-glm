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
        Response response = context.getDataManager().createTestTag();
        context.setResponse(response);
    }

    @When("I bulk create tags")
    public void iBulkCreateTags() {
        Map<String, Object> tagData = new HashMap<>();
        Map<String, Object> tag1 = new HashMap<>();
        tag1.put("name", "Tag1_" + System.currentTimeMillis());
        tag1.put("description", "Test tag 1");
        
        Map<String, Object> tag2 = new HashMap<>();
        tag2.put("name", "Tag2_" + System.currentTimeMillis());
        tag2.put("description", "Test tag 2");
        
        tagData.put("tags", new Object[]{tag1, tag2});
        
        Response response = client.post("/api/v1/tag/bulk_create", tagData);
        context.setResponse(response);
    }

    @When("I update a tag")
    public void iUpdateATag() {
        Integer tagId = context.getDataManager().getCreatedId("tag");
        if (tagId == null) {
            Response createResponse = context.getDataManager().createTestTag();
            context.setResponse(createResponse);
            if (createResponse.statusCode() == 200 || createResponse.statusCode() == 201) {
                try {
                    tagId = createResponse.jsonPath().getInt("id");
                    if (tagId != null) {
                        context.getDataManager().setCreatedId("tag", tagId);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing tag ID: " + e.getMessage());
                }
            }
            return;
        }
        
        Map<String, Object> tagData = new HashMap<>();
        tagData.put("name", "Updated Tag");
        tagData.put("description", "Updated tag description");
        
        Response response = client.put("/api/v1/tag/" + tagId, tagData);
        context.setResponse(response);
    }

    @When("I delete a tag")
    public void iDeleteATag() {
        Integer tagId = context.getDataManager().getCreatedId("tag");
        if (tagId == null) {
            Response createResponse = context.getDataManager().createTestTag();
            context.setResponse(createResponse);
            if (createResponse.statusCode() == 200 || createResponse.statusCode() == 201) {
                try {
                    tagId = createResponse.jsonPath().getInt("id");
                    if (tagId != null) {
                        context.getDataManager().setCreatedId("tag", tagId);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing tag ID: " + e.getMessage());
                }
            }
            return;
        }
        
        Response response = client.delete("/api/v1/tag/" + tagId);
        context.setResponse(response);
    }

    @When("I request specific tag")
    public void iRequestSpecificTag() {
        Integer tagId = context.getDataManager().getCreatedId("tag");
        if (tagId == null) {
            Response createResponse = context.getDataManager().createTestTag();
            context.setResponse(createResponse);
            if (createResponse.statusCode() == 200 || createResponse.statusCode() == 201) {
                try {
                    tagId = createResponse.jsonPath().getInt("id");
                    if (tagId != null) {
                        context.getDataManager().setCreatedId("tag", tagId);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing tag ID: " + e.getMessage());
                }
            }
            return;
        }
        
        Response response = client.get("/api/v1/tag/" + tagId);
        context.setResponse(response);
    }

    @When("I request tag favorite status")
    public void iRequestTagFavoriteStatus() {
        Integer tagId = context.getDataManager().getCreatedId("tag");
        if (tagId == null) {
            Response createResponse = context.getDataManager().createTestTag();
            context.setResponse(createResponse);
            if (createResponse.statusCode() == 200 || createResponse.statusCode() == 201) {
                try {
                    tagId = createResponse.jsonPath().getInt("id");
                    if (tagId != null) {
                        context.getDataManager().setCreatedId("tag", tagId);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing tag ID: " + e.getMessage());
                }
            }
            return;
        }
        
        Map<String, Object> params = new HashMap<>();
        params.put("q", new Integer[]{tagId});
        
        Response response = client.get("/api/v1/tag/favorite_status/", params);
        context.setResponse(response);
    }

    @When("I request tag favorites")
    public void iRequestTagFavorites() {
        Integer tagId = context.getDataManager().getCreatedId("tag");
        if (tagId == null) {
            Response createResponse = context.getDataManager().createTestTag();
            context.setResponse(createResponse);
            if (createResponse.statusCode() == 200 || createResponse.statusCode() == 201) {
                try {
                    tagId = createResponse.jsonPath().getInt("id");
                    if (tagId != null) {
                        context.getDataManager().setCreatedId("tag", tagId);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing tag ID: " + e.getMessage());
                }
            }
            return;
        }
        
        Response response = client.post("/api/v1/tag/" + tagId + "/favorites/", "{}");
        context.setResponse(response);
    }

    @When("I tag an object")
    public void iTagAnObject() {
        Integer tagId = context.getDataManager().getCreatedId("tag");
        if (tagId == null) {
            Response createResponse = context.getDataManager().createTestTag();
            context.setResponse(createResponse);
            if (createResponse.statusCode() == 200 || createResponse.statusCode() == 201) {
                try {
                    tagId = createResponse.jsonPath().getInt("id");
                    if (tagId != null) {
                        context.getDataManager().setCreatedId("tag", tagId);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing tag ID: " + e.getMessage());
                }
            }
            return;
        }
        
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("tags", new String[]{"TestTag"});
        Response response = client.post("/api/v1/tag/1/1/", requestBody);
        context.setResponse(response);
    }

    @When("I request objects with tag")
    public void iRequestObjectsWithTag() {
        Response response = client.get("/api/v1/tag/get_objects/");
        context.setResponse(response);
    }

    @When("I untag an object")
    public void iUntagAnObject() {
        Response response = client.delete("/api/v1/tag/1/1/TestTag/");
        context.setResponse(response);
    }
}
